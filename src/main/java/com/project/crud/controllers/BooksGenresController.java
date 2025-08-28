package com.project.crud.controllers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.services.BooksGenresService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booksGenres")
public class BooksGenresController {
    private final BooksGenresService BooksGenresService;

    public BooksGenresController(BooksGenresService BooksGenresService) {
        this.BooksGenresService = BooksGenresService;
    }

    @GetMapping
    public List<BooksGenresDto> getAllBooksGenres() {
        return BooksGenresService.getAllBooksGenres();
    }
}
