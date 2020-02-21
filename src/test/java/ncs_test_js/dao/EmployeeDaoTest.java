package ncs_test_js.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_js.dao.impl.EmployeeDaoImpl;
import ncs_test_js.dto.Department;
import ncs_test_js.dto.Employee;
import ncs_test_js.dto.Title;
import ncs_test_js.util.LogUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = EmployeeDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	}

	@Test
	public void test01SelectEmployeeByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = dao.selectEmployeeByNo(new Employee(3));
		Assert.assertNotNull(emp);
		LogUtil.prnLog(emp);
		
		LogUtil.prnLog(String.format("%1$tF - %1$tT %1$tY년 %1$tm월 %1$td일 %1$tH시 %1$tM분 %1$tS초 ", emp.getHireDate()));
		
		Calendar hire_date = Calendar.getInstance();
		hire_date.setTime(emp.getHireDate());
		
		LogUtil.prnLog(String.format("%d 년", hire_date.get(Calendar.YEAR)));
		LogUtil.prnLog(String.format("%d 월", hire_date.get(Calendar.MONTH) + 1));
		LogUtil.prnLog(String.format("%d 일", hire_date.get(Calendar.DAY_OF_MONTH)));
		LogUtil.prnLog(String.format("%d시 %d분 %d초", hire_date.get(Calendar.HOUR_OF_DAY), hire_date.get(Calendar.MINUTE), hire_date.get(Calendar.SECOND)));
	}

	@Test
	public void test02SelectEmployeeByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotEquals(0, list.size());
	}

	@Test
	public void test03InsertEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar c = Calendar.getInstance();
		Date hireDate = new Date(c.getTimeInMillis());
		
		Employee emp = new Employee(9, "이유리", new Title(3), 3000000, 1, new Department(2), hireDate);
		LogUtil.prnLog(emp);
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04UpdateEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(9, "이유정", new Title(3), 3000000, 1, new Department(2), new Date());
		LogUtil.prnLog(emp);
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test05DeleteEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(9);
		int res = dao.deleteEmployee(emp);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test06SelectEmployeeLastData() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Employee employee = dao.selectEmployeeLastData();
		Assert.assertNotNull(employee);
		LogUtil.prnLog(employee);
	}

}
