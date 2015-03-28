package github.sangmin0602.schedule.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
	
	public UserVO findBySeq(int seq) throws DaoException{
		SqlSession session = factory.openSession(false);
		
		try {
			UserVO user = session.selectOne("User.findBySeq", seq);
			return user;
			
		} catch(Exception e){
			throw new DaoException("fail to findBySeq : " + seq, e);
		}finally {
			session.close();
		}
	}

	public UserVO login(String email, String password) {
		SqlSession session = factory.openSession(false);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pass", password);
		try{
			UserVO user = session.selectOne("User.getLogin", map);
			return user;
		} finally {
			session.close();
		}
	}
	
	/**
	 * 새로운 user를 등록함.
	 * 
	 * @param newUser
	 * @return 
	 */
	public UserVO insert(UserVO newUser) throws DaoException{
		// TEST 테스트 해야함.
		return null;
	}
	
	/**
	 * 탈퇴한 사용자로 기록함.
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO checkAsDeleted ( UserVO user) throws DaoException {
		return null;
	}
	
	/**
	 * 탈퇴 요청한 사용자들을 삭제함.
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO delete(UserVO user) throws DaoException {
		return null;
	}
	
	/**
	 * 사용자 정보를 갱신함(현재는 비밀번호밖에 없을 듯...)
	 * 
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO update ( UserVO user) throws DaoException {
		return null;
	}
}
