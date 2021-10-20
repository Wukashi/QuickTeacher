package pl.coderslab.QuickTeacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.QuickTeacher.entity.Group;
import pl.coderslab.QuickTeacher.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> getAllByGroup(Group group);
}
