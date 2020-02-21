select user(), database ();

desc employee ;
desc title;
desc department ;

select title_no, title_name from title where title_no=1;
select * from title;

select dept_no, dept_name, floor from department;

insert into department values(2, '인사', 12);
update department set dept_name ='모바일개발', floor=11 where dept_no = 5;
delete from department where dept_no = 5;

select emp_no, emp_name, title, manager, salary, gender, dept, hire_date from employee where emp_no = 1005;

select * from employee e;

delete from department where dept_no =8;

ALTER TABLE ncs_test_js.employee MODIFY COLUMN emp_no int(11) auto_increment NOT NULL COMMENT '사원번호';

select * from information_schema.table_constraints where table_name = 'title';


SELECT title_no FROM title ORDER BY title_no DESC LIMIT 1; -- 마지막 데이터 가져오기 

ALTER TABLE department AUTO_INCREMENT=5; -- auto_increment 초기화

select e.emp_no, e.emp_name, t.title_no, t.title_name, e.salary, e.gender, d.dept_no, d.dept_name, e.hire_date
  from employee e left join title t on e.title = t.title_no
 	     left join department d on e.dept = d.dept_no;

