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
import java.util.Optional;

@Controller
@RequestMapping("/logged")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/allcourses")
    public String showAllCourses(Model model)
    {
        model.addAttribute("courses", courseRepository.findAll());
        return "course/all";
    }

    @RequestMapping("/mycourses")
    public String showAllYourCourses(HttpServletRequest request, Model model)
    {
        Teacher teacher = (Teacher) request.getSession().getAttribute("loggedTeacher");
        model.addAttribute("teacherCourses", teacher.getCourses());
        return "course/allteachercourses";
    }
    public @RequestMapping("/avilablecourses")
    String showAvilablecourses(Model model)
    {
        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("avilableCourses", courseList);
        return "course/avilable";
    }
    @GetMapping("/addcourse")
    public String prepareAddCourse(Model model)
    {
        model.addAttribute("course", new Course());
        return "course/add";
    }
    @PostMapping("/addcourse")
    public String addCourse(@Valid Course course, BindingResult result)
    {
        if(result.hasErrors())
            return "teacher/register";
        courseRepository.save(course);
        return "redirect:/logged/avilablecourses";
    }
    @RequestMapping("/currentcourse/{id}")
    public String chooseCurrentCourse(@PathVariable Long id, HttpServletRequest request)
    {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        optionalCourse.ifPresent(course -> request.getSession().setAttribute("currentCourse", course));
        //wynullować currentGroup
        return "redirect:/logged";
    }
}
