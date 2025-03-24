-- 4. WHERE�� 
-- ������ IT_PROG�� �������� ������ ���
select * from employees where job_id='IT_PROG';

-- ���������
select salary, salary+2, salary-2, salary*2, salary/2 from employees;

-- where�� �ǽ�
select employee_id, first_name, last_name from employees where employee_id=105;

-- �� ������
select first_name,salary from employees where salary<=5000;
select first_name,salary,salary*12 as "AnnSal" from employees where salary*12<=50000 
order by salary desc;

-- � �� ������(=, !=, ^=, <>, NOT)
select first_name, job_id from employees where job_id <> 'FI_ACCOUNT';
select first_name, salary from employees where salary >= 10000;
select first_name, job_id from employees where not job_id = 'FI_ACCOUNT';
select first_name, salary from employees where not salary < 10000;

-- �������� and, or
select employee_id, first_name from employees where department_id=90 and salary>=5000;

select first_name, hire_date, department_id from employees where department_id=100 or hire_date >'2016/02/02';

select employee_id,first_name,salary,department_id from employees where (department_id=100 or department_id=90) and salary*12>=10000;
select employee_id,first_name,salary,department_id from employees where department_id in (100,90) and salary*12>=10000;

select employee_id,first_name,salary*12 as "AnnSal" from employees where department_id in (100,90) and employee_id=101;

-- IS NULL, IS NOT NULL
select employee_id,phone_number from employees where phone_number is null;
select first_name,commission_pct from employees where commission_pct IS NOT NULL;

-- IN ������
select employee_id,department_id from employees where department_id in (30,50,90);

-- NOT IN ������
select employee_id,department_id from employees where department_id not in (30,50,90);
select employee_id,department_id from employees where department_id not in (30,50,90, null);
-- -> and �����ڷ� null�� ���Ƿ� ������� ����

-- BETWEEN ������ -> �������� ������
-- BETWEEN A AND B -> A�̻� B����
select employee_id from employees where salary BETWEEN 3000 and 3999;

-- 2005�⿡ �Ի��� ������
select first_name,hire_date from employees where hire_date BETWEEN '2005/01/01' and '2005/12/31';

-- Like������
-- �̸��� S�� �����ϴ� ������
select first_name from employees where first_name like 'S%';
-- �̸��� ���� s�� ������ ������
select first_name from employees where first_name like '%s';
-- _(�����) : ���� ��ü
-- �������̵� 100������ ������
select employee_id from employees where employee_id like '1__';

select employee_id, phone_number from employees where phone_number like '650%';  --45
select first_name from employees where first_name like 'S%n'; --4
select first_name from employees where first_name like '_e%'; --12
select employee_id, hire_date from employees where hire_date like '__%01%__'; --14

