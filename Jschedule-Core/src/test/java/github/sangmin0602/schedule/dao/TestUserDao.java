package github.sangmin0602.schedule.dao;

import static org.junit.Assert.*;
import static github.sangmin0602.schedule.TestUtils.*;


import github.sangmin0602.schedule.SpringBasedTestCase;
import github.sangmin0602.schedule.UserVO;

import org.junit.Test;

public class TestUserDao extends SpringBasedTestCase{

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
		newUser.setEmail("sangmin@gmail.com");
		newUser.setNickName("java");
		newUser.setPassword("1111");
		
		UserDao userDao = ctx.getBean(UserDao.class);
		newUser = userDao.insert(newUser);
		assertEquals ( 2, userDao.countUsers());
		
	}
	
//	@Test
//	public void test_update_delete() {
//		UserVO user = new UserVO();
//		user.setSeq(1);
//		UserDao userDao = ctx.getBean(UserDao.class);
//		userDao.checkAsDeleted(user);
//		
//	}
	
	@Test
	public void test_checkAsDeleted ( ) {
		UserVO user = new UserVO();
		user.setSeq(1);
		UserDao userDao = ctx.getBean(UserDao.class);
		userDao.checkAsDeleted(user);
	}
	@Test
	public void test_delete() {
		UserVO user = new UserVO();
		user.setSeq(1);
		UserDao userDao = ctx.getBean(UserDao.class);
		userDao.delete(user);
	}
	@Test
	public void test_update() {
		UserVO user = new UserVO();
		user.setSeq(1);
		user.setNickName("updatedName");
		user.setEmail("udpated@gmail.com");
		user.setPassword("updatedPasswd");
		UserDao userDao = ctx.getBean(UserDao.class);
		userDao.update(user);
	}
	
//	@Test(expected=DaoException.class)
//	public void test_duplicated_email() {
//		UserVO newUser = new UserVO();
//		newUser.setEmail("james@e.mail");
//		newUser.setNickName("newmember");
//		newUser.setPassword("1111");
//		
//		UserDao userDao = ctx.getBean(UserDao.class);
//		newUser = userDao.insert(newUser);
//	}
	
}
