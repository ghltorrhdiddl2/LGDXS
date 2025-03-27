-- 9.��������
-- SQL ���ο��� ���Ǵ� SQL����
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
-- David�� �������� �� ���̶� ������ ����
-- ������ �������� : ����� �� �ϳ��� ������ ������ ��������, ������ �����ڿ� ���ؼ� ���(=,>=,!= . .)

-- ��� �޿����� ���� �޿��� �޴� ���� ��ȸ
select first_name from employees
where salary > (select avg(salary) from employees);

-- ������ �������� : ����� �������� ������ ������ ��������
-- ������ ������ : IN, ALL, SOME, ANY, EXISTS

-- IN : ���������� ����� ���������� �����Ϳ� �ϳ��� ��ġ�ϸ� ���
-- = + OR , ����� ��

-- �μ��� �ְ�޿��� �޴� ������ ���� �޿��� �޴� ���� ���
-- 1. �μ��� �ְ�޿�
SELECT MAX(salary) FROM employees GROUP BY department_id;

-- 2. 1���� ����� ���� �޿��� �޴� �������� ���
SELECT first_name,salary from employees 
where salary in (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--3. ANY, SOME : ���������� ���ǽ��� �����ϴ� ���������� ����� �ϳ� �̻��̸� TRUE�� ȭ�鿡 ���
-- IN�����ڴ� ����� ��, ANY/SOME �����ڴ� ��Ұ��� �񱳵� ����
-- ANY, SOME �����ڿ� = �����ڸ� ���� ����ϸ� IN�����ڿ� ���� ���
-- ANY, SOME�� OR�������� ���!
SELECT first_name,salary from employees 
where salary = SOME (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

SELECT first_name,salary from employees 
where salary = ANY (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

-- ��Ұ��� �ľ�
-- �μ��� �ִ�޿����� ���� �޴� ��� ��ȸ
-- �μ��� �ִ�޿� �� ���� ���� �޿��� 4400�� �񱳸� ���� �� ���ǿ� �����ϴ� ��� ȭ�鿡 ���
SELECT first_name,salary from employees 
where salary >= SOME (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--4. ALL ������(AND) : ���������� ���ǽ��� ���������� ����� ��� �����ϸ� TRUE
-- = �����ڸ� ���� �Ǹ� �μ��� �ְ� �޿��� ��� ���� ���� ���
-- SALARY = 4400 AND SLALRY = 13000 AND ....
SELECT first_name,salary from employees 
where salary = ALL (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

SELECT first_name,salary from employees 
where salary >= ALL (SELECT MAX(salary) FROM employees GROUP BY department_id)
order by department_id, salary;

--5. ���߿� ��������
-- �μ��� �ְ� �޿��� �޴� ����
SELECT first_name,department_id,salary FROM employees
WHERE (department_id, salary) 
IN (SELECT department_id,MAX(salary) FROM employees GROUP BY department_id)
ORDER BY 2; 


--6. FROM�� ��������(�ζ��κ�)
-- �μ����� �ְ�޿��� �޴� ���� ��ȸ
SELECT first_name, DMAX.department_id, DMAX."�μ��� �ְ�޿�" FROM employees e
    , (SELECT department_id,MAX(salary) as "�μ��� �ְ�޿�" FROM employees GROUP BY department_id) DMAX
WHERE e.department_id = DMAX.department_id AND e.salary=DMAX."�μ��� �ְ�޿�";

SELECT first_name, DMAX.department_id, DMAX."�μ��� �ְ�޿�" FROM employees e
    join (SELECT department_id,MAX(salary) as "�μ��� �ְ�޿�" FROM employees GROUP BY employees) DMAX
    on e.department_id = DMAX.department_id AND e.salary=DMAX."�μ��� �ְ�޿�";


-- SELECT�� ��������(��Į�� ��������)
-- JOIN���� JOINó�� ����ϰ��� �� �� ���
-- �� ������ �μ� �̸��� ���
SELECT employee_id, department_id
    , (SELECT department_name FROM departments WHERE department_id=e.department_id) AS �μ��̸�
FROM employees e;


SELECT department_name FROM departments;