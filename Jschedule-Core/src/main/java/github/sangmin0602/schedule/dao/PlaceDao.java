package github.sangmin0602.schedule.dao;

import github.sangmin0602.schedule.PlaceVO;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao {

	private SqlSessionFactory factory ;
	
	@Autowired
	public PlaceDao(SqlSessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * 주어진 seq가 나타내는 사용자가 등록한 모든 장소들을 반환합니다.
	 * @param userSeq - 사용자의 seq 값
	 * @return 모든 장소를 List 로 반환함.
	 */
	public List<PlaceVO> allPlace(Integer userSeq) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param newPlace
	 * @return
	 */
	public PlaceVO insertNewPlace ( PlaceVO newPlace) throws DaoException{
		
		return null;
	}
	
	public PlaceVO updatePlace ( PlaceVO place ) throws DaoException {
		
		return null;
	}
}
