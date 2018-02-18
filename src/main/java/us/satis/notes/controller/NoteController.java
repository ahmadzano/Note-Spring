package us.satis.notes.controller;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import us.satis.notes.model.Note;
import us.satis.notes.repository.NoteRepository;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    private enum STATUS {ok, error }

    @GetMapping("/all")
    public @ResponseBody Iterable<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    List<Note> getAllNotes(@PathVariable int id) {
        return noteRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public  @ResponseBody ModelMap addNote( Note note ) {

        note.setUuid(UUID.randomUUID().toString());
        note.setCratedAt(Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
        ModelMap model = new ModelMap();

        try {
            noteRepository.save(note);
            model.addAttribute("status", STATUS.ok);
            model.addAttribute("item", note);

            return model;

        } catch (Exception error) {
            model.addAttribute("status", STATUS.error);
            model.addAttribute("message", error.getMessage());

            return model;
        }
    }
}
