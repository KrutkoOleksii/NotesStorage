package com.example.notesStorage.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationEx {


    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView onConstraintValidationException(ConstraintViolationException e, Model model) {
        List<String> error = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            error.add(violation.getMessage());
        }
        model.addAttribute("message", error);
        return new ModelAndView("error");
    }

}
