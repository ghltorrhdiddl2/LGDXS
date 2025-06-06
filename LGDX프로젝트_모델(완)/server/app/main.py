# server/app/main.py
import os
import json
import time
import subprocess
from pathlib import Path
from fastapi import FastAPI, Depends, File, UploadFile, Form, HTTPException
from fastapi.responses import JSONResponse
from sqlalchemy.orm import Session
from datetime import datetime, date, timedelta
from sqlalchemy import and_
from .db import SessionLocal, engine, Base
from . import models, crud
from dotenv import load_dotenv
import google.generativeai as genai
from .utils.summary_generator import generate_normal_summary, generate_abnormal_summary

# 환경변수 로드
load_dotenv()  

# Gemini API 설정
genai.configure(api_key=os.getenv("GOOGLE_API_KEY"))
LLM = genai.GenerativeModel(model_name="models/gemini-2.5-flash-preview-05-20")

# 테이블 생성
Base.metadata.create_all(bind=engine)

app = FastAPI()

# 라우터 포함
from .routers import events
app.include_router(events.router)

# detector 프로세스 저장용 변수
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

    # 서버 시작 시 테스트용 강아지 데이터 추가
    db = SessionLocal()
    try:
        # 테스트용 강아지가 없으면 추가
        test_dog = db.query(models.Dog).filter(models.Dog.name == "테스트강아지").first()
        if not test_dog:
            test_dog = models.Dog(
                name="테스트강아지",
                created_at=datetime.now()
            )
            db.add(test_dog)
            db.commit()
            print("✅ 테스트용 강아지 데이터가 추가되었습니다.")
    except Exception as e:
        print(f"❌ 테스트 데이터 추가 실패: {str(e)}")
    finally:
        db.close()

@app.on_event("shutdown")
async def shutdown_event():
    """서버 종료 시 detector 프로세스도 종료"""
    global detector_process
    if detector_process:
        detector_process.terminate()
        detector_process.wait()
        print("🛑 Detector stopped")

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

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

@app.get("/daily-summary/{dog_name}/{date}")
async def get_daily_summary(dog_name: str, date: str, db: Session = Depends(get_db)):
    """특정 날짜의 강아지 행동 요약 조회"""
    try:
        print(f"\n📅 요청된 날짜: {date}")
        print(f"🐕 강아지 이름: {dog_name}")
        
        ########################
        # 강아지 이름 확인 및 pet_name 확보
        dog = db.query(models.Dog).filter(models.Dog.name == dog_name).first()
        if not dog:
            raise HTTPException(status_code=404, detail="존재하지 않는 강아지입니다.")
        pet_name = dog.name  # 또는 dog.pet_name, dog.nickname 등
        #######################

        # 날짜 파싱
        target_date = datetime.strptime(date, "%Y-%m-%d").date()
        start_datetime = datetime.combine(target_date, datetime.min.time())
        end_datetime = datetime.combine(target_date, datetime.max.time())
        
        # 정상 행동 (stage 0) 조회
        normal_events = db.query(models.Event).filter(
            and_(
                models.Event.dog_name == dog_name,
                models.Event.timestamp.between(start_datetime, end_datetime),
                models.Event.stage == 0
            )
        ).all()
        print(f"\n📊 정상 행동 이벤트 수: {len(normal_events)}")
        for e in normal_events:
            print(f"- {e.timestamp}: {e.summary}")
        
        # 이상 행동 (stage 1-3) 조회
        abnormal_events = db.query(models.Event).filter(
            and_(
                models.Event.dog_name == dog_name,
                models.Event.timestamp.between(start_datetime, end_datetime),
                models.Event.stage > 0
            )
        ).all()
        print(f"\n📊 이상 행동 이벤트 수: {len(abnormal_events)}")
        for e in abnormal_events:
            print(f"- Stage {e.stage} ({e.timestamp}): {e.summary}")
        
        # 요약 생성
        print("\n🔄 정상 행동 요약 생성 시작...")
        # normal_summary = generate_normal_summary(normal_events)
        normal_summary = generate_normal_summary(normal_events, pet_name)
        print(f"✅ 정상 행동 요약 완료: {normal_summary}")
        
        print("\n🔄 이상 행동 요약 생성 시작...")
        # abnormal_summary = generate_abnormal_summary(abnormal_events)
        abnormal_summary = generate_abnormal_summary(abnormal_events, pet_name)
        print(f"✅ 이상 행동 요약 완료: {abnormal_summary}")
        
        return {
            # "dog_name": dog_name,
            "dog_name": pet_name,
            "date": date,
            "normal_summary": normal_summary,
            "abnormal_summary": abnormal_summary
        }
        
    except Exception as e:
        print(f"\n❌ 일일 요약 생성 중 오류 발생:")
        print(f"오류 유형: {type(e).__name__}")
        print(f"오류 내용: {str(e)}")
        import traceback
        print(f"상세 정보:\n{traceback.format_exc()}")
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/weekly-summary/{dog_name}")
async def get_weekly_summary(dog_name: str, start_date: str, db: Session = Depends(get_db)):
    """일주일 간의 강아지 행동 요약 조회"""
    try:
        # 시작 날짜 파싱
        start = datetime.strptime(start_date, "%Y-%m-%d").date()
        end = start + timedelta(days=6)
        
        # 해당 기간의 모든 일일 요약 조회
        summaries = db.query(models.DailySummary).filter(
            and_(
                models.DailySummary.dog_name == dog_name,
                models.DailySummary.date.between(start, end)
            )
        ).order_by(models.DailySummary.date).all()
        
        return {
            "dog_name": dog_name,
            "start_date": start_date,
            "end_date": end.strftime("%Y-%m-%d"),
            "summaries": [
                {
                    "date": s.date.strftime("%Y-%m-%d"),
                    "normal_summary": s.normal_summary,
                    "abnormal_summary": s.abnormal_summary
                }
                for s in summaries
            ]
        }
        
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
