package com.project.crud.controllers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.services.BooksGenresService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booksGenres")
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

    @GetMapping("/genreid/{id}")
    public List<BooksGenresDto> getBooksGenresByGenre(@PathVariable Integer id) {
        return booksGenresService.getBooksGenresByGenre(id);
    }
}
