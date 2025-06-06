# detector/detection.py
# 개선된 코드 구조: 짧은 영상에서도 정확하게 분석되도록 슬라이딩 버퍼 방식 적용 + Gemini 분석 최적화

import os
import time
import cv2
import numpy as np
from datetime import datetime
from pathlib import Path
from ultralytics import YOLO
from sklearn.ensemble import IsolationForest
from sklearn.neighbors import LocalOutlierFactor
import google.generativeai as genai
import re
import warnings
from PIL import Image
from io import BytesIO

warnings.filterwarnings('ignore', category=UserWarning)

GOOGLE_API_KEY = "AIzaSyAodNAwhpYmQkLWPA3dv-giw0WppjLhjMY"
genai.configure(api_key=GOOGLE_API_KEY)
LLM = genai.GenerativeModel(model_name="models/gemini-2.0-flash")

PROJECT_ROOT = Path(__file__).resolve().parent
USE_CAMERA = False
CAMERA_ID = 0
VIDEO_PATH = "./videos/aggressive_dog.mp4"

FRAME_SKIP = 3
WINDOW_SIZE = 5
DETECTION_WINDOWS = 3
ALLOWED_NORMAL = 1
FPS = 30.0
GEMINI_BUFFER_SECONDS = 5
DB_BUFFER_SECONDS = 5
GEMINI_MAX_FRAMES = int(FPS * GEMINI_BUFFER_SECONDS)
DB_MAX_FRAMES = int(FPS * DB_BUFFER_SECONDS)
STD_MULTIPLIER = 0.3
IF_CONTAM = 0.2
LOF_CONTAM = 0.2
LOF_NEIGHBORS = 4
dog_name = "별이"

PROMPT = """당신은 강아지의 이상행동을 감독하는 수의사입니다.
강아지의 현재 행동을 분석하고 보호자가 이해하기 쉽게 설명해주세요. 

3줄 이내로 다음을 설명해주세요:
1. 지금 무엇을 하고 있는지
2. 보호자가 어떻게 대처해야 하는지
3. 심각도: [0/1/2/3]단계 중 선택

* 위급 상황일 때만 **굵게 표시**해
* 예의적인 표현은 생략"""

MODEL_PATH = PROJECT_ROOT / "dog_pose_model.pt"
yolo = YOLO(str(MODEL_PATH))
if_model  = IsolationForest(contamination=IF_CONTAM, random_state=42)
lof_model = LocalOutlierFactor(n_neighbors=LOF_NEIGHBORS, contamination=LOF_CONTAM, novelty=True)

# 중요 키워드 정의
CRITICAL_KEYWORDS = [
    "위험", "응급", "경련", "발작", "치료", "즉시", "병원", "고통", "심각", "위급",
    "불안", "공격", "무기력", "탈진", "탈수", "구토", "설사", "호흡", "마비", "경직"
]

def z_norm(a):
    return (a - a.mean()) / (a.std() or 1.0)

def summarize_action(max_movement, avg_movement, movement_description):
    prompt = f"""
당신은 강아지의 이상행동을 감독하는 수의사입니다.
3줄 이내로 다음을 설명해주세요:
1. 지금 무엇을 하고 있는지 (예: 다리를 심하게 흔들고 있음)
2. 보호자가 어떻게 대처해야 하는지
3. 심각도: [0/1/2/3]단계 중 선택

* 위급 상황일 때만 **굵게 표시**해
* 예의적인 표현은 생략

[관찰 정보]
- 최대 움직임: {max_movement:.2f}
- 평균 움직임: {avg_movement:.2f}
- 행동 요약: {movement_description}
"""
    return prompt

def encode_frames_to_image(frames):
    try:
        if not frames:
            return None
        # 대표 프레임 선택 (마지막 프레임)
        frame = frames[-1]
        _, img_encoded = cv2.imencode(".jpg", frame)
        
        # 이미지 파일로 저장
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        os.makedirs("gemini_input", exist_ok=True)
        image_path = f"gemini_input/analysis_frame_{timestamp}.jpg"
        cv2.imwrite(image_path, frame)
        print(f"🖼️ Gemini 입력 이미지 저장됨: {image_path}")
        
        return img_encoded.tobytes()
    except Exception as e:
        print("❌ 이미지 인코딩 오류:", str(e))
        return None

