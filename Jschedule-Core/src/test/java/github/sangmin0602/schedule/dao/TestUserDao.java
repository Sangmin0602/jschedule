package github.sangmin0602.schedule.dao;

import static org.junit.Assert.*;
import static github.sangmin0602.schedule.TestUtils.*;


import github.sangmin0602.schedule.SpringBasedTestCase;
import github.sangmin0602.schedule.UserVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserDao extends SpringBasedTestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test_findbySeq() {
		UserDao userDao = ctx.getBean(UserDao.class);
		UserVO james = userDao.findBySeq(1);
		assertNotNull (james );
		assertPropertyNotNull(james, new String[0]);
	}
	
	@Test
	public void test_login() {
		UserDao userDao = ctx.getBean(UserDao.class);
		UserVO user = userDao.login( "james@e.mail", "1111");
		assertNotNull( user );
		assertPropertyNotNull(user);
	}
	
	@Test
	public void test_when_invalidLoginInfo() {
		UserDao userDao = ctx.getBean(UserDao.class);
		UserVO user = userDao.login( "james@e.mail", "112332");
		assertNull( user );
	}	
	
	@Test
	public void test_insert_new_user() {
		UserVO newUser = new UserVO();
		newUser.setEmail("e@m.ail");
		newUser.setNickName("newmember");
		newUser.setPassword("1111");
		
		UserDao userDao = ctx.getBean(UserDao.class);
		newUser = userDao.insert(newUser);
		
	}
	
	@Test(expected=DaoException.class)
	public void test_duplicated_email() {
		UserVO newUser = new UserVO();
		newUser.setEmail("james@e.mail");
		newUser.setNickName("newmember");
		newUser.setPassword("1111");
		
		UserDao userDao = ctx.getBean(UserDao.class);
		newUser = userDao.insert(newUser);
	}
	
}
