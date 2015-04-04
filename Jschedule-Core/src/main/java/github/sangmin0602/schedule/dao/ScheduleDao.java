package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.ScheduleVO;
import github.sangmin0602.schedule.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {
	@Autowired
	private SqlSessionFactory factory;
	
	public List<ScheduleVO> findAllSchedule(Integer placeSeq, Integer userSeq) {
		ArrayList<ScheduleVO> schedules = new ArrayList<ScheduleVO>();
		UserVO me = new UserVO();
		me.setSeq(userSeq);
		
		schedules.add(new ScheduleVO(1000, "jamsil", me));
		schedules.add(new ScheduleVO(1001, "meeting", me));
		schedules.add(new ScheduleVO(1002, "with friends", me));
		
		return schedules;
	}
}
