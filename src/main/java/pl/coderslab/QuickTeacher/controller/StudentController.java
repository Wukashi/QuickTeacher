package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Student;
import pl.coderslab.QuickTeacher.repository.GroupRepository;
import pl.coderslab.QuickTeacher.repository.StudentRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/logged")
public class StudentController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentController(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }
    @ModelAttribute("groups")
    public Collection<Group> publishers() {
        return this.groupRepository.findAll();
    }

    @RequestMapping("/allstudents")
    public String showAllStudents(Model model)
    {
        model.addAttribute("allStudents", studentRepository.findAll());
        return "student/all";
    }
    @GetMapping("/createstudent")
    public String prepareStudent(Model model)
    {
        model.addAttribute("student", new Student());
        return "student/add";
    }
    @PostMapping("/createstudent")
    public String addStudent(@Valid Student student, BindingResult result, Model model)
    {
        if(result.hasErrors())
            return "teacher/register";
        studentRepository.save(student);
        return "redirect:/logged/allstudents";
    }
    @RequestMapping("/studentswithoutgroup")
    public String addStudentToGroup(Model model, HttpSession session)
    {
        List<Student> students = studentRepository.getAllByGroup(null);
        model.addAttribute("avilableStudents",students);
        Group group = (Group) session.getAttribute("currentGroup");
        group = groupRepository.getById(group.getId());
        model.addAttribute("cGroup",group);
        return "student/add";
    }
}
