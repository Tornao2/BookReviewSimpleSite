package com.project.crud.apicontrollers;

import com.project.crud.dtos.BooksDto;
import com.project.crud.services.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
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
        return ResponseEntity.ok(booksService.getBook(isbn));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){
        return ResponseEntity.status(booksService.deleteBook(isbn)).build();
    }

    @PostMapping("/")
    public ResponseEntity<BooksDto> postBook(@RequestBody BooksDto body){
        return booksService.postBooks(body);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BooksDto> updateBook(@RequestBody BooksDto body, @PathVariable String isbn){
        return booksService.updateBook(body, isbn);
    }
}
