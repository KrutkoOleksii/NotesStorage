package com.example.notesStorage.auth;

import com.example.notesStorage.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
@PreAuthorize("hasAuthority('ADMIN')") //When admin exists
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Object userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("error")
    public String userError(@RequestParam User user, Model model){
        /*UUID id = user.getId();
        model.addAttribute("userId",user.getId());*/
        return "errorUser";
    }

    @PostMapping("")
    public Object userSave(
            @RequestParam String userName,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            Model model
    ){
        if (!userName.isBlank() && (userName.length()<6 || userName.length()>51)){
            model.addAttribute("message", "The login must be between 5 and 50 characters long");
            model.addAttribute("userId", user.getId());
            return "errorUser";
        }
        user.setUsername(userName);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("{user}")
    public Object userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @ExceptionHandler(ConstraintViolationException.class)
    ModelAndView onConstraintValidationException(ConstraintViolationException e, Model model) {
        List<String> error = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations){
            error.add(violation.getMessage());
        }
        model.addAttribute("message",error);
        return new ModelAndView("error");
    }
}
