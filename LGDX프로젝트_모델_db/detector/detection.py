# detector/detection.py
import os
import time
import cv2
import requests
import numpy as np
from datetime import datetime
from pathlib import Path
from ultralytics import YOLO
from sklearn.ensemble import IsolationForest
from sklearn.neighbors import LocalOutlierFactor
import google.generativeai as genai
import re
import warnings
import json

# 경고 메시지 필터링
warnings.filterwarnings('ignore', category=UserWarning)

# 환경변수에서 로드
SERVER_URL     = os.getenv("SERVER_URL", "http://localhost:8000")
API_EVENT_EP   = f"{SERVER_URL}/event"

# API 키 직접 설정
GOOGLE_API_KEY = "AIzaSyAodNAwhpYmQkLWPA3dv-giw0WppjLhjMY"
genai.configure(api_key=GOOGLE_API_KEY)
LLM = genai.GenerativeModel(model_name="models/gemini-2.5-flash-preview-05-20")

# 프로젝트 루트 경로 설정
PROJECT_ROOT = Path(__file__).resolve().parents[1]

# ===== 입력 소스 설정 =====
# 카메라를 사용하려면 USE_CAMERA = True로 변경
# 영상 파일을 사용하려면 USE_CAMERA = False로 설정하고 VIDEO_PATH 지정
USE_CAMERA = False  # True: 카메라 사용, False: 비디오 파일 사용
CAMERA_ID = 0  # 카메라 사용 시 카메라 ID
VIDEO_PATH = "videos/test_video2.mp4"  # 비디오 파일 경로

# ===== 파라미터 설정 =====
"""
[영상 처리 관련 파라미터]
FRAME_SKIP = 5    # 몇 프레임당 1번 처리할지 설정
                  # 예: 5로 설정 시 5프레임마다 1번 처리 (30fps 기준 1초에 6번 처리)
                  # 값이 작을수록 더 자주 체크하지만 처리 부하가 증가
                  # 값이 클수록 처리는 빠르지만 놓치는 행동이 많아질 수 있음
                  # 권장 범위: 3~10

WINDOW_SIZE = 5    # 한 번에 분석할 프레임 묶음 크기
                   # 예: 5로 설정 시 5프레임을 하나의 묶음으로 분석
                   # FRAME_SKIP과 연계되어 실제 시간 계산됨
                   # 현재 설정: 5프레임 × 5프레임 간격 = 25프레임(약 0.8초) 단위로 분석

DETECTION_WINDOWS = 5    # 이상행동 감지를 위해 체크할 윈도우 수
                        # 예: 5로 설정 시 5번의 연속된 윈도우 체크
                        # 현재 설정: 5번 × 0.8초 = 약 4초 동안의 행동 패턴 체크

BUFFER_SECONDS = 4     # 제미니 분석 및 DB 저장을 위한 영상 길이(초)
                      # 제미니에 보낼 영상과 DB에 저장할 영상의 길이
                      # 값이 크면 더 정확한 분석이 가능하지만 처리 시간 증가
                      # 권장 범위: 3~5초

FPS = 30.0            # 기본 FPS 설정
                      # 대부분의 카메라/영상의 기본값이므로 수정 불필요

MAX_FRAMES = int(FPS * BUFFER_SECONDS)  # 버퍼에 저장할 최대 프레임 수
                                       # 자동 계산되므로 수정 불필요
                                       # 현재 설정: 30fps × 4초 = 120프레임

[민감도 관련 파라미터]
STD_MULTIPLIER = 0.5   # 이상행동 감지 민감도
                      # 값이 작을수록 더 민감하게 감지
                      # 값이 클수록 확실한 이상행동만 감지
                      # 권장 범위: 0.3~1.0

IF_CONTAM = 0.2       # IsolationForest 모델의 이상치 비율
LOF_CONTAM = 0.2      # LocalOutlierFactor 모델의 이상치 비율
                      # 두 값이 클수록 더 많은 행동을 이상행동으로 판단
                      # 권장 범위: 0.1~0.3

[실제 시간 계산 예시]
현재 설정 기준:
1. 프레임 처리: 5프레임마다 1번 → 1초에 6번 처리 (30fps 기준)
2. 행동 분석: 5프레임 × 5번 = 25프레임(약 0.8초) 단위로 분석
3. 이상행동 감지: 5번의 연속된 분석 = 약 4초 동안의 패턴 체크
4. 영상 저장: 감지 후 4초 분량 저장 및 분석

설정 변경 시 고려사항:
1. 빠른 감지가 필요하면: FRAME_SKIP과 WINDOW_SIZE를 줄임
2. 정확한 분석이 필요하면: BUFFER_SECONDS를 늘림
3. 저장 용량 절약이 필요하면: FRAME_SKIP을 늘림
"""

