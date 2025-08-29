package com.project.crud.controllers;


import com.project.crud.dtos.AuthorsDto;
import com.project.crud.services.AuthorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/")
    public List<AuthorsDto> getAllAuthors() {
        return authorsService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorsDto> getAuthor(@PathVariable Integer id) {
        AuthorsDto returnValue = authorsService.getAuthor(id);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id){
        return ResponseEntity.status(authorsService.deleteAuthor(id)).build();
    }
}
