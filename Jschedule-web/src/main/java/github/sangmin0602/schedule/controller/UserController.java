package github.sangmin0602.schedule.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import github.sangmin0602.schedule.UserVO;
import github.sangmin0602.schedule.dao.UserDao;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	private UserDao dao;
	
	@Autowired
	public UserController ( UserDao dao ){
		this.dao = dao;
	}
	
	@RequestMapping(value="/login.json", method=RequestMethod.POST, 
			consumes = {"application/x-www-form-urlencoded"},
			produces = {"application/json"})
	@ResponseBody
	public String processLogin(HttpServletRequest req, HttpSession session) {
		
		String id = (String) req.getParameter("userid");
		String password = (String) req.getParameter("pass");
		
		UserVO user = dao.login(id, password);

		JSONObject json = new JSONObject();
		if ( user == null ) {
			json.put("success", Boolean.FALSE);
			json.put("cause", "invalid id or password");
		} else {
			session.setAttribute("user", user);
			json.put("success", Boolean.TRUE);
		}
		
		return json.toJSONString();
	}
}
