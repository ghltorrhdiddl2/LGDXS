-- 9.서브쿼리
-- SQL 내부에서 사용되는 SQL문장
select first_name, salary from employees 
where salary < (select salary from employees where first_name='Shelli')
order by 2 desc;

select first_name, hire_date from employees
where hire_date < (select hire_date from employees where first_name='Nancy')
order by 2 desc;

select first_name, hire_date from employees
where hire_date < (select hire_date from employees where first_name='David')
order by 2 desc;
-- ORA-01427: single-row subquery returns more than one row
select hire_date from employees where first_name='David';
-- David는 동명이인 세 명이라 오류가 난다
-- 단일행 서브쿼리 : 결과가 단 하나의 행으로 나오는 서브쿼리, 단일행 연산자와 비교해서 사용(=,>=,!= . .)

-- 평균 급여보다 높은 급여를 받는 직원 조회
select first_name from employees
where salary > (select avg(salary) from employees);

-- 다중행 서브쿼리 : 결과가 여러개의 행으로 나오는 서브쿼리
-- 다중행 연산자 : IN, ALL, SOME, ANY, EXISTS

-- IN : 서브쿼리의 결과가 메인쿼리의 데이터에 하나라도 일치하면 출력
-- = + OR , 동등성만 평가

-- 부서별 최고급여를 받는 직원과 같은 급여를 받는 직원 출력
-- 1. 부서별 최고급여
SELECT MAX(salary) FROM employees GROUP BY department_id;

-- 2. 1번의 결과와 같은 급여를 받는 직원들을 출력
SELECT first_name,salary from employees 
where salary in (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--3. ANY, SOME : 메인쿼리의 조건식을 만족하는 서브쿼리의 결과가 하나 이상이면 TRUE로 화면에 출력
-- IN연산자는 동등성만 평가, ANY/SOME 연산자는 대소관계 비교도 가능
-- ANY, SOME 연산자에 = 연산자를 같이 사용하면 IN연산자와 같은 기능
-- ANY, SOME은 OR연산자의 기능!
SELECT first_name,salary from employees 
where salary = SOME (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

SELECT first_name,salary from employees 
where salary = ANY (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

-- 대소관계 파악
-- 부서별 최대급여보다 많이 받는 사람 조회
-- 부서별 최대급여 중 가장 낮은 급여인 4400과 비교를 했을 때 조건에 만족하는 경우 화면에 출력
SELECT first_name,salary from employees 
where salary >= SOME (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--4. ALL 연산자(AND) : 메인쿼리의 조건식을 서브쿼리의 결과가 모두 만족하면 TRUE
-- = 연산자를 쓰게 되면 부서별 최고 급여와 모두 같은 값은 출력
-- SALARY = 4400 AND SLALRY = 13000 AND ....
SELECT first_name,salary from employees 
where salary = ALL (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

SELECT first_name,salary from employees 
where salary >= ALL (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--5. 다중열 서브쿼리
-- 부서별 최고 급여를 받는 직원
SELECT first_name,department_id,salary FROM employees
WHERE (department_id, salary) 
IN (SELECT department_id,MAX(salary) FROM employees GROUP BY department_id)
ORDER BY 2; 


--6. FROM절 서브쿼리(인라인뷰)
-- 부서에서 최고급여를 받는 직원 조회
SELECT first_name, DMAX.department_id, DMAX."부서별 최고급여" FROM employees e
    , (SELECT department_id,MAX(salary) as "부서별 최고급여" FROM employees GROUP BY department_id) DMAX
WHERE e.department_id = DMAX.department_id AND e.salary=DMAX."부서별 최고급여";

SELECT first_name, DMAX.department_id, DMAX."부서별 최고급여" FROM employees e
    join (SELECT department_id,MAX(salary) as "부서별 최고급여" FROM employees GROUP BY employees) DMAX
    on e.department_id = DMAX.department_id AND e.salary=DMAX."부서별 최고급여";


-- SELECT절 서브쿼리(스칼라 서브쿼리)
-- JOIN없이 JOIN처럼 사용하고자 할 때 사용
-- 각 직원의 부서 이름을 출력
SELECT employee_id, department_id
    , (SELECT department_name FROM departments WHERE department_id=e.department_id) AS 부서이름
FROM employees e;


SELECT department_name FROM departments;