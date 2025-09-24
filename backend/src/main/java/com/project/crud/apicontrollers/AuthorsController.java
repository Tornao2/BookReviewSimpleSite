package com.project.crud.apicontrollers;


import com.project.crud.dtos.AuthorsDto;
import com.project.crud.services.AuthorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/")
    public List<AuthorsDto> getAllAuthors() {
        return authorsService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorsDto> getAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok(authorsService.getAuthor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id){
        return ResponseEntity.status(authorsService.deleteAuthor(id)).build();
    }

    @PostMapping("/")
    public ResponseEntity<AuthorsDto> postAuthor(@RequestBody AuthorsDto body){
        return authorsService.postAuthor(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorsDto> updateAuthor(@RequestBody AuthorsDto body, @PathVariable Integer id){
        return authorsService.updateAuthor(body, id);
    }
}
