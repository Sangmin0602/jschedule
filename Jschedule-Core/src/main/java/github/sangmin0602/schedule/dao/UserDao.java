package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	private SqlSessionFactory factory ;
	
	@Autowired
	public UserDao(SqlSessionFactory factory) {
		this.factory = factory;
	}
	
	public UserVO findBySeq(int seq) {
		SqlSession session = factory.openSession(false);
		
		try {
			UserVO user = session.selectOne("User.findBySeq", seq);
			return user;
			
		} finally {
			session.close();
		}
	}
}
