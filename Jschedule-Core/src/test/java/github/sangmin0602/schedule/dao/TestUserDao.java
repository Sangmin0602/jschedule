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

}
