package github.sangmin0602.schedule.dao;

import static org.junit.Assert.*;

import java.util.List;

import github.sangmin0602.schedule.PlaceVO;
import github.sangmin0602.schedule.SpringBasedTestCase;
import github.sangmin0602.schedule.UserVO;

import org.junit.Before;
import org.junit.Test;

public class TestPlaceDao extends SpringBasedTestCase {

	PlaceDao plsDao ;
	@Before 
	public void setUp() {
		plsDao = ctx.getBean(PlaceDao.class);
	}
	@Test
	public void test() {
		assertNotNull(plsDao);
	}

	@Test
	public void allPlace() {
		UserVO james = new UserVO(1, null, null, null, null); // 1. 
		Integer jamesSeq = 1;                                 // 2. @@@@@@@@@@
		List<PlaceVO> pls = plsDao.allPlace(jamesSeq);
		
		assertNotNull(pls);
		
		assertEquals(1, pls.size());
	}
	
	
}
