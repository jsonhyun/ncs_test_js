select user(),database ();

-- title
desc title;

insert into title values
(1, '사장'),
(2, '부장'),
(3, '과장'),
(4, '대리'),
(5, '사원');

-- department
desc department;

insert into department values
(1, '영업', 8),
(2, '기획', 10),
(3, '개발', 9),
(4, '총무', 7);

-- employee
desc employee;

insert into employee (emp_no, emp_name, title, salary, gender, dept, hire_date) values
(1,'이성래',1,5000000, 0, 2, '2000-03-01'),
(2,'박영권',3,3000000, 0, 1, '2000-07-01'),
(3,'조민희',3,3000000, 1, 2, '2005-07-01'),
(4,'이수민',2,4000000, 1, 3, '2007-07-01'),
(5,'김창섭',4,2500000, 0, 2, '2010-12-01'),
(6,'최종철',5,1500000, 0, 3, '2010-10-01'),
(7,'이유영',5,1500000, 1, 4, '2010-12-01'),
(8,'서현진',3,3000000, 1, 4, '2007-11-01');
