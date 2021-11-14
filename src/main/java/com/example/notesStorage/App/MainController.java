package com.example.notesStorage.App;

import com.example.notesStorage.addingNote.Note;
import com.example.notesStorage.addingNote.NoteRepository;
import com.example.notesStorage.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
//@RestController
public class MainController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("main")
    public String main(@RequestParam(required = false,defaultValue = "") String filter, Map<String, Object> model){
        Iterable<Note> notes = noteRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            notes = noteRepository.findByName(filter);
        } else {
            notes = noteRepository.findAll();
        }
        model.put("notes", notes);
        model.put("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Note note = Note.builder()
                .name(tag)
                .message(text)
                .author(user)
                .build();
        noteRepository.save(note);
        Iterable<Note> notes = noteRepository.findAll();
        model.put("notes", notes);
        return "main";
    }

}