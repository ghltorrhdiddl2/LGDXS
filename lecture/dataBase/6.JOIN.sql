-- 6. JOIN
-- 직원 아이디가 100인 직원의 부서이름 출력
SELECT d.DEPARTMENT_NAME FROM employees e JOIN departments d ON e.DEPARTMENT_ID=d.DEPARTMENT_ID
WHERE e.EMPLOYEE_ID=100;

-- JOIN : 여러개의 테이블을 연결해서 사용
-- 필요한 데이터가 2개 이상의 테이블에 나눠져 있을 때 한 번에 조회하는 방법
SELECT E.EMPLOYEE_ID, D.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM employees E, departments D
WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND E.EMPLOYEE_ID=100;

-- FROM절
-- 테이블 여러개 사용가능+별칭 사용 가능
-- 별칭을 사용한 이후에는 별칭만 사용 가능! (SELECT, WHERE 절 등에서)
-- 테이블이 여러개인 경우, 어떤 테이블의 컬럼인지 정확하게 명시