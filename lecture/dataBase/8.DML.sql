-- 8. DML
-- INSERT : ���� �߰�
-- INSERT INTO ���̺��(�÷�����Ʈ) VALUES (��1,��2...)
-- �÷�����Ʈ�� ���� �ۼ����� ���� ���, ��ü �÷��� ���� �ְڴٴ� �ǹ�!
INSERT INTO ����
VALUES(1,'����','��','SJ1234','010-123-1223',SYSDATE,'IT_PROG',5000,0.23,NULL,1);

-- ORA-02291: integrity constraint (HR.����_�μ����̵�_FK) violated - parent key not found
-- �θ����̺� ���� ���� �Է��߱� ������ ������ �Ұ����ؼ� ���� ����
-- �θ����̺� �� �߰��ϱ�
INSERT INTO �μ�(�μ����̵�,�μ��̸�) VALUES(1,'CX��');
SELECT * FROM �μ�;
INSERT INTO �μ� VALUES(2,'4TENTIAL',NULL,NULL);

SELECT*FROM ����;

INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�)
    VALUES(2,'����','��','JIYEONG',SYSDATE,'IT_PROG');

-- ORA-00001: unique constraint (HR.����_�������̵�_PK) violated
-- PK = UK+NN�̶�, 2���� �̹� ���ԵǾ��ִ� ���̶� ����ID �ߺ��� ������ �����߻�!
INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�)
    VALUES(2,'����','��','BOGYEON',SYSDATE,'IT_PROG');

-- ORA-02290: check constraint (HR.����_�޿�_MIN) violated
-- �޿�>0 �������� ����
INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�,�޿�)
    VALUES(3,'����','��','BOGYEON',SYSDATE,'IT_PROG',-1);

-- ���̺� �÷��� ũ�⸦ ����!
ALTER TABLE ���� MODIFY �̸� VARCHAR2(24);
INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�)
    VALUES(3,'���溸�溸�溸��','��','BOGYEON',SYSDATE,'IT_PROG');

-- SQL ����: ORA-00904: "�������̵�": invalid identifier
-- ������ Ÿ���� ���� ���� �� ��Ÿ���� ����!    
INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�)
    VALUES('4','����','��','DOYEON',SYSDATE,'IT_PROG');
    
INSERT INTO ����(����ID,�̸�,��,�̸���,�Ի���,�������̵�)
    VALUES(4,'����','��','DOYEON',SYSDATE,'IT_PROG');

COMMIT; -- �����͸� ���̺� ��������
    
-- UPDATE : ������ ������ �� ���
-- UPDATE ���̺�� SET ������ �÷� = ������ �� WHERE ���ǽ�;
UPDATE ���� SET �ڵ���='010-124-5678';
-- ��ü ���� ������ ������Ʈ ��

ROLLBACK; -- ����(������ COMMIT �� ����)

UPDATE ���� SET �ڵ���='010-123-4567' WHERE ����ID=2;

-- DELETE : ������ ������ �� ���
-- DELETE FROM ���̺�� WHRER ���ǽ�;
COMMIT;

DELETE FROM ����;

DELETE FROM ���� WHERE ����ID=3;
ROLLBACK;

-- DML���� �ڵ����� �����ݿ�X -> �� COMMIT�� ����� �Ѵ�!
-- DDL���� �ڵ����� COMMIT -> �����Ұ