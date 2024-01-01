package com.taskflow.example.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> dataNotValid(MethodArgumentNotValidException ex){
        HashMap<String, String> errorMessages = new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream()
                .map(error -> errorMessages.put(error.getField(), error.getDefaultMessage())).toList();
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);

    }
}
