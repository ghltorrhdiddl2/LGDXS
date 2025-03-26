-- 7. DDL
-- EMPLOYEES -> 직원
-- 테이블 정보 확인 -> 드래그 상태에서 SHIFT+F4 

CREATE TABLE 직원(
    직원아이디 NUMBER(6) NOT NULL
    , 이름 VARCHAR2(20)
    , 성 VARCHAR2(25) NOT NULL
    , 이메일 VARCHAR2(25) NOT NULL
    , 핸드폰 VARCHAR2(20)
    , 입사일 DATE NOT NULL
    , 직무아이디 VARCHAR2(10) NOT NULL
    , 급여 NUMBER(8,2)
    , 성과비율 NUMBER(2,2)
    , 매니저아이디 NUMBER(6)
    , 부서아이디 NUMBER(4)
);

-- departments
CREATE TABLE 부서(
    부서아이디 NUMBER(4) NOT NULL
    , 부서이름 VARCHAR2(30) NOT NULL
    , 매니저아이디 NUMBER(6)
    , 지역아이디 NUMBER(4)
);


-- 제약조건 : 테이블에 입력가능한 데이터를 제약한 조건들
-- Primary ky(PK) : NOT NULL + UNIQUE 중복불가, NULL값 불가
-- UNIQUE KEY(UK) : 중복불가, NULL 허용
-- NOT NULL(NN) : NULL불가
-- CHECK(CK) : 지정한 데이터만 입력 가능
-- FOREIGN KEY(FK) : 외래키, 다른 테이블의 기본키를 참조해서 사용하는 키

-- PK, UK, CK
-- ALTER TABEL 테이블명 ADD CONSTRAINT 제약조건명 제약조건(컬럼);
ALTER TABLE 직원 ADD CONSTRAINT 직원_직원아이디_PK PRIMARY KEY(직원아이디);
ALTER TABLE 직원 ADD CONSTRAINT 직원_이메일_UK UNIQUE(이메일);
ALTER TABLE 직원 ADD CONSTRAINT 직원_급여_MIN CHECK(급여>0);

-- FK
-- ALTER TABEL 테이블명 ADD CONSTRAINT 제약조건명 제약조건(컬럼) REFERENCES 참조할테이블(참조할컬럼);
ALTER TABLE 직원 ADD CONSTRAINT 직원_부서아이디_FK FOREIGN KEY(부서아이디) REFERENCES 부서(부서아이디);

-- ORA-02270: no matching unique or primary key for this column-list
-- 참조하고자 하는 부서아이디가 PK가 아니기 때문에 참조가 불가능 -> 부서아이디 먼저 PK 설정
ALTER TABLE 부서 ADD CONSTRAINT 직원_부서아이디_PK PRIMARY KEY(부서아이디);

-- FK 삭제 옵션
-- ON DELETE CASCADE : 부모테이블의 값을 삭제하면 자식 테이블의 값도 연동해서 삭제
-- ON DELETE SET NULL : 부모테이블의 값을 삭제하면 자식 테이블의 값이 NULL로 변경
ALTER TABLE 직원 ADD CONSTRAINT 직원_부서아이디_FK FOREIGN KEY(부서아이디) REFERENCES 부서(부서아이디) ON DELETE CASCADE;
ALTER TABLE 직원 ADD CONSTRAINT 직원_부서아이디_FK FOREIGN KEY(부서아이디) REFERENCES 부서(부서아이디) ON DELETE SET NULL;

-- NOT NULL 변경
-- 제약조건 수정은 불가능! 삭제하고 다시 만들거나 컬럼 자체를 수정
ALTER TABLE 직원 MODIFY 이름 NOT NULL;

-- 제약조건 삭제는 가능
ALTER TABLE 직원 DROP CONSTRAINT SYS_C007017;

-- 제약조건 생성(테이블 생성 시 VER1) -> 컬럼라인에서 생성
-- 제약조건 이름을 시스템이 자동생성, 대신 나중에 검색이 불편 SYS_C007017
CREATE TABLE 제약조건테스트1(
    PK NUMBER PRIMARY KEY
    , UK VARCHAR2(1) UNIQUE
    , NN NUMBER NOT NULL
    , CK NUMBER CHECK(CK>0)
    , CK2 VARCHAR2(4) CHECK(CK2 IN ('남','여'))
);

-- 테이블 생성 시 VER2
CREATE TABLE 제약조건테스트2(
    PK NUMBER
    , UK VARCHAR2(1)
    , NN NUMBER
    , CK NUMBER
    , CK2 VARCHAR2(4)
    , FK NUMBER
    -- CONSTRAINT 제약조건이름 제약조건(컬럼)
    , CONSTRAINT 테2_PK_PK PRIMARY KEY(PK)
    , CONSTRAINT 테2_UK_UK UNIQUE(UK)
    , CONSTRAINT 테2_NN_NN CHECK(NN IS NOT NULL)
    , CONSTRAINT 테2_CK_MIN CHECK(CK>0)
    , CONSTRAINT 테2_FK_FK FOREIGN KEY(FK) REFERENCES 제약조건테스트1(PK) ON DELETE SET NULL
);


-- ALTER : 테이블 수정할 때 사용하는 명령어
-- 컬럼 추가
ALTER TABLE 직원 ADD 나이 NUMBER;
SELECT * FROM 직원;
-- 컬럼 삭제
ALTER TABLE 직원 DROP COLUMN 나이;
-- 컬럼 속성 변경
ALTER TABLE 직원 MODIFY 이름 VARCHAR2(40);
-- 컬럼 이름 변경
ALTER TABLE 직원 RENAME COLUMN 직원아이디 TO 직원ID;


-- DROP TABLE 
DROP TABLE 직원;