FRAME_SKIP         = 5    # 5프레임당 1프레임 처리
WINDOW_SIZE        = 5    # 이상행동 감지를 위한 윈도우 크기 (5프레임)
DETECTION_WINDOWS  = 5    # 필요한 감지 횟수 (5번)
ALLOWED_NORMAL     = 1    # 허용되는 정상 행동 횟수
FPS               = 30.0  # 기본 FPS
BUFFER_SECONDS    = 4     # 저장할 영상 길이 (초)
MAX_FRAMES        = int(FPS * BUFFER_SECONDS)  # 버퍼에 저장할 최대 프레임 수
STD_MULTIPLIER    = 0.5   # 표준편차 배수 (민감도)
IF_CONTAM         = 0.2   # IsolationForest contamination
LOF_CONTAM        = 0.2   # LocalOutlierFactor contamination
LOF_NEIGHBORS     = 4     # LocalOutlierFactor neighbors

# Gemini 프롬프트 – 0~4단계 명시
dog_name = "별이"
PROMPT = f"""강아지 {dog_name}의 현재 행동을 분석해주세요.
강아지의 자세와 움직임을 기반으로 행동을 해석하고, 아래 형식으로 답변해주세요.

응답 형식:
1. 현재 행동 설명 (1줄)
2. 대처 방법 (1-2줄)
3. 심각도: [0-4]단계
- 0단계: 정상적인 행동 (평온, 휴식, 일상적 활동)
- 1단계: 주의 관찰 필요 (과도한 움직임, 불안한 징후)
- 2단계: 경미한 문제 행동 (반복적인 이상 행동)
- 3단계: 심각한 문제 행동 (공격성, 자해 위험)
- 4단계: 즉각 조치 필요 (위험한 상황)

* 3단계 이상일 경우 응답을 **굵은 글씨**로 표시
* 불필요한 인사말이나 예의적 표현은 생략
* 제공된 정보만으로는 파악이 어려워도 최대한 파악해서 행동 분석해서 답변해주세요.
* 반드시 단계를 숫자로 명시해주세요 (예: '2단계' 또는 '단계: 2')"""

# 모델 로드
MODEL_PATH = PROJECT_ROOT / "dog_pose_model.pt"
print(f"모델 파일 경로: {MODEL_PATH}")
yolo      = YOLO(str(MODEL_PATH))
if_model  = IsolationForest(contamination=IF_CONTAM, random_state=42)
lof_model = LocalOutlierFactor(n_neighbors=LOF_NEIGHBORS, contamination=LOF_CONTAM, novelty=True)

def z_norm(a):
    return (a - a.mean()) / (a.std() or 1.0)

def post_event(timestamp, stage, summary, frames, fps):
    """
    서버 /event 로 이벤트 데이터 전송 및 저장 확인
    """
    try:
        if not frames:
            print("⚠️ 저장할 프레임이 없습니다.")
            return None
            
        current_time = datetime.now().strftime("%Y%m%d_%H%M%S")
        video_name = f"{current_time}.avi"
        temp_path = os.path.join('temp_videos', video_name)
        
        # 임시 디렉토리 생성
        os.makedirs('temp_videos', exist_ok=True)
        
        print(f"\n📁 임시 파일 생성 중: {temp_path}")
        
        h, w = frames[0].shape[:2]
        fourcc = cv2.VideoWriter_fourcc(*'XVID')
        out = cv2.VideoWriter(temp_path, fourcc, fps/FRAME_SKIP, (w, h))
        
        for frame in frames:
            if frame.shape[:2] != (h, w):
                frame = cv2.resize(frame, (w, h))
            out.write(frame)
        out.release()
        
        if not os.path.exists(temp_path):
            print("⚠️ 임시 파일이 생성되지 않았습니다!")
            return None
            
        file_size = os.path.getsize(temp_path)
        print(f"📤 서버로 데이터 전송 중... (파일 크기: {file_size/1024:.1f}KB)")
        
        # multipart/form-data 형식으로 데이터 전송
        with open(temp_path, 'rb') as f:
            files = {'video_data': (video_name, f, 'video/avi')}
            data = {
                'timestamp': timestamp,
                'stage': str(stage),  # 문자열로 변환
                'summary': summary
            }
            
            try:
                r = requests.post(API_EVENT_EP, data=data, files=files, timeout=30)
                print(f"   - 서버 응답 코드: {r.status_code}")
                print(f"   - 서버 응답 내용: {r.text}")
                
                if r.status_code == 200:
                    response = r.json()
                    if response.get('id'):
                        print(f"✅ 영상 저장 완료!")
                        print(f"   - 파일명: {video_name}")
                        print(f"   - 프레임 수: {len(frames)}")
                        print(f"   - DB ID: {response['id']}")
                        return response
                    
                print("⚠️ DB 저장 실패: 서버 응답 확인 필요")
                return None
                    
            except requests.exceptions.RequestException as e:
                print(f"⚠️ HTTP 요청 실패: {str(e)}")
                return None
                
    except Exception as e:
        print(f"⚠️ 이벤트 전송 실패: {str(e)}")
        import traceback
        print(traceback.format_exc())
        return None
        
    finally:
        try:
            if os.path.exists(temp_path):
                os.remove(temp_path)
                print(f"🗑️ 임시 파일 삭제됨: {temp_path}")
        except Exception as e:
            print(f"⚠️ 임시 파일 삭제 실패: {str(e)}")

