package ncs_test_js.ui.service;

import java.util.List;

import ncs_test_js.dao.TitleDao;
import ncs_test_js.dao.impl.TitleDaoImpl;
import ncs_test_js.dto.Title;

public class TitleUIService {
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public Title lastTitle() {
		return titleDao.selectTitleLastData();
	}
	
	public void removeTitle(Title delTitle) {
		titleDao.deleteTitle(delTitle);
	}
	
	public void addTitle(Title newTitle) {
		titleDao.insertTitle(newTitle);
	}
	
	public void modifyTitle(Title upTitle) {
		titleDao.updateTitle(upTitle);
	}
}
