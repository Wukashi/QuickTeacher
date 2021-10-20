package pl.coderslab.QuickTeacher.entity;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    List<Student> students;
    @ManyToMany
    List<Course> courses;
    @ManyToMany
    List<Teacher> teachers;

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Group setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Group setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Group setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Group setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }
}
