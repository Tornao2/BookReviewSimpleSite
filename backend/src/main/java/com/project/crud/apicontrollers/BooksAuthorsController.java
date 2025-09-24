package com.project.crud.apicontrollers;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.services.BooksAuthorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booksauthors")
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

    @DeleteMapping("/{isbn}/{id}")
    public ResponseEntity<Void> deleteBooksAuthors(@PathVariable String isbn, @PathVariable Integer id){
        return ResponseEntity.status(booksAuthorsService.deleteBooksAuthors(isbn, id)).build();
    }

    @PostMapping("/")
    public ResponseEntity<BooksAuthorsDto> postBooksAuthors(@RequestBody BooksAuthorsDto body){
        return booksAuthorsService.postBooksAuthors(body);
    }
}
