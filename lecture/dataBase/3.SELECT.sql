-- �ּ�
-- SQL������ ��ҹ��ڸ� ����X
-- ���⳪ �ٹٲ��� ��ɾ� ���࿡ ����X
-- SQL������ ������ �� ;(�����ݷ�) ���
-- ���� CTRL+ENTER, F9

-- 1. ��ü �÷� ����ϱ�
SELECT * FROM employees;

-- �μ��� ��ü�÷� ���
select * from departments;

-- 2. ���ϴ� �÷� ����ϱ�
select department_id, first_name, hire_date from employees;

-- �μ��� �μ� ID, �μ� �̸�, �ٹ���ID ���
select department_id, department_name, location_id from departments;

-- 3. �ߺ�����
select DISTINCT department_id from employees;

-- �÷��� �������� ���
select DISTINCT department_id, job_id from employees order by 1;

-- �ǽ�
select count(hire_date) from employees;
select count(DISTINCT hire_date) from employees;

-- 4. ��Ī���� ����ϱ�
-- "" : ���� �� �� ���
select employee_id as �������̵�, employee_id "�������̵�", employee_id �������̵�
    , employee_id as "�������̵�"
from employees;

-- �ǽ�
select hire_date as "�Ի���", hire_date+1 as "�Ի� ������" from employees;

-- ���� �������� �߰�
insert into employees(employee_id, first_name, last_name, email, hire_date, job_id)
VALUES (207, '����','��','SOO1111',SYSDATE,'IT_PROG');

-- NULL �ǽ�
select employee_id, salary, salary*12 as AnnSal from employees;