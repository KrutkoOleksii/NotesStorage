package com.example.notesStorage.validator;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.executable.ValidateOnExecution;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ValidateOnExecution
public class ValidateUtils {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConversionNotSupportedException.class)
    public static Map<String,String> getErrors(BindingResult bindingResult){
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField(),
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}