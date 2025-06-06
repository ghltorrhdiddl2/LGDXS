# server/app/db.py
"""
[DB 설정 및 연결 관리]

1. 데이터베이스 구성
   - 사용 DB: MySQL 8.0
   - ORM: SQLAlchemy
   - 연결 풀링: 기본 5-10개 연결 유지

2. 주요 테이블
   - events: 이상행동 감지 이벤트 저장
     * id: 자동 증가 기본키
     * timestamp: 이벤트 발생 시간
     * stage: 행동 단계 (0-4)
     * summary: 행동 분석 요약
     * video_data: 영상 데이터
     * video_name: 영상 파일명

3. 환경 설정
   - DATABASE_URL: MySQL 연결 문자열
     형식: mysql+pymysql://user:pass@host:port/dbname
   - POOL_SIZE: 연결 풀 크기 (기본값: 5)
   - MAX_OVERFLOW: 최대 추가 연결 수 (기본값: 10)

4. 연결 관리
   - SessionLocal: 요청별 DB 세션 팩토리
   - engine: SQLAlchemy 엔진 인스턴스
   - Base: 모델 선언용 기본 클래스
"""

import os
from pathlib import Path
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from dotenv import load_dotenv

# ── .env 로드 ────────────────────────────────────────────
project_root = Path(__file__).resolve().parents[2]
load_dotenv(dotenv_path=project_root / ".env")

# ── 데이터베이스 URL ─────────────────────────────────────
DATABASE_URL = "mysql+pymysql://campus_LGDX6_p3_2:smhrd2@project-db-campus.smhrd.com:3307/campus_LGDX6_p3_2"

# ── SQLAlchemy 엔진 및 세션 ───────────────────────────────
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# ── 베이스 클래스 ─────────────────────────────────────────
Base = declarative_base()
