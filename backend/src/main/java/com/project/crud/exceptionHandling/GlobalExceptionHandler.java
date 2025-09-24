package com.project.crud.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;

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
    @ExceptionHandler(ForeignKeyFoundException.class)
    public ResponseEntity<?> foreignKeyFound(ForeignKeyFoundException ex){
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Parent key of resource being used in another table");
        returnMessage.put("Error message", ex.getMessage());
        returnMessage.put("Advice", "To delete this resource you need to remove the resources in the other table");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(returnMessage);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExists(ResourceAlreadyExistsException ex){
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Resource already exists");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(returnMessage);
    }
    @ExceptionHandler(ForeignKeyNotFoundException.class)
    public ResponseEntity<?> NoForeignKeyExists(ForeignKeyNotFoundException ex){
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Foreign key is wrong");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnMessage);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> WrongTypeData(HttpMessageNotReadableException ex){
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Wrong data type/format in body");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnMessage);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> WrongTypeData(MethodArgumentTypeMismatchException ex){
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Wrong data type/format in url");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnMessage);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> other(Exception ex) {
        HashMap<String, String> returnMessage = new HashMap<>();
        returnMessage.put("Error type", "Unspecified error");
        returnMessage.put("Error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnMessage);
    }
}