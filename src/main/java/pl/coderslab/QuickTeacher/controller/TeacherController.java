package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;
import pl.coderslab.QuickTeacher.repository.TeacherRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("loggedTeacher");
        session.removeAttribute("currentCourse");
        session.removeAttribute("currentGroup");

        return "redirect:";
    }

    @GetMapping("/login")
    public String prepareLogin()
    {
        return "teacher/login";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("pass") String pass)
    {
        List<Teacher> teachers = teacherRepository.findAll();
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getFirstName().equals(name)&&teachers.get(i).getLastName().equals(lastName) && teachers.get(i).getPass().equals(pass))
            {
                request.getSession().setAttribute("loggedTeacher", teachers.get(i));
                return "redirect:/logged";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String prepareRegistration(Model model)
    {
        model.addAttribute("teacher", new Teacher());
        return "teacher/register";
    }
    @PostMapping("/register")
    public String register(@Valid Teacher teacher, BindingResult result)
    {
        if(result.hasErrors())
            return "teacher/register";
        teacherRepository.save(teacher);
        return "redirect:/login";
    }
    @GetMapping("/logged/addcourse/{id}")
    public String addCourse(@PathVariable Long id, HttpServletRequest request)
    {
        Teacher teacher = (Teacher) request.getSession().getAttribute("loggedTeacher");
        List<Course> courses = teacher.getCourses();
        courses.add(courseRepository.getById(id));
        teacher.setCourses(courses);
        teacherRepository.save(teacher);
        return "redirect:/logged/mycourses";
    }
}
