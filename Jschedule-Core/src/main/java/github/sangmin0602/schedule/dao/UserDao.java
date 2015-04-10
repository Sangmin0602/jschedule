package github.sangmin0602.schedule.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import github.sangmin0602.schedule.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor=DaoException.class)
public class UserDao {
	
	private SqlSession session ;
	
	@Autowired
	public UserDao(SqlSession session) {
		this.session = session;
	}
	
	public  UserDao(){}
	
	public int countUsers() throws DaoException {
		int cnt = session.selectOne("User.countAllUser");
		return cnt;
	}
	
	public UserVO findBySeq(int seq) throws DaoException{
		UserVO user = session.selectOne("User.findBySeq", seq);
		return user;
	}

	public UserVO login(String email, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pass", password);
		UserVO user = session.selectOne("User.getLogin", map);
		return user;
	}
	
	/**
	 * 새로운 user를 등록함.
	 * 
	 * @param newUser
	 * @return 
	 */
	public UserVO insert(UserVO newUser) throws DaoException{
		// TEST 테스트 해야함.
		Map<String, String> map = new HashMap<String, String>();
		map.put("nickName", newUser.getNickName());
		map.put("email", newUser.getEmail());
		map.put("password", newUser.getPassword());
		int check = session.insert("newUser", map);
		if(check != 1) {
			session.rollback();
		}
		return newUser;
	}
	
	/**
	 * 탈퇴한 사용자로 기록함.
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO checkAsDeleted ( UserVO user) throws DaoException {
		//update시 업데이트 시간관리 어떻게 할지...
		UserVO uservo = session.selectOne("User.findBySeq", user.getSeq());
		if(uservo != null) {
			session.update("updateDeleteYN", uservo.getSeq());
		}
		return user;
	}
	
	/**
	 * 탈퇴 요청한 사용자들을 삭제함.
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO delete(UserVO user) throws DaoException {
		// String  파라미터 한개만 보낼때  HashMap에 담아야하는지 다른방법이 있느지.
		// User 삭제시 Place 테이블에 관계 있는  seq 같이 삭제
		// 매번 try catch를 해야하는지?? 다른 방법은 없는지.
		int deleteChk = session.delete("deleteUser");
		if(deleteChk != 1) {
			session.rollback();
		}
				
		//Y인것만 지우는건지.
		//UserVO uservo = session.selectOne("User.findBySeq", user.getSeq());
		
		//if(uservo != null) {
			//session.delete("deleteUser", user.getDeleted());
		//}
		
		return user;
	}
	
	/**
	 * 사용자 정보를 갱신함(현재는 비밀번호밖에 없을 듯...)
	 * 
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO update ( UserVO user) throws DaoException {
		Map<String, Object> map = new HashMap<>();
		map.put("nickName", user.getNickName());
		map.put("email", user.getEmail());
		map.put("password", user.getPassword());
		map.put("seq", user.getSeq());
		int checkUpdte = session.update("updateUser",map);
		if ( checkUpdte == 0 ) {
			throw new DaoException("fail to update user " + user);
		}
		return user;
	}
}
