package com.project.crud.controllers;

import com.project.crud.dtos.BooksDto;
import com.project.crud.services.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){
        return ResponseEntity.status(booksService.deleteBook(isbn)).build();
    }

    @PostMapping("/")
    public ResponseEntity<BooksDto> postBook(@RequestBody BooksDto body){
        return booksService.postBooks(body);
    }
}
