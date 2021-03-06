package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Student;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.StudentRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
	private final StudentRepository studentRepository;

	public HomeController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
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
		if(course != null)
		{
			model.addAttribute("currentCourse", course);
			model.addAttribute("courseGroups", course.getGroups());
			Group group = (Group) session.getAttribute("currentGroup");
			if(group != null)
			{
				List<Student> students = studentRepository.getAllByGroup(group);
				model.addAttribute("currGroup", group);
				model.addAttribute("groupsStudents", students);
			}
		}
		return "logged";
	}
}