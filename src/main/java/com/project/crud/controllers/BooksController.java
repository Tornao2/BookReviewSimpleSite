package com.project.crud.controllers;

import com.project.crud.dtos.BooksDto;
import com.project.crud.services.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/")
    public List<BooksDto> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BooksDto> getBook(@PathVariable String isbn) {
        BooksDto returnValue = booksService.getBook(isbn);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }
}
