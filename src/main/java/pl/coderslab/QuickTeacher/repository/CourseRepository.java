package pl.coderslab.QuickTeacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.QuickTeacher.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
