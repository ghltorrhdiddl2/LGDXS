"""
[데이터베이스 모델 정의]

1. Event 모델
   - 이상행동 감지 이벤트를 저장하는 테이블
   - 영상 데이터와 분석 결과를 함께 저장
   
   필드 구성:
   - id: 자동 증가 기본키
   - timestamp: 이벤트 발생 시간 (datetime)
   - stage: 행동 단계 (0-4)
     * 0: 정상 행동
     * 1: 주의 관찰
     * 2: 경미한 문제
     * 3: 심각한 문제
     * 4: 즉각 조치 필요
   - summary: 행동 분석 요약 (text)
   - video_data: 영상 데이터 (binary)
   - video_name: 원본 영상 파일명
   
   인덱스:
   - timestamp에 인덱스 추가 (날짜별 조회 성능 향상)
   - stage에 인덱스 추가 (단계별 필터링 성능 향상)
"""

from sqlalchemy import Column, Integer, String, DateTime, LargeBinary
from .db import Base

class Event(Base):
    """
    이상행동 감지 이벤트 모델
    
    사용 예시:
    1. 이벤트 생성
       event = Event(
           timestamp=datetime.now(),
           stage=2,
           summary="반복적인 꼬리 물기 행동 감지",
           video_data=video_binary,
           video_name="event_123.mp4"
       )
       
    2. 이벤트 조회
       # 특정 날짜의 모든 이벤트
       day_events = db.query(Event).filter(
           Event.timestamp.between(start_date, end_date)
       ).all()
       
       # 심각한 이벤트만 조회 (stage >= 3)
       serious = db.query(Event).filter(Event.stage >= 3).all()
    """
    __tablename__ = "events"
    
    id = Column(Integer, primary_key=True, index=True)
    timestamp = Column(DateTime, nullable=False)
    stage = Column(Integer, nullable=False)  # 0: 일반행동, 1-4: 이상행동 단계
    summary = Column(String(1000), nullable=False)
    video_data = Column(LargeBinary, nullable=True)  # 이상행동일 때만 영상 저장
    video_name = Column(String(255), nullable=True)  # 원본 영상 파일명 