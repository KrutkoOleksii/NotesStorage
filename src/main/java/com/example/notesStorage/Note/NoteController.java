package com.example.notesStorage.Note;

import com.example.notesStorage.auth.User;
import com.example.notesStorage.auth.UserService;
import com.example.notesStorage.enums.AccessTypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@Api(value = "Note Class")
@EnableSwagger2
@Validated
@Controller
@RequestMapping(value = "/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get list of Notes ", response = HashMap.class, tags = "getNotes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
//    @GetMapping("list")
    @RequestMapping(value = "list")
    public String getNotes(@ApiIgnore @AuthenticationPrincipal User user, @ApiIgnore String filter, @ApiIgnore Map<String, Object> model){
        List<Note> notes;
        if (filter != null || !filter.isEmpty()) {
            user = userService.getById(user.getId());
            notes = noteService.getListNotes(user.getId());
        } else {
            notes = noteService.getListNotes(user.getId());
        }
        int noteCount= notes.size();
        model.put("notes", notes);
        model.put("filter", filter);
        model.put("noteCount", noteCount);
        //model.put("message", "TEST MESSAGE!"); //for view testing
        return "noteList";
    }

    @ApiOperation(value = "Create New Note ", response = Note.class, tags = "noteCreate")
//    @GetMapping("create")
    @RequestMapping(value = "create")
    public String noteCreate( @ApiIgnore Map<String, Object> model){
        return "noteCreate";
    }

    @ApiOperation(value = "Update Note By ID ", response = Note.class, tags = "noteEdit")
//    @GetMapping("edit/{id}")
    @RequestMapping(value = "edit/{id}")
    @ResponseBody
    public String noteEdit(@PathVariable String id,@ApiIgnore  Map<String, Object> model){
        Note note = noteService.getById(UUID.fromString(id));
        if (note != null){
            model.put("editNote", note);
        }
        return "noteEdit";
    }

    @ApiOperation(value = "Delete Note By ID ", response = String.class, tags = "noteDelete")
//    @GetMapping("delete/{id}")
    @RequestMapping(value = "delete/{id}")
    public String noteDelete(@PathVariable String id, @ApiIgnore Map<String, Object> model){
        noteService.deleteById(UUID.fromString(id));
        return "redirect:/note/list";
    }

    @ApiOperation(value = "WRONG! ERROR! 404,302,101,500", response = String.class, tags = "noteError")
//    @GetMapping("error")
    @RequestMapping(value = "error")
    public String noteError(Map<String, Object> model){
        model.put("message", "TEST MESSAGE!"); //for view testing
        return "noteError";
    }

    @ApiOperation(value = "Get Note By ID ", response = Note.class, tags = "noteShow")
//    @GetMapping("share/{id}")
    @RequestMapping(value = "share/{id}")
    public String noteShow(@ApiIgnore @AuthenticationPrincipal @RequestParam(required = false,value = "user") User user,@PathVariable(value = "id",name = "id") String id,@ApiIgnore Map<String,Object> model){
        Optional<Note> note = noteService.findById(UUID.fromString(id));
        if ((!note.isEmpty() && ((user!=null && note.get().getAuthor().getId().equals(user.getId())) ||
                (user == null && note.get().getAccessType().equals(AccessTypes.PUBLIC))))){
        model.put("note", note.get());
        } else {
            model.put("message", "We can't find tis note ");
        }
        return "noteShow";
    }

    @ApiOperation(value = "Create Note Post", response = Note.class, tags = "addNote")
    @PostMapping(value = "create",params = "noteId")
    public String addNote(@ApiIgnore  @AuthenticationPrincipal User user,
                          @Valid @ModelAttribute("editNote") Note editNote,
                          @RequestParam(required = false,value = "noteId") String noteId,
                          @RequestParam(required = false,value = "accessType") String accessType,
                          @ApiIgnore Map<String, Object> model){
        if (!noteId.isBlank()) {
            editNote.setId(UUID.fromString(noteId));
            editNote.setAccessType(AccessTypes.valueOf(accessType.toUpperCase()));
        }
        editNote.setAuthor(user);
        noteService.save(editNote);
        return "redirect:/note/list";
    }

    @ExceptionHandler({ConstraintViolationException.class})
    ModelAndView onConstraintValidationException(ConstraintViolationException e, Model model) {
        List<String> error = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations){
            error.add(violation.getMessage());
        }
        model.addAttribute("message",error);
        return new ModelAndView("noteError");
    }
}
