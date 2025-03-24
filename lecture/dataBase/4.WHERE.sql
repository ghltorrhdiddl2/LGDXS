-- 4. WHERE절 
-- 직무가 IT_PROG인 직원들의 정보를 출력
select * from employees where job_id='IT_PROG';

-- 산술연산자
select salary, salary+2, salary-2, salary*2, salary/2 from employees;

-- where절 실습
select employee_id, first_name, last_name from employees where employee_id=105;

-- 비교 연산자
select first_name,salary from employees where salary<=5000;
select first_name,salary,salary*12 as "AnnSal" from employees where salary*12<=50000 
order by salary desc;

-- 등가 비교 연산자(=, !=, ^=, <>, NOT)
select first_name, job_id from employees where job_id <> 'FI_ACCOUNT';
select first_name, salary from employees where salary >= 10000;
select first_name, job_id from employees where not job_id = 'FI_ACCOUNT';
select first_name, salary from employees where not salary < 10000;

-- 논리연산자 and, or
select employee_id, first_name from employees where department_id=90 and salary>=5000;

select first_name, hire_date, department_id from employees where department_id=100 or hire_date >'2016/02/02';

select employee_id,first_name,salary,department_id from employees where (department_id=100 or department_id=90) and salary*12>=10000;
select employee_id,first_name,salary,department_id from employees where department_id in (100,90) and salary*12>=10000;

select employee_id,first_name,salary*12 as "AnnSal" from employees where department_id in (100,90) and employee_id=101;

-- IS NULL, IS NOT NULL
select employee_id,phone_number from employees where phone_number is null;
select first_name,commission_pct from employees where commission_pct IS NOT NULL;

-- IN 연산자
select employee_id,department_id from employees where department_id in (30,50,90);

-- NOT IN 연산자
select employee_id,department_id from employees where department_id not in (30,50,90);
select employee_id,department_id from employees where department_id not in (30,50,90, null);
-- -> and 연산자로 null이 들어가므로 결과값이 없다

-- BETWEEN 연산자 -> 범위조건 연산자
-- BETWEEN A AND B -> A이상 B이하
select employee_id from employees where salary BETWEEN 3000 and 3999;

-- 2005년에 입사한 직원들
select first_name,hire_date from employees where hire_date BETWEEN '2005/01/01' and '2005/12/31';

-- Like연산자
-- 이름이 S로 시작하는 직원들
select first_name from employees where first_name like 'S%';
-- 이름의 끝이 s로 끝나는 직원들
select first_name from employees where first_name like '%s';
-- _(언더바) : 문자 대체
-- 직원아이디가 100번대인 직원들
select employee_id from employees where employee_id like '1__';

select employee_id, phone_number from employees where phone_number like '650%';  --45
select first_name from employees where first_name like 'S%n'; --4
select first_name from employees where first_name like '_e%'; --12
select employee_id, hire_date from employees where hire_date like '__%01%__'; --14

