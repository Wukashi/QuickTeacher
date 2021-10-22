package pl.coderslab.QuickTeacher.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    @Max(6)
    private short grade;
    private String comment;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    Course course;

    public Long getId() {
        return id;
    }

    public Note setId(Long id) {
        this.id = id;
        return this;
    }

    public short getGrade() {
        return grade;
    }

    public Note setGrade(short grade) {
        this.grade = grade;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Note setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Note setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTime() {
        return time;
    }

    public Note setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public Note setCourse(Course course) {
        this.course = course;
        return this;
    }
}