def encode_frames_to_video(frames):
    try:
        if not frames:
            return None
            
        height, width, _ = frames[0].shape
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        os.makedirs("analyzed_clips", exist_ok=True)
        video_path = f"analyzed_clips/analysis_{timestamp}.mp4"
        
        # VideoWriter 초기화
        fourcc = cv2.VideoWriter_fourcc(*'mp4v')
        out = cv2.VideoWriter(video_path, fourcc, 10.0, (width, height))
        
        # 프레임들을 비디오로 작성
        for frame in frames:
            out.write(frame)
        out.release()
        
        return video_path
    except Exception as e:
        print("❌ 비디오 인코딩 오류:", str(e))
        return None

def analyze_with_gemini(frames_count, features_data, frames_vis):
    try:
        print("\n🤖 제미니 분석 시작...")
        print(f"📊 분석 프레임 수: {len(frames_vis)} (약 {len(frames_vis)/FPS:.1f}초)")
        max_movement = 0
        avg_movement = 0

        if len(features_data) >= WINDOW_SIZE:
            recent_features = features_data[-WINDOW_SIZE:]
            changes = []
            for i in range(1, len(recent_features)):
                prev = np.array(recent_features[i-1]).reshape(-1, 2)
                curr = np.array(recent_features[i]).reshape(-1, 2)
                distances = np.linalg.norm(curr - prev, axis=1)
                head = np.mean(distances[0:3])
                body = np.mean(distances[4:8])
                legs = np.mean(distances[8:16])
                tail = np.mean(distances[16:])
                changes.append({ 'max': np.max(distances), 'avg': np.mean(distances), 'head': head, 'body': body, 'legs': legs, 'tail': tail })

            max_movement = np.max([c['max'] for c in changes])
            avg_movement = np.mean([c['avg'] for c in changes])
            head_avg = np.mean([c['head'] for c in changes])
            body_avg = np.mean([c['body'] for c in changes])
            legs_avg = np.mean([c['legs'] for c in changes])
            tail_avg = np.mean([c['tail'] for c in changes])

        movement_description = f"머리: {head_avg:.2f}, 몸통: {body_avg:.2f}, 다리: {legs_avg:.2f}, 꼬리: {tail_avg:.2f}"
        analysis_prompt = summarize_action(max_movement, avg_movement, movement_description)

        # 비디오 생성 및 Gemini로 전송
        video_path = encode_frames_to_video(frames_vis)
        if not video_path:
            print("⚠️ 비디오 생성 실패")
            return 1, "비디오 인코딩 실패"

        print(f"📤 비디오 파일을 Gemini로 전송 중...")
        
        try:
            # 비디오 파일 업로드
            video_file = genai.upload_file(path=video_path)
            
            # 비디오 처리 상태 확인
            while video_file.state.name == "PROCESSING":
                print("⏳ 비디오 처리 중...")
                time.sleep(2)
                video_file = genai.get_file(video_file.name)

            if video_file.state.name == "FAILED":
                raise ValueError(f"비디오 처리 실패: {video_file.state.name}")

            print("✅ 비디오 처리 완료")

            # Gemini 분석 요청
            prompt_with_time = analysis_prompt + f" (분석시간:{datetime.now()})"
            
            # 프롬프트 저장
            os.makedirs("gemini_input", exist_ok=True)
            timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
            with open(f"gemini_input/prompt_{timestamp}.txt", "w", encoding="utf-8") as f:
                f.write(prompt_with_time)
            
            # 비디오와 프롬프트로 분석 요청
            resp = LLM.generate_content([video_file, prompt_with_time])
            
            if not resp:
                print("⚠️ 응답 없음")
                return 1, "분석 실패"
                
            text = resp.text.strip()
            print("\n✅ Gemini 응답:", text)
            
            # Gemini 응답 저장
            with open(f"gemini_input/response_{timestamp}.txt", "w", encoding="utf-8") as f:
                f.write(text)

            stage_match = re.search(r'(\d+)단계', text)
            stage = int(stage_match.group(1)) if stage_match else 1

            return stage, text

        finally:
            # 임시 비디오 파일 삭제
            try:
                if os.path.exists(video_path):
                    os.remove(video_path)
            except Exception as e:
                print(f"⚠️ 임시 파일 삭제 실패: {e}")

    except Exception as e:
        print("❌ Gemini 오류:", str(e))
        return 1, f"오류: {str(e)}"

