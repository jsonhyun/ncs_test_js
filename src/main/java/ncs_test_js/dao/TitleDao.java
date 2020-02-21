package ncs_test_js.dao;

import java.util.List;

import ncs_test_js.dto.Title;

public interface TitleDao {
	Title selectTitleByNo(Title title);
	Title selectTitleLastData();
	List<Title> selectTitleByAll();
	
	int insertTitle(Title title);
	int updateTitle(Title title);
	int deleteTitle(Title title);
}