def analyze_with_gemini(frames_count):
    """제미니를 사용한 행동 분석 함수"""
    try:
        print("\n🤖 제미니 분석 시작...")
        
        # 프롬프트에 현재 상황 추가
        analysis_prompt = f"""{PROMPT}

현재 상황: 
- 강아지가 {BUFFER_SECONDS}초 동안 연속적으로 특이한 행동을 보였습니다.
- 총 {frames_count}개의 프레임에서 이상 행동이 감지되었습니다.
- 자세한 분석을 부탁드립니다."""

        print("📝 프롬프트 전송 중...")
        
        # 제미니 API 호출
        resp = LLM.generate_content([analysis_prompt])
        
        if not resp:
            print("⚠️ 제미니 응답이 비어있습니다.")
            return 0, "분석 실패: 응답 없음"
            
        text = resp.text.strip()
        print("\n✅ 제미니 응답 받음:")
        print(text)
        
        # 단계 추출 시도
        stage = 0
        stage_match = re.search(r'(\d+)단계', text)
        if stage_match:
            stage = int(stage_match.group(1))
        else:
            # 다른 형식 시도 (예: "단계: 2" 또는 "심각도: 2")
            alt_match = re.search(r'단계:\s*(\d+)|심각도:\s*(\d+)', text)
            if alt_match:
                stage = int(alt_match.group(1) or alt_match.group(2))
            else:
                # 마지막 시도: 첫 번째 숫자 찾기
                num_match = re.search(r'\d+', text)
                if num_match:
                    stage = int(num_match.group())
        
        if stage < 0 or stage > 4:
            print(f"⚠️ 잘못된 단계 값 ({stage}) - 0으로 설정")
            stage = 0
            
        print(f"\n📊 분석 결과:")
        print(f"- 감지된 단계: {stage}")
        print(f"- 분석 길이: {len(text)} 문자")
        
        return stage, text
        
    except Exception as e:
        print(f"\n❌ 제미니 분석 중 오류 발생:")
        print(f"- 오류 유형: {type(e).__name__}")
        print(f"- 오류 내용: {str(e)}")
        import traceback
        print("- 상세 오류:")
        print(traceback.format_exc())
        return 0, f"분석 실패: {str(e)}"

