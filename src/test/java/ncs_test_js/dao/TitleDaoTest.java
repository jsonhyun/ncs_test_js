package ncs_test_js.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_js.dao.impl.TitleDaoImpl;
import ncs_test_js.dto.Title;
import ncs_test_js.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		dao = TitleDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
	}

	@Test
	public void test01SelectTitleByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Title title = dao.selectTitleByNo(new Title(1));
		Assert.assertNotNull(title);
		LogUtil.prnLog(title);
	}

	@Test
	public void test02SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Title> list = dao.selectTitleByAll();
		Assert.assertNotNull(list);
		
		for(Title t : list) {
			LogUtil.prnLog(t);
		}
	}

	@Test
	public void test03InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Title title = new Title(6, "인턴");
		int res = dao.insertTitle(title);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Title title = new Title(1, "회장");
		int res = dao.updateTitle(title);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Title title = new Title(6, "인턴");
		int res = dao.deleteTitle(title);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test06SelectTitleLastData() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Title title = dao.selectTitleLastData();
		Assert.assertNotNull(title);
		LogUtil.prnLog(title);
	}

}
