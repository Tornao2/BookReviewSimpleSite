package com.project.crud.controllers;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.services.BooksAuthorsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booksAuthors")
public class BooksAuthorsController {
    private final BooksAuthorsService BooksAuthorsService;

    public BooksAuthorsController(BooksAuthorsService BooksAuthorsService) {
        this.BooksAuthorsService = BooksAuthorsService;
    }

    @GetMapping
    public List<BooksAuthorsDto> getAllBooksAuthors() {
        return BooksAuthorsService.getAllBooksAuthors();
    }
}
