package com.project.crud.controllers;

import com.project.crud.dtos.GenresDto;
import com.project.crud.services.GenresService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {
    private final GenresService GenresService;

    public GenresController(GenresService GenresService) {
        this.GenresService = GenresService;
    }

    @GetMapping
    public List<GenresDto> getAllGenres() {
        return GenresService.getAllGenres();
    }
}
