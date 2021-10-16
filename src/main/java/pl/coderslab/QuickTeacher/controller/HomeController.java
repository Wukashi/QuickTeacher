package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.GroupRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
	private final GroupRepository groupRepository;

	public HomeController(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@RequestMapping("")
	public String greeting(HttpSession session, Model model) {
		Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
		String teacherName;
		String courseName;
		if(teacher == null)
			teacherName = "niezalogowany";
		else
			teacherName = teacher.getFirstName();
		model.addAttribute("loggedName", teacherName);

		Course course = (Course) session.getAttribute("currentCourse");
		if(course == null)
			courseName = "niewybrany";
		else
			courseName = course.getName();
		model.addAttribute("currentCourseName", courseName);
		return "index";
	}
	@RequestMapping("/logged")
	public String logged(HttpSession session, Model model) {
		Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
		model.addAttribute("teacherCourses", teacher.getCourses());
		Course course = (Course) session.getAttribute("currentCourse");
		model.addAttribute("currentCourse", course);
		List<Group> teachersCourseGroups = groupRepository.findByTeachersAndCourses(teacher, course);
		model.addAttribute("groups", teachersCourseGroups);
		return "logged";
	}
}