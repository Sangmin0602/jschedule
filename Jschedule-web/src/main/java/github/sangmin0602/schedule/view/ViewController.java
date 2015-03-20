package github.sangmin0602.schedule.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping(value={"/view", "/view/month"})
	public String viewByMonth(Model model) {
		return "cal-by-month";
	}
}
