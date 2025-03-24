--1.
select first_name,salary*12 from employees where salary*12 >= 120000;
--2.
select first_name,department_id from employees where employee_id=176;
--3.
select first_name,salary*12 as "AnnSal" from employees where not salary*12 BETWEEN 150000 and 200000;
--4.
select first_name,employee_id,hire_date from employees where hire_date BETWEEN '2003/01/01' and '2005/05/30'
order by 3 desc;
--5.
select first_name,department_id from employees where department_id in (20,50) order by first_name;
--6.  ?
select first_name,salary*12 from employees where department_id in (20,50) and (salary*12 BETWEEN 200000 and 250000);
--7.
select first_name,hire_date from employees where hire_date like '2006%';
--8.
select first_name,job_id from employees where manager_id is null;
--9.
select first_name,job_id,manager_id from employees where manager_id is not null;
--10.
select first_name,salary*12 as "ANNSAL", commission_pct from employees where commission_pct is not null;
--11.
select first_name from employees where first_name like '___a%';
--12.
select first_name from employees where first_name like '%a%' and first_name like '%e%';
--13.
select first_name,salary,job_id from employees where not salary in (2500,3500,7000) and (job_id = 'SA_REP' or job_id='ST_CLERK');
select first_name,salary,job_id from employees where salary not in (2500, 3500, 7000) and job_id in ('SA_REP', 'ST_CLERK');
--14.
select DISTINCT job_id from employees where department_id in (30,90) order by job_id;
--15.
select max(salary),min(salary),sum(salary),avg(salary) from employees;
--16.
select job_id,count(employee_id) from employees group by job_id;
--17.
select job_id,max(salary),min(salary),sum(salary),avg(salary) from employees group by job_id
order by job_id;
--18.
select count(DISTINCT manager_id) from employees where manager_id is not null;
--19.
select max(salary)-min(salary) from employees;
--20.
select e.first_name, d.department_name, e.department_id from employees e join departments d 
on e.department_id=d.department_id;