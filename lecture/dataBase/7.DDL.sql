-- 7. DDL
-- EMPLOYEES -> ����
-- ���̺� ���� Ȯ�� -> �巡�� ���¿��� SHIFT+F4 

CREATE TABLE ����(
    �������̵� NUMBER(6) NOT NULL
    , �̸� VARCHAR2(20)
    , �� VARCHAR2(25) NOT NULL
    , �̸��� VARCHAR2(25) NOT NULL
    , �ڵ��� VARCHAR2(20)
    , �Ի��� DATE NOT NULL
    , �������̵� VARCHAR2(10) NOT NULL
    , �޿� NUMBER(8,2)
    , �������� NUMBER(2,2)
    , �Ŵ������̵� NUMBER(6)
    , �μ����̵� NUMBER(4)
);

-- departments
CREATE TABLE �μ�(
    �μ����̵� NUMBER(4) NOT NULL
    , �μ��̸� VARCHAR2(30) NOT NULL
    , �Ŵ������̵� NUMBER(6)
    , �������̵� NUMBER(4)
);


-- �������� : ���̺� �Է°����� �����͸� ������ ���ǵ�
-- Primary ky(PK) : NOT NULL + UNIQUE �ߺ��Ұ�, NULL�� �Ұ�
-- UNIQUE KEY(UK) : �ߺ��Ұ�, NULL ���
-- NOT NULL(NN) : NULL�Ұ�
-- CHECK(CK) : ������ �����͸� �Է� ����
-- FOREIGN KEY(FK) : �ܷ�Ű, �ٸ� ���̺��� �⺻Ű�� �����ؼ� ����ϴ� Ű

-- PK, UK, CK
-- ALTER TABEL ���̺�� ADD CONSTRAINT �������Ǹ� ��������(�÷�);
ALTER TABLE ���� ADD CONSTRAINT ����_�������̵�_PK PRIMARY KEY(�������̵�);
ALTER TABLE ���� ADD CONSTRAINT ����_�̸���_UK UNIQUE(�̸���);
ALTER TABLE ���� ADD CONSTRAINT ����_�޿�_MIN CHECK(�޿�>0);

-- FK
-- ALTER TABEL ���̺�� ADD CONSTRAINT �������Ǹ� ��������(�÷�) REFERENCES ���������̺�(�������÷�);
ALTER TABLE ���� ADD CONSTRAINT ����_�μ����̵�_FK FOREIGN KEY(�μ����̵�) REFERENCES �μ�(�μ����̵�);

-- ORA-02270: no matching unique or primary key for this column-list
-- �����ϰ��� �ϴ� �μ����̵� PK�� �ƴϱ� ������ ������ �Ұ��� -> �μ����̵� ���� PK ����
ALTER TABLE �μ� ADD CONSTRAINT ����_�μ����̵�_PK PRIMARY KEY(�μ����̵�);

-- FK ���� �ɼ�
-- ON DELETE CASCADE : �θ����̺��� ���� �����ϸ� �ڽ� ���̺��� ���� �����ؼ� ����
-- ON DELETE SET NULL : �θ����̺��� ���� �����ϸ� �ڽ� ���̺��� ���� NULL�� ����
ALTER TABLE ���� ADD CONSTRAINT ����_�μ����̵�_FK FOREIGN KEY(�μ����̵�) REFERENCES �μ�(�μ����̵�) ON DELETE CASCADE;
ALTER TABLE ���� ADD CONSTRAINT ����_�μ����̵�_FK FOREIGN KEY(�μ����̵�) REFERENCES �μ�(�μ����̵�) ON DELETE SET NULL;

-- NOT NULL ����
-- �������� ������ �Ұ���! �����ϰ� �ٽ� ����ų� �÷� ��ü�� ����
ALTER TABLE ���� MODIFY �̸� NOT NULL;

-- �������� ������ ����
ALTER TABLE ���� DROP CONSTRAINT SYS_C007017;

-- �������� ����(���̺� ���� �� VER1) -> �÷����ο��� ����
-- �������� �̸��� �ý����� �ڵ�����, ��� ���߿� �˻��� ���� SYS_C007017
CREATE TABLE ���������׽�Ʈ1(
    PK NUMBER PRIMARY KEY
    , UK VARCHAR2(1) UNIQUE
    , NN NUMBER NOT NULL
    , CK NUMBER CHECK(CK>0)
    , CK2 VARCHAR2(4) CHECK(CK2 IN ('��','��'))
);

-- ���̺� ���� �� VER2
CREATE TABLE ���������׽�Ʈ2(
    PK NUMBER
    , UK VARCHAR2(1)
    , NN NUMBER
    , CK NUMBER
    , CK2 VARCHAR2(4)
    , FK NUMBER
    -- CONSTRAINT ���������̸� ��������(�÷�)
    , CONSTRAINT ��2_PK_PK PRIMARY KEY(PK)
    , CONSTRAINT ��2_UK_UK UNIQUE(UK)
    , CONSTRAINT ��2_NN_NN CHECK(NN IS NOT NULL)
    , CONSTRAINT ��2_CK_MIN CHECK(CK>0)
    , CONSTRAINT ��2_FK_FK FOREIGN KEY(FK) REFERENCES ���������׽�Ʈ1(PK) ON DELETE SET NULL
);


-- ALTER : ���̺� ������ �� ����ϴ� ��ɾ�
-- �÷� �߰�
ALTER TABLE ���� ADD ���� NUMBER;
SELECT * FROM ����;
-- �÷� ����
ALTER TABLE ���� DROP COLUMN ����;
-- �÷� �Ӽ� ����
ALTER TABLE ���� MODIFY �̸� VARCHAR2(40);
-- �÷� �̸� ����
ALTER TABLE ���� RENAME COLUMN �������̵� TO ����ID;


-- DROP TABLE 
DROP TABLE ����;