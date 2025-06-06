from fastapi import APIRouter, Depends, HTTPException, Form, UploadFile, File
from sqlalchemy.orm import Session
from datetime import datetime
from typing import Optional
from pydantic import BaseModel
from ..db import SessionLocal, Base
from .. import models
import io

router = APIRouter()

# DB 세션 의존성
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.post("/event")
async def create_event(
    dog_name: str = Form(...),
    timestamp: str = Form(...),
    stage: int = Form(...),
    summary: str = Form(...),
    video_data: Optional[UploadFile] = File(None),
    db: Session = Depends(get_db)
):
    """이상행동 이벤트 저장"""
    try:
        # timestamp를 datetime으로 변환
        event_time = datetime.fromisoformat(timestamp)
        
        # 강아지 존재 여부 확인
        dog = db.query(models.Dog).filter(models.Dog.name == dog_name).first()
        if not dog:
            raise HTTPException(status_code=404, detail="존재하지 않는 강아지입니다.")
        
        # 비디오 데이터 읽기
        video_content = None
        video_name = None
        if video_data:
            # 청크 단위로 파일 읽기
            CHUNK_SIZE = 1024 * 1024  # 1MB 단위로 읽기
            video_buffer = io.BytesIO()
            while chunk := await video_data.read(CHUNK_SIZE):
                video_buffer.write(chunk)
            video_content = video_buffer.getvalue()
            video_name = video_data.filename
            print(f"비디오 데이터 크기: {len(video_content)/1024:.1f}KB")

        # DB에 저장
        db_event = models.Event(
            dog_name=dog_name,
            timestamp=event_time,
            stage=stage,
            summary=summary,
            video_data=video_content,
            video_name=video_name
        )
        db.add(db_event)
        db.commit()
        db.refresh(db_event)
        return {"message": "이벤트가 저장되었습니다.", "id": db_event.id}
        
    except Exception as e:
        print(f"에러 발생: {str(e)}")  # 에러 로깅 추가
        raise HTTPException(status_code=500, detail=str(e))

@router.get("/events")
def get_events(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    """저장된 이벤트 목록 조회"""
    events = db.query(models.Event).order_by(models.Event.timestamp.desc()).offset(skip).limit(limit).all()
    return events

@router.get("/event/{event_id}/video")
def get_event_video(event_id: int, db: Session = Depends(get_db)):
    """특정 이벤트의 비디오 데이터 조회"""
    event = db.query(models.Event).filter(models.Event.id == event_id).first()
    if not event or not event.video_data:
        raise HTTPException(status_code=404, detail="비디오를 찾을 수 없습니다")
    return {
        "video_data": event.video_data,
        "video_name": event.video_name or f"event_{event_id}.mp4"
    } 