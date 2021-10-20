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
        return "student/create";
    }
    @PostMapping("/createstudent")
    public String addStudent(@Valid Student student, BindingResult result)
    {
        if(result.hasErrors())
            return "student/create";
        List<Student> students = studentRepository.findAll();
        for (Student value : students) {
            if (student.getName().equalsIgnoreCase(value.getName()))
                return "redirect:/logged/allstudents";
        }
        studentRepository.save(student);
        return "redirect:/logged/studentswithoutgroup";
    }
    @RequestMapping("/studentswithoutgroup")
    public String addStudentToGroup(Model model, HttpSession session) {
        List<Student> students = studentRepository.getAllByGroup(null);
        model.addAttribute("avilableStudents", students);
        Group group = (Group) session.getAttribute("currentGroup");
        group = groupRepository.getById(group.getId());
        model.addAttribute("cGroup", group);
        return "student/add";
    }
    @RequestMapping("/addstudenttogroup/{studentId}")
    public String addStudentTogroup(@PathVariable Long studentId, HttpSession session)
    {
        Student student = studentRepository.getById(studentId);
        Group group = (Group) session.getAttribute("currentGroup");
        group = groupRepository.getById(group.getId());
        student.setGroup(group);
        studentRepository.save(student);
        return "redirect:/logged";
    }
}
