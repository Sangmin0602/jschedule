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
//		UserVO james = new UserVO(); // 1. 
//		Integer jamesSeq = 1;       // 2. @@@@@@@@@@
//		List<PlaceVO> pls = plsDao.allPlace(jamesSeq);
//		
//		assertNotNull(pls);
//		
//		assertEquals(1, pls.size());
		List<PlaceVO> list;
		PlaceDao dao = ctx.getBean(PlaceDao.class);
		list = dao.allPlace(2);
		
		assertEquals(1, list.size()); 
	}
	
	@Test
	public void udpatePlace() {
		PlaceVO newPlace = new PlaceVO();
		newPlace.setPlaceName("UpdatemongChon");
		newPlace.setDescription("Updatemong");
		newPlace.setOwnerSeq(2);
		newPlace.setSeq(4);
		
		PlaceDao dao = ctx.getBean(PlaceDao.class);
		dao.updatePlace(newPlace);
	}
	
	
	@Test
	public void insertPlace() {
		PlaceVO newPlace = new PlaceVO();
		newPlace.setPlaceName("UpdatemongChon");
		newPlace.setDescription("Updatemong");
		newPlace.setOwnerSeq(2);
		
		PlaceDao dao = ctx.getBean(PlaceDao.class);
		// dao.insertNewPlace(newPlace);
	}
	
	@Test
	public void testDeletePlace() {
		int placeSeq = 4, jameSeq = 2;
		PlaceDao dao = ctx.getBean(PlaceDao.class);
		dao.deletePlace(placeSeq, jameSeq);
		
		List<PlaceVO> places = dao.allPlace(2);
		assertEquals ( 1, places.size());
		
	}
	
}
