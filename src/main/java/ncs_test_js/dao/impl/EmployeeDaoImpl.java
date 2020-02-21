package ncs_test_js.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ncs_test_js.dao.EmployeeDao;
import ncs_test_js.ds.MySqlDataSource;
import ncs_test_js.dto.Department;
import ncs_test_js.dto.Employee;
import ncs_test_js.dto.Title;
import ncs_test_js.util.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl Instance = new EmployeeDaoImpl();
	
	public EmployeeDaoImpl() {}

	public static EmployeeDaoImpl getInstance() {
		return Instance;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
//		String sql = "select emp_no, emp_name, title, salary, gender, dept, hire_date from employee where emp_no = ?";
		String sql = "select e.emp_no, e.emp_name, t.title_no, t.title_name, e.salary, e.gender, d.dept_no, d.dept_name, d.floor, e.hire_date"
				   + "  from employee e left join title t on e.title = t.title_no" 
				   + "                  left join department d on e.dept = d.dept_no where e.emp_no=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("emp_no");
		String empName = rs.getString("emp_name");
		Title title = new Title(rs.getInt("title_no"));
		int salary = rs.getInt("salary");
		int gender = rs.getInt("gender");
		Department dept = new Department(rs.getInt("dept_no"));
		Date hireDate = rs.getTimestamp("hire_date");
		return new Employee(empNo, empName, title, salary, gender, dept, hireDate);
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
//		String sql = "select emp_no, emp_name, title, salary, gender, dept, hire_date from employee";
		String sql = "select e.emp_no, e.emp_name, t.title_no, t.title_name, e.salary, e.gender, d.dept_no, d.dept_name, d.floor, e.hire_date"
				   + "  from employee e left join title t on e.title = t.title_no" 
				   + "                  left join department d on e.dept = d.dept_no";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				list.add(getEmployeeJoin(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployeeJoin(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("emp_no");
		String empName = rs.getString("emp_name");
		Title title = new Title(rs.getInt("title_no"), rs.getString("title_name"));
		int salary = rs.getInt("salary");
		int gender = rs.getInt("gender");
		Department dept = new Department(rs.getInt("dept_no"));
		dept.setDeptName(rs.getString("dept_name"));
		dept.setFloor(rs.getInt("floor"));
		Date hireDate = rs.getTimestamp("hire_date");
		return new Employee(empNo, empName, title, salary, gender, dept, hireDate);
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee (emp_no, emp_name, title, salary, gender, dept, hire_date) values (?,?,?,?,?,?,?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getGender());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		StringBuilder sql = new StringBuilder("update employee set ");
		if(emp.getEmpName()!=null) sql.append("emp_name=?, ");
		if(emp.getTitle()!=null) sql.append("title=?, ");
		if(emp.getSalary()!=0) sql.append("salary=?, ");
		if(emp.getGender()!=-1) sql.append("gender=?, ");
		if(emp.getDept()!=null) sql.append("dept=?, ");
		if(emp.getHireDate()!=null) sql.append("hire_date=?, ");
		sql.replace(sql.lastIndexOf(","), sql.length(), " ");
		sql.append("where emp_no=?");
		
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			int argCnt = 1;
			if(emp.getEmpName()!=null) pstmt.setString(argCnt++, emp.getEmpName());
			if(emp.getTitle()!=null) pstmt.setInt(argCnt++, emp.getTitle().getTitleNo());
			if(emp.getSalary()!=0) pstmt.setInt(argCnt++, emp.getSalary());
			if(emp.getGender()!=-1) pstmt.setInt(argCnt++, emp.getGender());
			if(emp.getDept()!=null) pstmt.setInt(argCnt++, emp.getDept().getDeptNo());
			if(emp.getHireDate()!=null) pstmt.setTimestamp(argCnt++, new Timestamp(emp.getHireDate().getTime()));
			pstmt.setInt(argCnt++, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where emp_no=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Employee selectEmployeeLastData() {
		String sql = "select emp_no from employee order by emp_no desc limit 1";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmpNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmpNum(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("emp_no");
		return new Employee(empNo);
	}

}
