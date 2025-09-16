package com.project.crud.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(ResourceNotFoundException ex) {
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Resource not found");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnMessage);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> noMethodFound(NoResourceFoundException ex) {
        HashMap<String,String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Method not implemented");
        returnMessage.put("Error message", ("Not implemented the following call: " + (ex.getMessage().split(" "))[3]));
        return ResponseEntity.badRequest().body(returnMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> other(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
    }
}