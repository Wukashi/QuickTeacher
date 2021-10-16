package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;
import pl.coderslab.QuickTeacher.repository.GroupRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/logged")
public class GroupController {
    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @RequestMapping("/groups")
    public String showTeachersGroup(HttpServletRequest request, Model model)
    {
        Teacher currentTeacher = (Teacher) request.getSession().getAttribute("loggedTeacher");
        Course currentCourse = (Course) request.getSession().getAttribute("currentcourse");
        List<Group> teachersCourseGroups = groupRepository.findByTeachersAndCourses(currentTeacher, currentCourse);
        model.addAttribute("avilablegroups", teachersCourseGroups);
        model.addAttribute("coursename", currentCourse.getName());
        return "group/teachercoursegropus";
    }
    @RequestMapping("/allgroups")
    public String showAllGroups(Model model)
    {
        List<Group> allGroups = groupRepository.findAll();
        model.addAttribute("allGroups", allGroups);
        return "group/all";
    }
    @GetMapping("/addgroup")
    String prepareGroup(Model model)
    {
        model.addAttribute("group", new Group());
        return "group/add";
    }
    @PostMapping("/addgroup")
    String addGroup(@Valid Group group, BindingResult result)
    {
        if(result.hasErrors())
            return "group/add";
        groupRepository.save(group);
        return "redirect:/logged/allgroups";
    }
    @RequestMapping("/groupstobook")
    String chooseAvilableGroup(HttpServletRequest request, Model model)
    {
        List<Group> groups = groupRepository.findAll();
        Course course = (Course) request.getSession().getAttribute("currentcourse");
        for (int i = groups.size() - 1; i >= 0; i--) {
            for (int j = 0; j < groups.get(i).getCourses().size(); j++) {
                if(groups.get(i).getCourses().get(j) == course)
                {
                    groups.remove(i);
                    break;
                }
            }
        }
        model.addAttribute("avilablegroups", groups);
        return "group/avilable";
    }
}