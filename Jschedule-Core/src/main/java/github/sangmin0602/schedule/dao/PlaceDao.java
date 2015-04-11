package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.PlaceVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao {

	//private SqlSessionFactory factory ;
	private SqlSession session;
	
	@Autowired
	public PlaceDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * 주어진 seq가 나타내는 사용자가 등록한 모든 장소들을 반환합니다.
	 * @param userSeq - 사용자의 seq 값
	 * @return 모든 장소를 List 로 반환함.
	 */
	public List<PlaceVO> allPlace(Integer userSeq) {
		List<PlaceVO> list = session.selectList("placeAll", userSeq);
		return list;
	}
	/**
	 * 
	 * @param newPlace
	 * @return
	 */
	public PlaceVO insertNewPlace ( PlaceVO newPlace) throws DaoException{
		Map map = new HashMap();
		
		map.put("place", newPlace.getPlaceName());
		map.put("desc", newPlace.getDescription());
		map.put("user", newPlace.getOwnerSeq());
		
		int chk = session.insert("AddPlace", map);
		return null;
	}
	
	public PlaceVO updatePlace ( PlaceVO place ) throws DaoException {
		Map map = new HashMap();
		
		map.put("place", place.getPlaceName());
		map.put("desc", place.getDescription());
		map.put("user", place.getOwnerSeq());
		map.put("seq", place.getSeq());
		
		int chk = session.update("updatePlace", map);
		return null;
	}
	
	/**
	 *  /places/3993 method=DELETE
	 *  
	 * @param place
	 * @return
	 */
	public boolean deletePlace(Integer placeSeq, Integer ownnerSeq) {
		Map map = new HashMap();
		map.put("seq", placeSeq);
		map.put("user_seq", ownnerSeq);
		int check = session.delete("deletePlace", map);
		return check > 0;
	}
	public PlaceVO findByPlaceName(String placeName) {
		return session.selectOne("findByPlaceName",placeName);
	}
	
}
