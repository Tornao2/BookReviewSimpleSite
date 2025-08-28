package com.project.crud.controllers;


import com.project.crud.dtos.AuthorsDto;
import com.project.crud.services.AuthorsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public List<AuthorsDto> getAllAuthors() {
        return authorsService.getAllAuthors();
    }
}