def process_video():
    print("\n🎥 비디오 처리 시작...")
    
    video_path = str(PROJECT_ROOT / VIDEO_PATH)
    if not os.path.exists(video_path):
        print(f"❌ 비디오 파일을 찾을 수 없습니다: {video_path}")
        return
        
    cap = cv2.VideoCapture(video_path)
    if not cap.isOpened():
        print("❌ 비디오 열기 실패")
        return

    # 비디오 정보 확인
    total_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    video_fps = cap.get(cv2.CAP_PROP_FPS)
    video_duration = total_frames / video_fps
    
    print(f"\n📊 비디오 정보:")
    print(f"- 총 프레임: {total_frames}")
    print(f"- FPS: {video_fps:.1f}")
    print(f"- 길이: {video_duration:.1f}초")
    
    # 짧은 영상 감지 및 파라미터 조정
    if video_duration < 10:  # 10초 미만 영상
        print("\n⚠️ 짧은 영상 감지 - 파라미터 조정")
        global FRAME_SKIP, WINDOW_SIZE, DETECTION_WINDOWS, GEMINI_BUFFER_SECONDS
        FRAME_SKIP = 2
        WINDOW_SIZE = 3
        DETECTION_WINDOWS = 2
        GEMINI_BUFFER_SECONDS = min(3, video_duration * 0.5)  # 영상 길이의 절반 또는 3초
        print(f"- 프레임 스킵: {FRAME_SKIP}")
        print(f"- 윈도우 크기: {WINDOW_SIZE}")
        print(f"- 감지 윈도우: {DETECTION_WINDOWS}")
        print(f"- 분석 시간: {GEMINI_BUFFER_SECONDS:.1f}초")

    # 버퍼 초기화
    features = []          # 특징점 데이터
    frames_vis = []        # 제미니 분석용 원본 프레임
    frames_display = []    # 화면 표시용 프레임
    abnormal_windows = []
    continuous_detection = False
    last_analysis_time = time.time()
    frame_count = 0
    
    # 중요 분석 결과 저장
    important_analyses = []  # (심각도, 시간, 내용) 튜플 리스트
    max_severity = 0
    max_severity_text = ""

    cv2.namedWindow("Dog Pose Detection", cv2.WINDOW_NORMAL)

    try:
        while True:
            ret, frame = cap.read()
            if not ret:
                # 마지막 프레임에서 남은 데이터 분석
                if len(frames_vis) > WINDOW_SIZE:
                    print("\n🔍 영상 종료 - 최종 분석 실행")
                    stage, summary = analyze_with_gemini(len(frames_vis), features, frames_vis)
                    
                    # 분석 결과 저장
                    timestamp = datetime.now().strftime("%H:%M:%S")
                    important_analyses.append((stage, timestamp, summary))
                    if stage > max_severity:
                        max_severity = stage
                        max_severity_text = summary
                print("\n🎬 비디오 종료")
                break

            if frame is None:
                continue

            frame_count += 1
            if frame_count % FRAME_SKIP != 0:
                continue

            # YOLO 분석
            result = yolo.predict(frame, conf=0.7, verbose=False)[0]
            vis = result.plot()

            if result.keypoints is not None and len(result.keypoints.data) > 0:
                kpts = result.keypoints.data[0]
                if kpts.shape[0] == 24:
                    # 키포인트 추출 및 특징점 계산
                    pelvis = kpts[14][:2]
                    l_sh, r_sh = kpts[11][:2], kpts[12][:2]
                    scale = np.linalg.norm(l_sh - r_sh) or 1.0
                    
                    feat = []
                    for x, y, v in kpts:
                        feat += [(x - pelvis[0]) / scale, (y - pelvis[1]) / scale] if v >= 0.5 else [0.0, 0.0]
                    
                    # 버퍼 관리
                    features.append(feat)
                    frames_vis.append(frame.copy())
                    frames_display.append(vis.copy())
                    
                    # 버퍼 크기 관리 - 최소 필요 프레임 수 확보
                    min_required_frames = int(FPS * GEMINI_BUFFER_SECONDS / FRAME_SKIP)
                    max_frames = min_required_frames + 10  # 여유분

                    # 이상행동 감지
                    if len(features) >= WINDOW_SIZE:
                        X = np.array(features[-WINDOW_SIZE:])
                        if_model.fit(X)
                        lof_model.fit(X)
                        combined = (z_norm(if_model.decision_function(X)) + z_norm(lof_model.decision_function(X))) / 2.0
                        threshold = combined.mean() - STD_MULTIPLIER * combined.std()
                        is_abnormal = combined[-1] < threshold

                        abnormal_windows.append(is_abnormal)
                        if len(abnormal_windows) > DETECTION_WINDOWS:
                            abnormal_windows.pop(0)

                        # 이상행동 체크 및 분석
                        current_time = time.time()
                        enough_frames = len(frames_vis) >= min_required_frames
                        if (sum(abnormal_windows) >= (DETECTION_WINDOWS - ALLOWED_NORMAL) and 
                            not continuous_detection and 
                            enough_frames and
                            current_time - last_analysis_time >= GEMINI_BUFFER_SECONDS):
                            
                            continuous_detection = True
                            print(f"\n🔍 행동 분석 시작 (프레임 수: {len(frames_vis)})")
                            
                            stage, summary = analyze_with_gemini(len(frames_vis), features, frames_vis)
                            
                            # 분석 결과 저장
                            timestamp = datetime.now().strftime("%H:%M:%S")
                            
                            # 중요 키워드가 포함되어 있거나 심각도가 높은 경우 저장
                            has_critical_keyword = any(keyword in summary for keyword in CRITICAL_KEYWORDS)
                            if has_critical_keyword or stage >= 2:
                                important_analyses.append((stage, timestamp, summary))
                            
                            # 최대 심각도 업데이트
                            if stage > max_severity:
                                max_severity = stage
                                max_severity_text = summary
                            
                            features.clear()
                            frames_vis.clear()
                            frames_display.clear()
                            abnormal_windows.clear()
                            continuous_detection = False
                            last_analysis_time = current_time

                    # 버퍼가 너무 커지면 오래된 프레임 제거
                    if len(features) > max_frames:
                        features.pop(0)
                        frames_vis.pop(0)
                        frames_display.pop(0)

            # 화면 표시
            if len(frames_display) > 0:
                cv2.imshow("Dog Pose Detection", frames_display[-1])
            else:
                cv2.imshow("Dog Pose Detection", vis)

            if cv2.waitKey(1) & 0xFF == ord('q'):
                print("\n👋 분석 종료")
                if important_analyses:
                    print_final_analysis(important_analyses, max_severity, max_severity_text)
                break

    finally:
        if important_analyses:
            print_final_analysis(important_analyses, max_severity, max_severity_text)
        cap.release()
        cv2.destroyAllWindows()

