package ncs_test_js.ui.service;

import java.util.List;

import ncs_test_js.dao.DepartmentDao;
import ncs_test_js.dao.impl.DepartmentDaoImpl;
import ncs_test_js.dto.Department;

public class DepartmentUIService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	
	public List<Department> showDepartmentList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public Department lastDepartment() {
		return deptDao.selectDepartmentLastData();
	}
	
	public void removeDepartment(Department delDept) {
		deptDao.deleteDepartment(delDept);
	}
	
	public void addDepartment(Department newDept) {
		deptDao.inserDepartment(newDept);
	}
	
	public void modifyDepartment(Department upDept) {
		deptDao.updateDepartment(upDept);
	}
}
