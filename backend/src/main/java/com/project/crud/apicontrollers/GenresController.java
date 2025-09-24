package com.project.crud.apicontrollers;

import com.project.crud.dtos.GenresDto;
import com.project.crud.services.GenresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenresController {
    private final GenresService genresService;

    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping("/")
    public List<GenresDto> getAllGenres() {
        return genresService.getAllGenres();
    }

    @GetMapping("/{title}")
    public ResponseEntity<GenresDto> getGenre(@PathVariable String title) {
        return ResponseEntity.ok(genresService.getGenre(title));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteGenre(@PathVariable String title){
        return ResponseEntity.status(genresService.deleteGenre(title)).build();
    }

    @PostMapping("/")
    public ResponseEntity<GenresDto> postGenre(@RequestBody GenresDto body){
        return genresService.postGenre(body);
    }
}
