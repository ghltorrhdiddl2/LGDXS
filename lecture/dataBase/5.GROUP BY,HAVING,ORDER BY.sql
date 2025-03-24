-- 5. GROUP BY,HAVAING, ORDER BY

-- 1. GROUP BY : 특정 컬럼을 기준으로 그룹화 할 때 사용
-- EX) 부서별로 급여의 평균값(다중행 함수-집계함수 AVG)을 구하시오

-- 단일행 함수 : 행1 -> 행1
-- EX) 문자함수, 숫자함수, 날짜함수 등등,, ROUND, UPPER
-- 다중행 함수 : 행N -> 행1
-- 집계함수 : SUM, COUNT, MAX, MIN, AVG

SELECT department_id FROM employees GROUP BY department_id;
-- 그룹화를 하고 나면 출력되는 행의 개수가 제한
-- GROUP BY 보다 늦게 실행되는 HAVING, SELECT, ORDER BY절에서는 사용할 수 있는 컬럼이 제한된다
-- GROUP BY를 통해 행이 12개로 제한이 되었고, employees_id는 출력될 수 있는 행의 개수가 108개라서 실행 불가

SELECT department_id, COUNT(department_id),SUM(salary),MIN(salary),MAX(salary),ROUND(AVG(salary),1)
FROM employees GROUP BY department_id ORDER BY 1;

-- 실습
-- 성적표 테이블에서 학생별로 평균점수 출력하기. 단, 반올림을 통해서 소수점 1자리까지만 출력
SELECT ROUND(AVG(성적),1) FROM 성적표 GROUP BY 학생ID;
-- 과목별로 최고 성적과 최저 성적을 출력
SELECT 과목, MAX(성적), MIN(성적) FROM 성적표 GROUP BY 과목;
-- 수강생 정보 테이블에서 각 팀에 몇 명이 있는지 출력
SELECT COUNT(학생ID) FROM 교육생정보 GROUP BY 팀;
-- 성적표 테이블에서 학생별로 JAVA와 DATABASE 성적의 평균을 출력
SELECT 학생ID, ROUND(AVG(성적),1) FROM 성적표 WHERE 과목 IN ('JAVA','DATABASE') GROUP BY 학생ID;
SELECT 학생ID, ROUND(AVG(성적),1) FROM 성적표 WHERE 과목 <> 'PYTHON' GROUP BY 학생ID;


-- HAVING절 : GROUP BY절을 통해서 그룹화 된 결과에 조건을 지정하는 절
-- 평균 성적(AVG)이 75점 이하인 학생들만 출력
SELECT 학생ID,ROUND(AVG(성적)) AS 평균성적 FROM 성적표 GROUP BY 학생ID HAVING AVG(성적)<=75;
-- 수강생 정보에서 소속된 팀의 인원수가 3명이상인 팀만 출력
SELECT 팀, COUNT(학생ID) FROM 교육생정보 GROUP BY 팀 HAVING COUNT(학생ID)>3;
-- 직원 테이블에서 부서별 최고 연봉이 100,000이상인 부서만 출력
SELECT department_id FROM employees GROUP BY department_id HAVING MAX(salary*12)>=100000;
-- 성적표 테이블에서 학생 별 평균성적을 출력하되, NULL이 아닌 값만
SELECT ROUND(AVG(성적),1) FROM 성적표 WHERE 성적 IS NOT NULL GROUP BY 학생ID;
