package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Teacher;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@RequestMapping("")
	public String greeting(HttpSession session, Model model) {
		Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
		String name;
		if(teacher == null)
			name = "niezalogowany";
		else
			name = teacher.getFirstName();
		model.addAttribute("loggedName", name);
		return "index";
	}

}