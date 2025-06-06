# server/app/main.py
"""
[FastAPI 서버 구성]
1. 주요 기능
   - 이상행동 감지 이벤트 저장 (/event)
   - 일일 행동 일기 생성 (/diary/{year}/{month}/{day})
   - Detector 프로세스 자동 실행/종료

2. 시스템 구성
   - FastAPI: REST API 서버
   - SQLAlchemy: DB ORM
   - Gemini API: 행동 분석 및 일기 생성
   - Detector: 별도 프로세스로 실행되는 감지 시스템

3. 환경 설정
   - GOOGLE_API_KEY: Gemini API 키
   - DATABASE_URL: DB 연결 정보 (db.py에서 설정)
   - 기타 환경변수는 .env 파일에서 관리
"""

import os
import json
import time
import subprocess
from pathlib import Path
from fastapi import FastAPI, Depends, File, UploadFile, Form, HTTPException
from fastapi.responses import JSONResponse
from sqlalchemy.orm import Session
from datetime import datetime
from .db import SessionLocal, engine, Base
from . import models, crud
from dotenv import load_dotenv
import google.generativeai as genai

# 환경변수 로드
load_dotenv()  

"""
[Gemini API 설정]
- model_name: "models/gemini-2.5-flash-preview-05-20" 사용
- 일기 생성 시에만 사용 (Detector의 분석과는 별개)
- API 키는 환경변수에서 로드
"""
genai.configure(api_key=os.getenv("GOOGLE_API_KEY"))
LLM = genai.GenerativeModel(model_name="models/gemini-2.5-flash-preview-05-20")

# 테이블 생성
Base.metadata.create_all(bind=engine)

app = FastAPI()

# 라우터 포함
from .routers import events
app.include_router(events.router)

"""
[Detector 프로세스 관리]
- 서버 시작 시 자동으로 detector.py 실행
- 서버 종료 시 자동으로 detector 프로세스 종료
- detector_process: 실행 중인 프로세스 객체 저장
"""
detector_process = None

@app.on_event("startup")
async def startup_event():
    """서버 시작 시 detector.py 실행"""
    global detector_process
    project_root = Path(__file__).resolve().parents[2]
    detector_path = project_root / "detector" / "detection.py"
    
    if detector_path.exists():
        detector_process = subprocess.Popen(
            ["python", str(detector_path)],
            cwd=str(project_root)  # 프로젝트 루트에서 실행
        )
        print("🎥 Detector started")

@app.on_event("shutdown")
async def shutdown_event():
    """서버 종료 시 detector 프로세스도 종료"""
    global detector_process
    if detector_process:
        detector_process.terminate()
        detector_process.wait()
        print("🛑 Detector stopped")

"""
[DB 세션 관리]
- 각 요청마다 새로운 DB 세션 생성
- 요청 처리 후 자동으로 세션 닫힘
- SQLAlchemy의 세션 풀링 사용
"""
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

"""
[일기 생성 API]
엔드포인트: /diary/{year}/{month}/{day}
기능:
1. 해당 날짜의 모든 이벤트 조회
2. 일반 행동과 이상 행동 분리
3. Gemini API로 각각 일기 형식 요약 생성
4. 두 가지 일기를 합쳐서 반환

반환 형식:
{
    "date": "YYYY-MM-DD",
    "normal_diary": "일반 행동 일기",
    "abnormal_diary": "이상 행동 일기"
}
"""
@app.get("/diary/{year}/{month}/{day}")
def get_diary(year: int, month: int, day: int, db: Session = Depends(get_db)):
    from sqlalchemy import and_
    start = datetime(year, month, day)
    end   = datetime(year, month, day, 23,59,59)
    events = db.query(models.Event).filter(
        and_(models.Event.timestamp >= start,
             models.Event.timestamp <= end)
    ).all()

    # 일반 행동 모아서 일기 요약
    normals = [e.summary for e in events if e.stage==0]
    if normals:
        prompt_n = "오늘 반려견 일반 행동:\n" + "\n".join(normals) + "\n일기 형식 3문장 요약"
        normal_diary = LLM.generate_content([prompt_n]).text.strip()
    else:
        normal_diary = "오늘은 기록된 일반 행동이 없습니다."

    # 이상 행동 모아서 일기 요약
    abn = [e.summary for e in events if e.stage>=1]
    if abn:
        prompt_a = "오늘 반려견 이상 행동:\n" + "\n".join(abn) + "\n일기 형식 3문장 요약"
        abnormal_diary = LLM.generate_content([prompt_a]).text.strip()
    else:
        abnormal_diary = "오늘은 기록된 이상 행동이 없습니다."

    return {
        "date": f"{year:04d}-{month:02d}-{day:02d}",
        "normal_diary": normal_diary,
        "abnormal_diary": abnormal_diary
    }
