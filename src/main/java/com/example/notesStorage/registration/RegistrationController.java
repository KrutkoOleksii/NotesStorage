package com.example.notesStorage.registration;

import antlr.Token;
import com.example.notesStorage.auth.User;
import com.example.notesStorage.auth.UserService;
import com.example.notesStorage.enums.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@Api
@Validated
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Path("register")
    @ApiOperation(value = "Registration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Token.class)
    })
    @GetMapping("/register")
    public String registration(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if (userService.findByUsername(user.getUsername()).isPresent()){
            model.addAttribute("message", "User exists!");
            return "register";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ModelAndView onConstraintValidationException(ConstraintViolationException e, Model model) {
        List<String> error = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations){
            error.add(violation.getMessage());
        }
        model.addAttribute("message",error);
        return new ModelAndView("/register");
    }
}
