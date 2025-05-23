/*게시판 테이블 만들기*/
CREATE TABLE BOARD(
	IDX INT NOT NULL AUTO_INCREMENT,
	TITLE VARCHAR(100) NOT NULL,
	CONTENTS VARCHAR(1000) NOT NULL,
	WRITER VARCHAR(100) NOT NULL,
	COUNT INT DEFAULT 0,
	INDATE DATETIME DEFAULT NOW(),
	PRIMARY KEY(IDX)
);

INSERT INTO BOARD(TITLE,CONTENTS,WRITER)
VALUES('이번 주 금요일은 오전 수업만 진행합니다.','뻥이였다','손지영');

INSERT INTO BOARD(TITLE,CONTENTS,WRITER)
VALUES('오늘의 저녁 메뉴는 뭘까요?','저도 모르겠어요','노랑이');

INSERT INTO BOARD(TITLE,CONTENTS,WRITER)
VALUES('언젠가 동국 한 번 가야하는데..','5월 전에','점심요정');

INSERT INTO BOARD(TITLE,CONTENTS,WRITER)
VALUES('미유제면소 가볼사람','구인공고','박병철');

COMMIT;

SELECT * FROM BOARD;