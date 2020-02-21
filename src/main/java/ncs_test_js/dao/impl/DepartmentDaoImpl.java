package ncs_test_js.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_test_js.dao.DepartmentDao;
import ncs_test_js.ds.MySqlDataSource;
import ncs_test_js.dto.Department;
import ncs_test_js.util.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl Instance = new DepartmentDaoImpl();
	
	public DepartmentDaoImpl() {}
	
	public static DepartmentDaoImpl getInstance() {
		return Instance;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		String sql = "select dept_no, dept_name, floor from department where dept_no = ?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("dept_no");
		String deptName = rs.getString("dept_name");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select dept_no, dept_name, floor from department";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			List<Department> list = new ArrayList<Department>();
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Department selectDepartmentLastData() {
		String sql = "select dept_no from department order by dept_no desc limit 1";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDeptNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Department getDeptNum(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("dept_no");
		return new Department(deptNo);
	}

	@Override
	public int inserDepartment(Department department) {
		String sql = "insert into department values(?, ?, ?)";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateDepartment(Department department) {
		String sql = "update department set dept_name = ?, floor = ? where dept_no = ?";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setInt(3, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteDepartment(Department department) {
		String sql = "delete from department where dept_no = ?";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return res;
	}

}
