package pl.coderslab.QuickTeacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.QuickTeacher.entity.Course;
import pl.coderslab.QuickTeacher.entity.Teacher;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Transactional
    @Query("update Teacher t set t.courses = :list where t.id = :id")
    void updateCurrentTeacherCourses(List<Course> list, Long id);

}
