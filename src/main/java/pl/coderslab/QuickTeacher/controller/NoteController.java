package pl.coderslab.QuickTeacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Note;
import pl.coderslab.QuickTeacher.entity.Student;
import pl.coderslab.QuickTeacher.entity.Teacher;
import pl.coderslab.QuickTeacher.repository.CourseRepository;
import pl.coderslab.QuickTeacher.repository.NoteRepository;
import pl.coderslab.QuickTeacher.repository.StudentRepository;
import pl.coderslab.QuickTeacher.repository.TeacherRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/logged")
@Controller
public class NoteController {

    private final StudentRepository studentRepository;
    private final NoteRepository noteRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public NoteController(StudentRepository studentRepository, NoteRepository noteRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.noteRepository = noteRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }
    @ModelAttribute("grades")
    public Collection<Integer> skills() {
        List<Integer> skills = new ArrayList<>();
        skills.add(1);
        skills.add(2);
        skills.add(3);
        skills.add(4);
        skills.add(5);
        skills.add(6);
        return skills;
    }

    @GetMapping("/createnote/{studentId}")
    public String prepareNote(@PathVariable Long studentId, Model model, HttpSession session)
    {
        Student student = studentRepository.getById(studentId);
        session.setAttribute("currentStudent", student);
        model.addAttribute("note", new Note());
        return "note/create";
    }
    @PostMapping("/createnote/{studentId}")
    public String saveNote(HttpSession session, @PathVariable Long studentId, @Valid Note note, BindingResult result)
    {
        if(result.hasErrors())
            return "redirect:/loggerd/createnote/" + studentId;
        Course currentCourse = (Course) session.getAttribute("currentCourse");
        currentCourse = courseRepository.getById(currentCourse.getId());
        note.setCourse(currentCourse);
        note.setDate(LocalDate.now());
        note.setTime(LocalTime.now());
        note.setStudent(studentRepository.getById(studentId));
        noteRepository.save(note);
        Student currentStudent = studentRepository.getById(studentId);
        List<Note> studentsNotes = currentStudent.getNotes();
        studentsNotes.add(note);
        currentStudent.setNotes(studentsNotes);
        studentRepository.save(currentStudent);
        Teacher currentTeacher = (Teacher) session.getAttribute("loggedTeacher");
        currentTeacher = teacherRepository.getById(currentTeacher.getId());
        List<Note> teachersNotes = currentTeacher.getNotes();
        teachersNotes.add(note);
        currentTeacher.setNotes(teachersNotes);
        teacherRepository.save(currentTeacher);
        return "redirect:/logged";
    }
    @RequestMapping("/allnotes")
    public String showAllCurrentTeacherNotes(HttpSession session, Model model)
    {
        Teacher currentTeacher = (Teacher) session.getAttribute("loggedTeacher");
        currentTeacher = teacherRepository.getById(currentTeacher.getId());
        List<Note> currentTeacheNotes = currentTeacher.getNotes();
        model.addAttribute("currentTeacheNotes", currentTeacheNotes);
        List<Student> students = new ArrayList<>();
        for (Note currentTeacheNote : currentTeacheNotes) {
            students.add(studentRepository.getById(currentTeacheNote.getStudent().getId()));
        }
        model.addAttribute("students", students);
        return "note/all";
    }
}
