package github.sangmin0602.schedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import github.sangmin0602.schedule.PlaceVO;
import github.sangmin0602.schedule.UserVO;
import github.sangmin0602.schedule.dao.PlaceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaceController {

	@Autowired
	private PlaceDao placeDao;
	
	@RequestMapping(value="/places", method=RequestMethod.GET) 
	public String listPlaces(HttpSession session, Model model) {
		UserVO user = (UserVO)session.getAttribute("user");
		List<PlaceVO> places;
		places = placeDao.allPlace(user.getSeq());
		model.addAttribute("places",places);
		return "places";
	}
	
}
