-- 12. ��ü
-- VIEW �� : ������ ���̺�, �ϳ� �̻��� ���̺��� ��ȸ�ϴ� SELECT���� ������ ��ü
-- �μ��� �ְ�޿� �並 �����, �ش� �並 ����ؼ� �μ����� �ְ�޿��� �޴� ������ ������ ���
-- 1. �� �����
CREATE VIEW �μ����ְ�޿� 
AS SELECT DEPARTMENT_ID, MAX(SALARY) AS �ְ�޿� FROM employees
    GROUP BY department_id
    ORDER BY department_id;

SELECT * FROM �μ����ְ�޿�;

-- 2. �μ����� �ְ�޿��� �޴� ������ ����(�� ���)
CREATE VIEW �ְ�޿�����
AS SELECT FIRST_NAME, SALARY, �ְ�޿� FROM employees e, �μ����ְ�޿� DMAX
WHERE e.department_id=dmax.department_id AND e.salary=dmax.�ְ�޿�;

SELECT * FROM �ְ�޿�����;


-- ������ : ������ǥó�� Ư�� ��Ģ�� �´� ���� ���ڸ� �����ϴ� ��ü
CREATE TABLE ���������̺�(
    ������ NUMBER
);

CREATE SEQUENCE NUM1;

INSERT INTO ���������̺� VALUES(NUM1.NEXTVAL);
SELECT * FROM ���������̺�;

SELECT NUM1.CURRVAL FROM DUAL;  -- DUAL: �������̺�


CREATE SEQUENCE NUM2
START WITH 100 INCREMENT BY 2;

INSERT INTO ���������̺� VALUES(NUM2.NEXTVAL);
SELECT * FROM ���������̺�;

DROP SEQUENCE NUM2;


-- ROWMUN : ���̺��� �࿡ �ӽ÷� �ο��Ǵ� �Ϸ��� ��ȣ(���� ������ ����)
-- �ݵ�� 1���� ���, 6~10���� �Ұ���
SELECT ROWNUM, employee_id FROM employees WHERE ROWNUM<=5;

SELECT ROWNUM, employee_id FROM employees WHERE ROWNUM=5; -- ��� �ȳ���
-- �ζ��κ並 Ȱ���ϸ� Ư�� ROWNUM ���� ����
SELECT * 
FROM (SELECT ROWNUM AS RN, employee_id FROM employees WHERE ROWNUM<=5)
WHERE RN=5;


-- �޿��� ���� ������� ������ ���(NULL ����)
SELECT first_name, salary FROM employees
WHERE salary IS NOT NULL AND ROWNUM<=5
ORDER BY salary DESC;

SELECT * 
FROM (SELECT first_name, salary FROM employees WHERE salary IS NOT NULL
    ORDER BY salary DESC)
WHERE ROWNUM<=5;

-- VIEW�� ������
CREATE VIEW �޿��������� 
AS SELECT first_name, salary FROM employees WHERE salary IS NOT NULL
    ORDER BY salary DESC;
    
SELECT * FROM �޿���������
WHERE ROWNUM<=5;

-- ����¡ ���, N�� ä���
SELECT * FROM (SELECT ROWNUM AS RN, �Խñ�.*
                FROM (SELECT * FROM �Խ��� ORDER BY �Խ��ǹ�ȣ) �Խñ�
                WHERE ROWNUM <= 20*N)
WHERE RN >= 20*(N-1)+1;