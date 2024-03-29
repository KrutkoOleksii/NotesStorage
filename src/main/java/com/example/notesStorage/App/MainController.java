package com.example.notesStorage.App;

import com.example.notesStorage.Note.Note;
import com.example.notesStorage.Note.NoteRepository;
import com.example.notesStorage.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
//@RestController
public class MainController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/")
    public Object greeting(Map<String, Object> model) {
        return "redirect:/note/list";
    }

    @GetMapping("main")
    public Object main(@RequestParam(required = false,defaultValue = "") String filter, Map<String, Object> model){
        Iterable<Note> notes;
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
    public Object add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Map<String, Object> model){
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
