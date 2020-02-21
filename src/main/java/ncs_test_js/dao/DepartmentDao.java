package ncs_test_js.dao;

import java.util.List;

import ncs_test_js.dto.Department;

public interface DepartmentDao {
	Department selectDepartmentByNo(Department department);
	Department selectDepartmentLastData();
	List<Department> selectDepartmentByAll();
	
	int inserDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
}
