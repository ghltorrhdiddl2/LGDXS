-- 주석
-- SQL문법은 대소문자를 구분X
-- 띄어쓰기나 줄바꿈은 명령어 실행에 영향X
-- SQL문장의 끝에는 꼭 ;(세미콜론) 찍기
-- 실행 CTRL+ENTER, F9

-- 1. 전체 컬럼 출력하기
SELECT * FROM employees;

-- 부서의 전체컬럼 출력
select * from departments;

-- 2. 원하는 컬럼 출력하기
select department_id, first_name, hire_date from employees;

-- 부서의 부서 ID, 부서 이름, 근무지ID 출력
select department_id, department_name, location_id from departments;

-- 3. 중복제거
select DISTINCT department_id from employees;

-- 컬럼이 여러개인 경우
select DISTINCT department_id, job_id from employees order by 1;

-- 실습
select count(hire_date) from employees;
select count(DISTINCT hire_date) from employees;

-- 4. 별칭으로 출력하기
-- "" : 띄어쓰기 할 때 사용
select employee_id as 직원아이디, employee_id "직원아이디", employee_id 직원아이디
    , employee_id as "직원아이디"
from employees;

-- 실습
select hire_date as "입사일", hire_date+1 as "입사 다음날" from employees;

-- 나를 직원으로 추가
insert into employees(employee_id, first_name, last_name, email, hire_date, job_id)
VALUES (207, '수지','심','SOO1111',SYSDATE,'IT_PROG');

-- NULL 실습
select employee_id, salary, salary*12 as AnnSal from employees;