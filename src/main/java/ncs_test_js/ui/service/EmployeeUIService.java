package ncs_test_js.ui.service;

import java.util.List;

import ncs_test_js.dao.DepartmentDao;
import ncs_test_js.dao.EmployeeDao;
import ncs_test_js.dao.TitleDao;
import ncs_test_js.dao.impl.DepartmentDaoImpl;
import ncs_test_js.dao.impl.EmployeeDaoImpl;
import ncs_test_js.dao.impl.TitleDaoImpl;
import ncs_test_js.dto.Department;
import ncs_test_js.dto.Employee;
import ncs_test_js.dto.Title;

public class EmployeeUIService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDao titleDao;
	

	public EmployeeUIService() {
		empDao = EmployeeDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
		titleDao = TitleDaoImpl.getInstance();
	}

	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}

	public List<Employee> showEmployeeList() {
		return empDao.selectEmployeeByAll();
	}

	public Employee lastEmployee() {
		return empDao.selectEmployeeLastData();
	}
	
	public void removeEmployee(Employee emp) {
		empDao.deleteEmployee(emp);
		
	}

	public void modifyEmployee(Employee emp) {
		empDao.updateEmployee(emp);
		
	}

	public void addEmployee(Employee emp) {
		empDao.insertEmployee(emp);
		
	}
}
