import pymysql

# DB 연결 정보 (데이터베이스 지정하지 않음)
DB_CONFIG = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': '123456',
    'charset': 'utf8mb4'
}

def create_database():
    """새로운 데이터베이스 생성"""
    try:
        # DB 연결
        conn = pymysql.connect(**DB_CONFIG)
        cursor = conn.cursor()
        
        # 데이터베이스 생성
        cursor.execute("CREATE DATABASE IF NOT EXISTS dx_model")
        print("✅ 데이터베이스 dx_model이 생성되었습니다.")
        
    except Exception as e:
        print(f"❌ 오류 발생: {str(e)}")
        
    finally:
        cursor.close()
        conn.close()

if __name__ == "__main__":
    create_database() 