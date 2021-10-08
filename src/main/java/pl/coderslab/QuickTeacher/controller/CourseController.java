package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/logged")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping("/mycourses")
    private String showAllYourCourses(HttpServletRequest request, Model model)
    {
        Teacher teacher = (Teacher) request.getSession().getAttribute("loggedTeacher");
        model.addAttribute("teacherCourses", teacher.getCourses());
        return "course/allteachercourses";
    }
    @RequestMapping("/avilablecourses")
    public String showAvilablecourses(Model model)
    {
        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("avilablecourses", courseList);
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
    @RequestMapping("/currentcourse/{id}")
    private String chooseCurrentCourse(@PathVariable Long id, HttpServletRequest request)
    {
        return "redirect:";
    }
}
