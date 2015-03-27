package github.sangmin0602.schedule.dao;

import static org.junit.Assert.*;
import github.sangmin0602.schedule.SpringBasedTestCase;

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
		assertNotNull ( userDao.findBySeq(1000));
	}

}
