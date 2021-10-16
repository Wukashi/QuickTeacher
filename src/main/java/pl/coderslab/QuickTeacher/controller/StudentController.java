package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Student;
import pl.coderslab.QuickTeacher.repository.GroupRepository;
import pl.coderslab.QuickTeacher.repository.StudentRepository;

import javax.validation.Valid;
import java.util.Collection;

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
    @GetMapping("/addstudent")
    public String prepareStudent(Model model)
    {
        model.addAttribute("student", new Student());
        return "student/add";
    }
    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult result, Model model)
    {
        if(result.hasErrors())
            return "teacher/register";
        studentRepository.save(student);
        return "redirect:/logged/allstudents";
    }
    @RequestMapping("/classstudents/{groupId}")
    public String showStudentsInGroup(@PathVariable Long groupId, Model model)
    {
        model.addAttribute("studentsingroup", studentRepository.findAllByGroup_Id(groupId));
        model.addAttribute("group", groupRepository.findById(groupId).get());
        return "student/groupstudents";
    }
}
