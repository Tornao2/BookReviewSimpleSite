package com.project.crud.controllers;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.services.BooksAuthorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booksAuthors")
public class BooksAuthorsController {
    private final BooksAuthorsService booksAuthorsService;

    public BooksAuthorsController(BooksAuthorsService booksAuthorsService) {
        this.booksAuthorsService = booksAuthorsService;
    }

    @GetMapping("/")
    public List<BooksAuthorsDto> getAllBooksAuthors() {
        return booksAuthorsService.getAllBooksAuthors();
    }

    @GetMapping("/authorid/{id}")
    public List<BooksAuthorsDto> getBooksAuthorsByAuthor(@PathVariable Integer id) {
        return booksAuthorsService.getBooksAuthorsByAuthor(id);
    }

    @GetMapping("/isbn/{isbn}")
    public List<BooksAuthorsDto> getBooksAuthorsByBook(@PathVariable String isbn) {
        return booksAuthorsService.getBooksAuthorsByBook(isbn);
    }
}
