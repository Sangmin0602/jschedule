package github.sangmin0602.schedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import github.sangmin0602.schedule.ScheduleVO;
import github.sangmin0602.schedule.UserVO;
import github.sangmin0602.schedule.dao.ScheduleDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduleController {
	@Autowired
	private ScheduleDao dao ;
	
	@RequestMapping(value="/schedules/byPlace/{place}",
			produces = {"application/json"})
	@ResponseBody
	public String listSchedulesByPlace( @PathVariable(value="place") Integer placeSeq, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("user");
		List<ScheduleVO> schedules = dao.findAllSchedule(placeSeq, user.getSeq());
		
		JSONArray jsonArr = new JSONArray();
		for ( ScheduleVO s : schedules ) {
			JSONObject json = new JSONObject();
			json.put("desc", s.getDescription());
			jsonArr.add(json);
		}
		
		return jsonArr.toJSONString();
	}
}
