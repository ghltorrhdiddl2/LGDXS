-- 10. TCL
-- 트랜잭션 : 업무를 수행하기 위한 최소 수행 단뒤
-- COMMIT : DB에 영구적으로 저장, 마지막 COMMIT 시점 이후의 트랜잭션 결과를 저장
-- ROLLBACK : 트랜잭션을 취소, 마지막 COMMIT 시점까지만 복구 가능

CREATE TABLE 카카오뱅크(
    계좌번호 VARCHAR2(50)
    , 이름 VARCHAR2(12)
    , 잔액 NUMBER
);

INSERT INTO 카카오뱅크 VALUES ('1234-1234','심수지',4000);
INSERT INTO 카카오뱅크 VALUES ('1234-5678','지영',0);

SELECT * FROM 카카오뱅크;
ROLLBACK; -- 첫번째 롤백
-- ROLLBACK을 하니까 방금 INSERT 했던 행이 반영되지 않은 것은 확인!
-- DML(INSERT, UPDATE, DELETE)는 테이블에 바로 영구반영 되지 않는다. COMMIT으로 영구반영 필요

INSERT INTO 카카오뱅크 VALUES ('1234-1234','심수지',4000);
COMMIT; -- 첫번째 커밋
SELECT * FROM 카카오뱅크;
ROLLBACK; -- 두번째 커밋
SELECT * FROM 카카오뱅크;
-- COMMIT을 했기 때문에 ROLLBACK을 해도 마지막 COMMIT시점으로 돌아가서 데이터가 유지됨

-- 송금 트랜잭션(심수지 -> 지영)
-- 1. 심수지의 잔액 확인
SELECT * FROM 카카오뱅크 WHERE 이름 ='심수지';
-- 2. 심수지의 계좌에서 1000원을 차감
UPDATE 카카오뱅크 SET 잔액 = 3000 WHERE 이름='심수지';
-- UPDATE 카카오뱅크 SET 잔액 = 잔액-1000 WHERE 이름='심수지';
-- 3. 지연의 계좌에서 1000원을 추가
UPDATE 카카오뱅크 SET 잔액 = 1000 WHERE 이름='지영';
-- UPDATE 카카오뱅크 SET 잔액 = 잔액 + 1000 WHERE 이름='지영';
-- 4. 지영의 잔액 확인
SELECT * FROM 카카오뱅크;

COMMIT;
ROLLBACK;
SELECT * FROM 카카오뱅크;

-- 읽기 일관성 테스트
-- 트랜잭션이 완료되기 전까지는 데이터를 직접 조작하고 있던 SESSION외에
-- 다른 SESSION에서는 데이터 조작 전 상태를 일괄적으로 조회/출력/검색할 수 있도록 만드는 특성
-- COMMIT된 영구저장된 데이터만 검색 -> 읽이 일관성을 보장
UPDATE 카카오뱅크 SET 잔액 = 0 WHERE 이름='지영';
SELECT * FROM 카카오뱅크;
COMMIT;

-- LOCK
-- 특정 SESSTION에서 조작중인 데이터는 트랜잭션이 완료되기 전까지
-- 다른 SESSION에서 해당 데이터를 조작할 수 없는 상태
UPDATE 카카오뱅크 SET 잔액 = 1000 WHERE 이름='지영';

COMMIT;