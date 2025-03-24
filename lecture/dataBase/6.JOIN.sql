-- 6. JOIN
-- ���� ���̵� 100�� ������ �μ��̸� ���
SELECT d.DEPARTMENT_NAME FROM employees e JOIN departments d ON e.DEPARTMENT_ID=d.DEPARTMENT_ID
WHERE e.EMPLOYEE_ID=100;

-- JOIN : �������� ���̺��� �����ؼ� ���
-- �ʿ��� �����Ͱ� 2�� �̻��� ���̺� ������ ���� �� �� ���� ��ȸ�ϴ� ���
SELECT E.EMPLOYEE_ID, D.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM employees E, departments D
WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND E.EMPLOYEE_ID=100;

-- FROM��
-- ���̺� ������ ��밡��+��Ī ��� ����
-- ��Ī�� ����� ���Ŀ��� ��Ī�� ��� ����! (SELECT, WHERE �� ���)
-- ���̺��� �������� ���, � ���̺��� �÷����� ��Ȯ�ϰ� ���