-- 5. GROUP BY,HAVAING, ORDER BY

-- 1. GROUP BY : Ư�� �÷��� �������� �׷�ȭ �� �� ���
-- EX) �μ����� �޿��� ��հ�(������ �Լ�-�����Լ� AVG)�� ���Ͻÿ�

-- ������ �Լ� : ��1 -> ��1
-- EX) �����Լ�, �����Լ�, ��¥�Լ� ���,, ROUND, UPPER
-- ������ �Լ� : ��N -> ��1
-- �����Լ� : SUM, COUNT, MAX, MIN, AVG

SELECT department_id FROM employees GROUP BY department_id;
-- �׷�ȭ�� �ϰ� ���� ��µǴ� ���� ������ ����
-- GROUP BY ���� �ʰ� ����Ǵ� HAVING, SELECT, ORDER BY�������� ����� �� �ִ� �÷��� ���ѵȴ�
-- GROUP BY�� ���� ���� 12���� ������ �Ǿ���, employees_id�� ��µ� �� �ִ� ���� ������ 108���� ���� �Ұ�

SELECT department_id, COUNT(department_id),SUM(salary),MIN(salary),MAX(salary),ROUND(AVG(salary),1)
FROM employees GROUP BY department_id ORDER BY 1;

-- �ǽ�
-- ����ǥ ���̺��� �л����� ������� ����ϱ�. ��, �ݿø��� ���ؼ� �Ҽ��� 1�ڸ������� ���
SELECT ROUND(AVG(����),1) FROM ����ǥ GROUP BY �л�ID;
-- ���񺰷� �ְ� ������ ���� ������ ���
SELECT ����, MAX(����), MIN(����) FROM ����ǥ GROUP BY ����;
-- ������ ���� ���̺��� �� ���� �� ���� �ִ��� ���
SELECT COUNT(�л�ID) FROM ���������� GROUP BY ��;
-- ����ǥ ���̺��� �л����� JAVA�� DATABASE ������ ����� ���
SELECT �л�ID, ROUND(AVG(����),1) FROM ����ǥ WHERE ���� IN ('JAVA','DATABASE') GROUP BY �л�ID;
SELECT �л�ID, ROUND(AVG(����),1) FROM ����ǥ WHERE ���� <> 'PYTHON' GROUP BY �л�ID;


-- HAVING�� : GROUP BY���� ���ؼ� �׷�ȭ �� ����� ������ �����ϴ� ��
-- ��� ����(AVG)�� 75�� ������ �л��鸸 ���
SELECT �л�ID,ROUND(AVG(����)) AS ��ռ��� FROM ����ǥ GROUP BY �л�ID HAVING AVG(����)<=75;
-- ������ �������� �Ҽӵ� ���� �ο����� 3���̻��� ���� ���
SELECT ��, COUNT(�л�ID) FROM ���������� GROUP BY �� HAVING COUNT(�л�ID)>3;
-- ���� ���̺��� �μ��� �ְ� ������ 100,000�̻��� �μ��� ���
SELECT department_id FROM employees GROUP BY department_id HAVING MAX(salary*12)>=100000;
-- ����ǥ ���̺��� �л� �� ��ռ����� ����ϵ�, NULL�� �ƴ� ����
SELECT ROUND(AVG(����),1) FROM ����ǥ WHERE ���� IS NOT NULL GROUP BY �л�ID;
