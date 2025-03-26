-- 8. DML
-- INSERT : 값을 추가
-- INSERT INTO 테이블명(컬럼리스트) VALUES (값1,값2...)
-- 컬럼리스트를 따로 작성하지 않을 경우, 전체 컬럼에 값을 넣겠다는 의미!
INSERT INTO 직원
VALUES(1,'수지','심','SJ1234','010-123-1223',SYSDATE,'IT_PROG',5000,0.23,NULL,1);

-- ORA-02291: integrity constraint (HR.직원_부서아이디_FK) violated - parent key not found
-- 부모테이블에 없는 값을 입력했기 때문에 참조가 불가능해서 나는 오류
-- 부모테이블에 값 추가하기
INSERT INTO 부서(부서아이디,부서이름) VALUES(1,'CX팀');
SELECT * FROM 부서;
INSERT INTO 부서 VALUES(2,'4TENTIAL',NULL,NULL);

SELECT*FROM 직원;

INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디)
    VALUES(2,'지영','손','JIYEONG',SYSDATE,'IT_PROG');

-- ORA-00001: unique constraint (HR.직원_직원아이디_PK) violated
-- PK = UK+NN이라서, 2번은 이미 삽입되어있는 값이라서 직원ID 중복된 값으로 오류발생!
INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디)
    VALUES(2,'보경','서','BOGYEON',SYSDATE,'IT_PROG');

-- ORA-02290: check constraint (HR.직원_급여_MIN) violated
-- 급여>0 제약조건 위배
INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디,급여)
    VALUES(3,'보경','서','BOGYEON',SYSDATE,'IT_PROG',-1);

-- 테이블 컬럼의 크기를 증가!
ALTER TABLE 직원 MODIFY 이름 VARCHAR2(24);
INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디)
    VALUES(3,'보경보경보경보경','서','BOGYEON',SYSDATE,'IT_PROG');

-- SQL 오류: ORA-00904: "직원아이디": invalid identifier
-- 데이터 타입이 맞지 않을 때 나타나는 오류!    
INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디)
    VALUES('4','도연','이','DOYEON',SYSDATE,'IT_PROG');
    
INSERT INTO 직원(직원ID,이름,성,이메일,입사일,직무아이디)
    VALUES(4,'도연','이','DOYEON',SYSDATE,'IT_PROG');

COMMIT; -- 데이터를 테이블에 영구저장
    
-- UPDATE : 데이터 수정할 때 사용
-- UPDATE 테이블명 SET 변경할 컬럼 = 변경할 값 WHERE 조건식;
UPDATE 직원 SET 핸드폰='010-124-5678';
-- 전체 같은 값으로 업데이트 됨

ROLLBACK; -- 복원(마지막 COMMIT 한 시점)

UPDATE 직원 SET 핸드폰='010-123-4567' WHERE 직원ID=2;

-- DELETE : 데이터 삭제할 때 사용
-- DELETE FROM 테이블명 WHRER 조건식;
COMMIT;

DELETE FROM 직원;

DELETE FROM 직원 WHERE 직원ID=3;
ROLLBACK;

-- DML언어는 자동으로 영구반영X -> 꼭 COMMIT을 해줘야 한다!
-- DDL언어는 자동으로 COMMIT -> 복구불가