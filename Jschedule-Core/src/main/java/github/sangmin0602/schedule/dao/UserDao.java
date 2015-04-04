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
		SqlSession session = factory.openSession(false);
		Map<String, String> map = new HashMap<String, String>();
		map.put("nickName", newUser.getNickName());
		map.put("email", newUser.getEmail());
		map.put("password", newUser.getPassword());
		try {
			int check = session.insert("newUser", map);
			if(check != 1) {
				session.rollback();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * 탈퇴한 사용자로 기록함.
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public UserVO checkAsDeleted ( UserVO user) throws DaoException {
		//update시 업데이트 시간관리 어떻게 할지...
		SqlSession session = factory.openSession(false);
		try {
			UserVO uservo = session.selectOne("User.findBySeq", user.getSeq());
			if(uservo != null) {
				session.update("updateDeleteYN", uservo.getSeq());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return null;
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
		SqlSession session = factory.openSession(false);
		try {
			int deleteChk = session.delete("deleteUser");
			if(deleteChk != 1) {
				session.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		//Y인것만 지우는건지.
		//UserVO uservo = session.selectOne("User.findBySeq", user.getSeq());
		
		//if(uservo != null) {
			//session.delete("deleteUser", user.getDeleted());
		//}
		
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
		SqlSession session = factory.openSession(false);
		Map map = new HashMap();
		map.put("nickName", user.getNickName());
		map.put("email", user.getEmail());
		map.put("password", user.getPassword());
		map.put("seq", user.getSeq());
		try {
			int checkUpdte = session.update("updateUser",map);
			if(checkUpdte != 1) {
				session.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}
}
