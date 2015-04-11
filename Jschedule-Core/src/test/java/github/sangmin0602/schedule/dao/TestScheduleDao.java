package github.sangmin0602.schedule.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import github.sangmin0602.schedule.PlaceVO;
import github.sangmin0602.schedule.ScheduleVO;
import github.sangmin0602.schedule.SpringBasedTestCase;
import github.sangmin0602.schedule.UserVO;

public class TestScheduleDao extends SpringBasedTestCase {

	@Test
	public void test_findAllSchedule() {
		ScheduleDao dao = ctx.getBean(ScheduleDao.class);
//		dao.findAllSchedule(1);
//		dao.findByPlace();
		List<ScheduleVO> schedules = dao.findByUser(1);
		assertEquals(1, schedules.size());
	}
	
	@Test
	public void test_create_new_schedule() {
		UserDao userDao = ctx.getBean(UserDao.class);
		PlaceDao placeDao = ctx.getBean(PlaceDao.class);
		
		UserVO james = userDao.findByNickname("james");
		PlaceVO mongchon = placeDao.findByPlaceName("mongchon");
		
		assertNotNull ( james.getSeq() );
		assertNotNull ( mongchon.getSeq() );
		
		ScheduleVO newSchedule = new ScheduleVO();
		newSchedule.setOwner(james);
		newSchedule.setPlace(mongchon);
		newSchedule.setTime("2015-04-14 10:00:00");
		newSchedule.setRegisteredTime("2015-04-10 10:00:00");
		newSchedule.setDescription("테스트 일정");
		
		ScheduleDao dao = ctx.getBean(ScheduleDao.class);
		
		dao.insertSchedule ( newSchedule);
		
		assertTrue ( newSchedule.getSeq().intValue() > 0 ) ;
		
	}
}
