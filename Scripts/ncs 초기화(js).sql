-- NCS_프로젝트
DROP SCHEMA IF EXISTS ncs_test_js;

-- NCS_프로젝트
CREATE SCHEMA ncs_test_js;

-- 직책
CREATE TABLE ncs_test_js.title (
	title_no   INTEGER     NOT NULL COMMENT '직책번호', -- 직책번호
	title_name VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE ncs_test_js.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			title_no -- 직책번호
		);

-- 부서
CREATE TABLE ncs_test_js.department (
	dept_no   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
	dept_name VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor     INTEGER     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE ncs_test_js.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			dept_no -- 부서번호
		);

-- 사원
CREATE TABLE ncs_test_js.employee (
	emp_no    INTEGER     NOT NULL COMMENT '사원번호', -- 사원번호
	emp_name  VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title     INTEGER     NULL     COMMENT '직책', -- 직책
	salary    INTEGER     NULL     COMMENT '급여', -- 급여
	gender    TINYINT     NULL     COMMENT '성별', -- 성별
	dept      INTEGER     NULL     COMMENT '부서', -- 부서
	hire_date DATETIME    NOT NULL COMMENT '입사일' -- 입사일
)
COMMENT '사원';

-- 사원
ALTER TABLE ncs_test_js.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE ncs_test_js.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dept -- 부서
		)
		REFERENCES ncs_test_js.department ( -- 부서
			dept_no -- 부서번호
		);

-- 사원
ALTER TABLE ncs_test_js.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES ncs_test_js.title ( -- 직책
			title_no -- 직책번호
		);
		
-- 사용자 추가
drop user if exists 'user_ncs_test_js'@'localhost';
grant all privileges on ncs_test_js.* to 'user_ncs_test_js'@'localhost'
identified by 'rootroot';
flush privileges;