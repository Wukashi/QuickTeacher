package pl.coderslab.QuickTeacher.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2)
    private String firstName;
    @NotBlank
    @Size(min = 2)
    private String lastName;
    @NotBlank
    @Size(min = 5)
    private String pass;

    @ManyToMany
    private List<Group> groups;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    public Teacher setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Teacher setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Teacher setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public Teacher setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Teacher setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Teacher setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }
}
