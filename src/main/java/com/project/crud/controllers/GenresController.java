package com.project.crud.controllers;

import com.project.crud.dtos.GenresDto;
import com.project.crud.services.GenresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {
    private final GenresService genresService;

    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping("/")
    public List<GenresDto> getAllGenres() {
        return genresService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenresDto> getGenre(@PathVariable Integer id) {
        GenresDto returnValue = genresService.getGenre(id);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id){
        return ResponseEntity.status(genresService.deleteGenre(id)).build();
    }
}
