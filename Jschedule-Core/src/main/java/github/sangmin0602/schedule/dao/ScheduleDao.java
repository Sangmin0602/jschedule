package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.ScheduleVO;
import github.sangmin0602.schedule.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {
	@Autowired
	private SqlSession session;
	
	public List<ScheduleVO> findAllSchedule(Integer placeSeq, Integer userSeq) {
		ArrayList<ScheduleVO> schedules = new ArrayList<ScheduleVO>();
		UserVO me = new UserVO();
		me.setSeq(userSeq);
		
		schedules.add(new ScheduleVO(1000, "jamsil", me));
		schedules.add(new ScheduleVO(1001, "meeting", me));
		schedules.add(new ScheduleVO(1002, "with friends", me));
		
		return schedules;
	}
	
	public List<ScheduleVO> findByUser(Integer userSeq) {
		UserVO me = new UserVO();
		me.setSeq(userSeq);
		return session.selectList("Schedule.findByUser",me.getSeq());
	}

	public void insertSchedule(ScheduleVO schedule) {
		int cnt = session.insert("Schedule.insertSchedule", schedule);
		
		if ( cnt != 1) {
			throw new DaoException ("fail to insert new schedule : " + schedule );
		}
		
	}
}
