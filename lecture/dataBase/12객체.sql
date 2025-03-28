-- 12. 객체
-- VIEW 뷰 : 가상의 테이블, 하나 이상의 테이블을 조회하는 SELECT문을 저장한 객체
-- 부서별 최고급여 뷰를 만들고, 해당 뷰를 사용해서 부서별로 최고급여를 받는 직원의 정보를 출력
-- 1. 뷰 만들기
CREATE VIEW 부서별최고급여 
AS SELECT DEPARTMENT_ID, MAX(SALARY) AS 최고급여 FROM employees
    GROUP BY department_id
    ORDER BY department_id;

SELECT * FROM 부서별최고급여;

-- 2. 부서별로 최고급여를 받는 직원의 정보(뷰 사용)
CREATE VIEW 최고급여직원
AS SELECT FIRST_NAME, SALARY, 최고급여 FROM employees e, 부서별최고급여 DMAX
WHERE e.department_id=dmax.department_id AND e.salary=dmax.최고급여;

SELECT * FROM 최고급여직원;


-- 시퀀스 : 대기순번표처럼 특정 규칙에 맞는 연속 숫자를 생성하는 객체
CREATE TABLE 시퀀스테이블(
    시퀀스 NUMBER
);

CREATE SEQUENCE NUM1;

INSERT INTO 시퀀스테이블 VALUES(NUM1.NEXTVAL);
SELECT * FROM 시퀀스테이블;

SELECT NUM1.CURRVAL FROM DUAL;  -- DUAL: 더미테이블


CREATE SEQUENCE NUM2
START WITH 100 INCREMENT BY 2;

INSERT INTO 시퀀스테이블 VALUES(NUM2.NEXTVAL);
SELECT * FROM 시퀀스테이블;

DROP SEQUENCE NUM2;


-- ROWMUN : 테이블의 행에 임시로 부여되는 일련의 번호(행의 개수를 제한)
-- 반드시 1부터 사용, 6~10까지 불가능
SELECT ROWNUM, employee_id FROM employees WHERE ROWNUM<=5;

SELECT ROWNUM, employee_id FROM employees WHERE ROWNUM=5; -- 결과 안나옴
-- 인라인뷰를 활용하면 특정 ROWNUM 지정 가능
SELECT * 
FROM (SELECT ROWNUM AS RN, employee_id FROM employees WHERE ROWNUM<=5)
WHERE RN=5;


-- 급여가 높은 순서대로 직원을 출력(NULL 제외)
SELECT first_name, salary FROM employees
WHERE salary IS NOT NULL AND ROWNUM<=5
ORDER BY salary DESC;

SELECT * 
FROM (SELECT first_name, salary FROM employees WHERE salary IS NOT NULL
    ORDER BY salary DESC)
WHERE ROWNUM<=5;

-- VIEW로 만들어보기
CREATE VIEW 급여내림차순 
AS SELECT first_name, salary FROM employees WHERE salary IS NOT NULL
    ORDER BY salary DESC;
    
SELECT * FROM 급여내림차순
WHERE ROWNUM<=5;

-- 페이징 기술, N만 채우기
SELECT * FROM (SELECT ROWNUM AS RN, 게시글.*
                FROM (SELECT * FROM 게시판 ORDER BY 게시판번호) 게시글
                WHERE ROWNUM <= 20*N)
WHERE RN >= 20*(N-1)+1;