package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/logged")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping("/mycourses")
    private String showAllYourCourses(HttpSession session, Model model)
    {
        Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
        model.addAttribute("teacherCourses", teacher.getCourses());
        return "course/allteachercourses";
    }
    @RequestMapping("/avilablecourses")
    public String showAvilablecourses(Model model)
    {
        model.addAttribute("avilablecourses", courseRepository.findAll());
        return "course/avilable";
    }
    @GetMapping("/addcourse")
    private String prepareAddCourse(Model model)
    {
        model.addAttribute("course", new Course());
        return "course/add";
    }
    @PostMapping("/addcourse")
    private String addCourse(@Valid Course course, BindingResult result)
    {
        if(result.hasErrors())
            return "teacher/register";
        courseRepository.save(course);
        return "redirect:/logged/avilablecourses";
    }
}
