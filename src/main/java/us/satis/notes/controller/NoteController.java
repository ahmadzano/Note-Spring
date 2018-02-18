package us.satis.notes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import us.satis.notes.model.HttpResponse;
import us.satis.notes.model.Note;
import us.satis.notes.repository.NoteRepository;

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
            model.addAttribute("status", HttpResponse.STATUS.ok);
            model.addAttribute("item", note);

            return model;

        } catch (Exception error) {
            model.addAttribute("status", HttpResponse.STATUS.error);
            model.addAttribute("message", error.getMessage());

            return model;
        }
    }
}
