import pymysql
import os
from pathlib import Path

# DB 연결 정보
DB_CONFIG = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': '123456',
    'db': 'dx_model',
    'charset': 'utf8mb4'
}

def apply_migration():
    """SQL 마이그레이션 스크립트 실행"""
    try:
        # DB 연결
        conn = pymysql.connect(**DB_CONFIG)
        cursor = conn.cursor()
        
        # SQL 파일 읽기
        sql_path = Path(__file__).parent / 'recreate_events_table.sql'
        with open(sql_path, 'r', encoding='utf-8') as f:
            sql = f.read()
            
        # SQL 실행
        print("테이블 재생성 중...")
        for statement in sql.split(';'):
            if statement.strip():
                cursor.execute(statement)
        
        # 변경사항 저장
        conn.commit()
        print("✅ 테이블이 성공적으로 재생성되었습니다.")
        
    except Exception as e:
        print(f"❌ 오류 발생: {str(e)}")
        conn.rollback()
        
    finally:
        cursor.close()
        conn.close()

if __name__ == "__main__":
    apply_migration() 