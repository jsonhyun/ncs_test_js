package ncs_test_js.dao;

import java.util.List;

import ncs_test_js.dto.Employee;

public interface EmployeeDao {
	Employee selectEmployeeByNo(Employee emp);
	Employee selectEmployeeLastData();
	List<Employee> selectEmployeeByAll();
	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
	
	
}
