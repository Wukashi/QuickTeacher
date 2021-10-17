package pl.coderslab.QuickTeacher.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2)
    private String name;
    @ManyToMany
    List<Teacher> teachers;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Group> groups;

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Course setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Course setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }
}

