package ncs_test_js.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_test_js.dao.TitleDao;
import ncs_test_js.ds.MySqlDataSource;
import ncs_test_js.dto.Title;
import ncs_test_js.util.LogUtil;

public class TitleDaoImpl implements TitleDao {
	private static final TitleDaoImpl Instance = new TitleDaoImpl();
	
	public TitleDaoImpl() {}

	public static TitleDaoImpl getInstance() {
		return Instance;
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select title_no, title_name from title where title_no=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getTitle(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("title_no");
		String titleName = rs.getString("title_name");
		return new Title(titleNo, titleName);
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select title_no, title_name from title";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Title> list = new ArrayList<Title>();
			while(rs.next()) {
				list.add(getTitle(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values (?,?)";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set title_name = ? where title_no = ?";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTitleName());
			pstmt.setInt(2, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where title_no = ?";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Title selectTitleLastData() {
		String sql = "select title_no from title order by title_no desc limit 1";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getTitleNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitleNum(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("title_no");
		return new Title(titleNo);
	}

}
