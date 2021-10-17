package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;
import pl.coderslab.QuickTeacher.repository.GroupRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/logged")
@Controller
public class CourseController {
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    public CourseController(CourseRepository courseRepository, GroupRepository groupRepository) {
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/allcourses")
    public String showAllCourses(Model model)
    {
        model.addAttribute("courses", courseRepository.findAll());
        return "course/all";
    }
    @RequestMapping("/teachercourselist")
    public String showAllYourCourses(HttpServletRequest request, Model model)
    {
        Teacher teacher = (Teacher) request.getSession().getAttribute("loggedTeacher");
        model.addAttribute("teacherCourses", teacher.getCourses());
        return "course/allteachercourses";
    }
    public @RequestMapping("/avilablecourses")
    String showAvilablecourses(Model model, HttpSession session)
    {
        List<Course> courseList = courseRepository.findAll();
        Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
        for (int i = courseList.size() - 1;i > -1; i--) {
            for (int j = 0; j < teacher.getCourses().size(); j++) {
                if(courseList.get(i).getId().equals(teacher.getCourses().get(j).getId()))
                {
                    courseList.remove(i);
                    break;
                }
            }
        }
        model.addAttribute("avilableCourses", courseList);
        return "course/add";
    }
    @GetMapping("/createcourse")
    public String prepareAddCourse(Model model)
    {
        model.addAttribute("course", new Course());
        return "course/create";
    }
    @PostMapping("/createcourse")
    public String addCourse(@Valid Course course, BindingResult result)
    {
        if(result.hasErrors())
            return "teacher/register";
        List<Course> courses = courseRepository.findAll();
        for (Course cours : courses)
            if (cours.getName().equalsIgnoreCase(course.getName()))
                return "redirect:/logged";
        courseRepository.save(course);
        return "redirect:/logged";
    }
    @RequestMapping("/choosecurrentcourse/{id}")
    public String chooseCurrentCourse(@PathVariable Long id, HttpSession session)
    {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        optionalCourse.ifPresent(course -> session.setAttribute("currentCourse", course));
        //wynullować currentGroup !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1one
        return "redirect:/logged";
    }
    @RequestMapping("/coursestochoose")
    public String chooseCurrentCourseToTeach(HttpSession session, Model model)
    {
        Teacher teacher = (Teacher) session.getAttribute("loggedTeacher");
        model.addAttribute("teacherCourses", teacher.getCourses());
        return "course/choose";
    }
    @RequestMapping("/addgrouptocourse/{groupId}")
    public String chooseCurrentGroup(@PathVariable Long groupId, HttpSession session)
    {
        Course course = (Course) session.getAttribute("currentCourse");
        List<Group> groups = course.getGroups();
        Group group = groupRepository.getById(groupId);
        groups.add(group);
        course.setGroups(groups);
        courseRepository.save(course);
        session.setAttribute("currentCourse", course);
        return "redirect:/logged";
    }
}
