package com.taskflow.example.handler.exception;

import com.taskflow.example.handler.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex){
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new  ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<?> validationException(ValidationException ex){
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> dataNotValid(MethodArgumentNotValidException ex){
        HashMap<String, String> errorsMessage = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> errorsMessage.put(error.getField(),error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorsMessage, HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> argumentNotValid(IllegalArgumentException ex){
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex){
        GenericResponse message = new GenericResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
