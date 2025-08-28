package com.project.crud.controllers;

import com.project.crud.dtos.BooksDto;
import com.project.crud.services.BooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService BooksService;

    public BooksController(BooksService BooksService) {
        this.BooksService = BooksService;
    }

    @GetMapping
    public List<BooksDto> getAllBooks() {
        return BooksService.getAllBooks();
    }
}