def process_video():
    """
    영상 처리 메인 함수
    카메라 또는 비디오 파일에서 프레임을 읽어 처리
    """
    print("🎥 영상 소스 초기화 중...")
    
    if USE_CAMERA:
        # 카메라 모드
        cap = cv2.VideoCapture(CAMERA_ID, cv2.CAP_DSHOW)
        if not cap.isOpened():
            print("❌ 카메라를 열 수 없습니다!")
            return
        print(f"✅ 카메라 {CAMERA_ID} 연결됨")
    else:
        # 비디오 파일 모드
        video_path = str(PROJECT_ROOT / VIDEO_PATH)
        cap = cv2.VideoCapture(video_path)
        if not cap.isOpened():
            print(f"❌ 비디오 파일을 열 수 없습니다: {video_path}")
            return
        print(f"✅ 비디오 파일 로드됨: {video_path}")
    
    # 해상도 설정 (카메라 모드일 때만)
    if USE_CAMERA:
        cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1280)
        cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 720)
    
    # 비디오 정보 출력
    width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = cap.get(cv2.CAP_PROP_FPS) or FPS
    total_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    
    print(f"- 해상도: {width}x{height}")
    print(f"- FPS: {fps:.1f}")
    print(f"- 프레임 스킵: {FRAME_SKIP} (처리 FPS: {fps/FRAME_SKIP:.1f})")
    print(f"- 버퍼 크기: {BUFFER_SECONDS}초 ({MAX_FRAMES} 프레임)")
    if not USE_CAMERA:
        print(f"- 총 프레임 수: {total_frames}")
        print(f"- 예상 재생 시간: {total_frames/fps:.1f}초")

    # 초기화
    features = []  # 특징점 버퍼
    frames_vis = []  # 시각화된 프레임 버퍼
    frame_idxs = []  # 프레임 인덱스 버퍼
    frame_no = 0
    last_event_time = 0
    is_recording = False
    current_stage = 0
    current_summary = ""
    
    # 이상행동 감지 관련 변수
    abnormal_windows = []  # 각 윈도우의 이상행동 여부를 저장
    continuous_detection = False  # 연속 감지 상태
    
    cv2.namedWindow("Dog Pose Detection", cv2.WINDOW_NORMAL)

    while True:
        ret, frame = cap.read()
        if not ret:
            print("✅ 영상 처리 완료!")
            break
            
        if frame is None or frame.size == 0:
            print("⚠️ 빈 프레임을 받았습니다.")
            continue
            
        frame_no += 1
        current_time = time.time()
        
        # 진행률 표시 (비디오 파일 모드일 때만)
        if not USE_CAMERA and frame_no % 30 == 0:
            progress = (frame_no / total_frames) * 100
            print(f"\r진행률: {progress:.1f}%", end="")
        
        if frame_no % FRAME_SKIP != 0:
            continue

        # YOLO로 포즈 예측 및 시각화
        result = yolo.predict(frame, conf=0.7, verbose=False)[0]
        vis = result.plot()

        if result.keypoints is not None and len(result.keypoints.data) > 0:
            kpts = result.keypoints.data[0]
            if kpts.shape[0] == 24:
                pelvis = kpts[14][:2]
                l_sh, r_sh = kpts[11][:2], kpts[12][:2]
                scale = np.linalg.norm(l_sh - r_sh) or 1.0

                feat = []
                for x, y, v in kpts:
                    if v < 0.5:
                        feat += [0.0, 0.0]
                    else:
                        feat += [(x - pelvis[0]) / scale,
                                (y - pelvis[1]) / scale]
                features.append(feat)
                frames_vis.append(vis.copy())
                frame_idxs.append(frame_no)

                # 특징점이 충분히 모이면 이상행동 감지
                if len(features) >= WINDOW_SIZE:
                    X = np.array(features[-WINDOW_SIZE:])
                    if_model.fit(X)
                    lof_model.fit(X)
                    if_scores = if_model.decision_function(X)
                    lof_scores = lof_model.decision_function(X)
                    combined = (z_norm(if_scores) + z_norm(lof_scores)) / 2.0
                    
                    # 임계치 계산 및 이상행동 판정
                    threshold = combined.mean() - STD_MULTIPLIER * combined.std()
                    is_abnormal = combined[-1] < threshold

                    # 현재 윈도우의 이상행동 여부 저장
                    abnormal_windows.append(is_abnormal)
                    if len(abnormal_windows) > DETECTION_WINDOWS:
                        abnormal_windows.pop(0)

                    # 연속된 이상행동 체크
                    if len(abnormal_windows) == DETECTION_WINDOWS:
                        abnormal_count = sum(abnormal_windows)
                        if abnormal_count >= (DETECTION_WINDOWS - ALLOWED_NORMAL) and not continuous_detection:
                            continuous_detection = True
                            is_recording = True
                            frames_vis = []  # 버퍼 초기화
                            print("\n🔍 연속적인 이상행동 감지 - 영상 수집 시작")
                    
                    # 영상 수집 및 분석
                    if is_recording:
                        if len(frames_vis) >= MAX_FRAMES:
                            print(f"\n📹 영상 수집 완료 ({len(frames_vis)} 프레임)")
                            
                            # 제미니 분석 실행
                            current_stage, current_summary = analyze_with_gemini(len(frames_vis))
                            
                            # 0단계가 아닐 때만 저장
                            if current_stage > 0:
                                print(f"\n💾 영상 저장 시도 중... (단계: {current_stage})")
                                result = post_event(datetime.now().isoformat(), 
                                                  current_stage, 
                                                  current_summary, 
                                                  frames_vis, 
                                                  fps)
                                
                                if result:
                                    print(f"✅ 영상 저장 완료!")
                                    print(f"   - 프레임 수: {len(frames_vis)}")
                                    print(f"   - 영상 길이: {BUFFER_SECONDS}초")
                                    print(f"   - 행동 단계: {current_stage}")
                                else:
                                    print("⚠️ 이벤트 저장 실패")
                            else:
                                print(f"\n✨ 정상 행동으로 판단됨 (0단계) - 영상 저장하지 않음")
                            
                            # 상태 초기화
                            frames_vis = []
                            is_recording = False
                            continuous_detection = False
                            abnormal_windows = []
                            current_stage = 0
                            current_summary = ""

                    # 버퍼 관리
                    if len(features) > WINDOW_SIZE:
                        features.pop(0)
                    if len(frames_vis) > MAX_FRAMES:
                        frames_vis.pop(0)
                    if len(frame_idxs) > MAX_FRAMES:
                        frame_idxs.pop(0)

        cv2.imshow("Dog Pose Detection", vis)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            print("\n사용자가 종료를 요청했습니다.")
            break

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    process_video()
