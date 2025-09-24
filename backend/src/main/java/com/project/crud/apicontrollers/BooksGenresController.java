package com.project.crud.apicontrollers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.services.BooksGenresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booksgenres")
public class BooksGenresController {
    private final BooksGenresService booksGenresService;

    public BooksGenresController(BooksGenresService booksGenresService) {
        this.booksGenresService = booksGenresService;
    }

    @GetMapping("/")
    public List<BooksGenresDto> getAllBooksGenres() {
        return booksGenresService.getAllBooksGenres();
    }

    @GetMapping("/bookid/{isbn}")
    public List<BooksGenresDto> getBooksGenresByAuthor(@PathVariable String isbn) {
        return booksGenresService.getBooksGenresByBook(isbn);
    }

    @GetMapping("/genreid/{title}")
    public List<BooksGenresDto> getBooksGenresByGenre(@PathVariable String title) {
        return booksGenresService.getBooksGenresByGenre(title);
    }

    @DeleteMapping("/{isbn}/{title}")
    public ResponseEntity<Void> deleteBooksGenres(@PathVariable String isbn, @PathVariable String title){
        return ResponseEntity.status(booksGenresService.deleteBooksGenres(isbn, title)).build();
    }

    @PostMapping("/")
    public ResponseEntity<BooksGenresDto> postBooksGenres(@RequestBody BooksGenresDto body){
        return booksGenresService.postBooksGenres(body);
    }
}