def print_final_analysis(important_analyses, max_severity, max_severity_text):
    severity_emoji = {0: "✅", 1: "⚠️", 2: "🚨", 3: "⛔"}
    severity_text = {0: "정상", 1: "경미", 2: "주의", 3: "위험"}
    
    print(f"\n📊 최종 분석 요약")
    print("=" * 40)
    
    # 1. 최고 심각도 상황
    print(f"\n{severity_emoji[max_severity]} 가장 위험한 상황: {severity_text[max_severity]}단계")
    print(f"💬 {max_severity_text}")
    
    # 2. 주요 위험 증상 (상위 3개만)
    keyword_counts = {}
    for _, _, content in important_analyses:
        for keyword in CRITICAL_KEYWORDS:
            if keyword in content:
                keyword_counts[keyword] = keyword_counts.get(keyword, 0) + 1
    
    if keyword_counts:
        print("\n🚨 주요 발견 증상:")
        # 빈도수 기준 상위 3개만 표시
        sorted_keywords = sorted(keyword_counts.items(), key=lambda x: x[1], reverse=True)[:3]
        for keyword, count in sorted_keywords:
            print(f"- {keyword}: {count}회")
    
    # 3. 행동 분석 및 소견
    print("\n📋 행동 분석 및 소견:")
    
    # 가장 최근의 심각한 행동들 수집 (심각도 2 이상)
    critical_behaviors = []
    for stage, _, content in sorted(important_analyses, key=lambda x: x[1], reverse=True):
        if stage >= 2:
            # 문장 단위로 분리하여 첫 문장만 사용
            first_sentence = content.split('.')[0].strip()
            if first_sentence and len(first_sentence) > 5:  # 의미 있는 문장인지 확인
                critical_behaviors.append(first_sentence)
                if len(critical_behaviors) >= 2:  # 최대 2개의 주요 행동만 표시
                    break
    
    # 행동 분석 표시
    if critical_behaviors:
        print("발견된 주요 이상행동:")
        for behavior in critical_behaviors:
            print(f"- {behavior}")
    
    # 권장 사항
    print("\n권장 사항:")
    if max_severity >= 3:
        print("⛔ 응급 진료가 필요합니다.")
    elif max_severity >= 2:
        print("❗ 수의사 상담이 권장됩니다.")
    elif max_severity >= 1:
        print("⚠️ 지속적인 관찰이 필요합니다.")
    else:
        print("✅ 특별한 이상이 없습니다.")
    
    print("=" * 40)

if __name__ == "__main__":
    process_video()
