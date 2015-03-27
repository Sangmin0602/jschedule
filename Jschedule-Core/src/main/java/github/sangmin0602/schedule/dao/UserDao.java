package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.UserVO;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	public UserVO findBySeq(int seq) {
		return new UserVO(1000, "nick", "e@m.ail", "1234", "2015-02-11  12:33:00");
	}
}
