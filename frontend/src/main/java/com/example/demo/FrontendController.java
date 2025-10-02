package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/Authors")
    public String authors() {
        return "authors";
    }

    @GetMapping("/Books")
    public String books() {
        return "books";
    }

    @GetMapping("/Genres")
    public String genres() {
        return "genres";
    }

    @GetMapping("/Users")
    public String users() {
        return "users";
    }

    @GetMapping("/Reviews")
    public String reviews() {
        return "reviews";
    }

    @GetMapping("/BooksAuthors")
    public String booksAuthors() {
        return "booksAuthors";
    }

    @GetMapping("/BooksGenres")
    public String booksGenres() {
        return "booksGenres";
    }
}