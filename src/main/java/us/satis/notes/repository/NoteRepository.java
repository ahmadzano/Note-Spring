package us.satis.notes.repository;

import org.springframework.data.repository.CrudRepository;
import us.satis.notes.model.Note;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    List<Note> findById(int id);
}
