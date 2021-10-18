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
        Course currentCourse = (Course) request.getSession().getAttribute("currentCourse");
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
    @GetMapping("/creategroup")
    String prepareGroup(Model model, HttpSession session)
    {
        model.addAttribute("group", new Group());
        return "group/create";
    }
    @PostMapping("/creategroup")
    String addGroup(@Valid Group group, BindingResult result)
    {
        if(result.hasErrors())
            return "group/create";
        groupRepository.save(group);
        return "redirect:/logged/allgroups";
    }
    @RequestMapping("/groupstobook")
    String bookAvilableGroup(HttpServletRequest request, Model model)
    {
        List<Group> groups = groupRepository.findAll();
        Course course = (Course) request.getSession().getAttribute("currentCourse");
        for (int i = groups.size() - 1; i >= 0; i--) {
            for (int j = 0; j < course.getGroups().size(); j++) {
                if(groups.get(i).getName().equalsIgnoreCase((course.getGroups().get(j).getName())))
                {
                    groups.remove(i);
                    break;
                }
            }
        }
        model.addAttribute("avilableGroups", groups);
        return "group/add";
    }
    @RequestMapping("/showgroupstochoose")
    public String chooseGroupToTeach(HttpSession session, Model model)
    {
        Course course = (Course) session.getAttribute("currentCourse");
        if(course == null)
            return "redirect:/logged";
        List<Group> groups = groupRepository.findByCourses(course);
        model.addAttribute("avilableGroups", groups);
        return "group/choose";
    }
    @RequestMapping("/grouptochoose")
    public String chooseCurrentGroupToTeach(HttpSession session, Model model)
    {
        Course course = (Course) session.getAttribute("currentCourse");
        model.addAttribute("courseGroups", course.getGroups());
        return "group/choose";
    }
    @RequestMapping("/currentgroup/{groupId}")
    public String setCurrentGroup(@PathVariable Long groupId, HttpSession session)
    {
        Group group = groupRepository.getById(groupId);
        session.setAttribute("currentGroup", group);
        return "redirect:/logged";
    }
}
