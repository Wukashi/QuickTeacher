package pl.coderslab.QuickTeacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.QuickTeacher.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Long> {

}
