package com.project.crud.services;

import com.project.crud.dtos.GenresDto;
import com.project.crud.entities.Genres;
import com.project.crud.mappers.GenresMapper;
import com.project.crud.repositories.BooksGenresRepository;
import com.project.crud.repositories.GenresRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenresService {
    private final GenresRepository genresRepository;
    private final GenresMapper genresMapper;
    private final BooksGenresRepository booksGenresRepository;

    public GenresService(GenresRepository genresRepository, GenresMapper genresMapper, BooksGenresRepository booksGenresRepository) {
        this.genresRepository = genresRepository;
        this.genresMapper = genresMapper;
        this.booksGenresRepository = booksGenresRepository;
    }

    public List<GenresDto> getAllGenres(){
        return StreamSupport.stream(genresRepository.findAll().spliterator(), false).
                map(genresMapper::toDto).collect(Collectors.toList());
    }

    public GenresDto getGenre(String title){
        return genresMapper.toDto(genresRepository.findById(title).orElse(null));
    }

    public HttpStatus deleteGenre(String title){
        if (!booksGenresRepository.findByIdTitle(title).isEmpty()){
            return HttpStatus.CONFLICT;
        }
        if (!genresRepository.existsById(title)){
            return HttpStatus.NOT_FOUND;
        }
        genresRepository.deleteById(title);
        return HttpStatus.OK;
    }

    public ResponseEntity<GenresDto> postGenre(GenresDto body) {
        if (genresRepository.existsById(body.getTitle())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
        Genres entity = genresMapper.toEntity(body);
        genresRepository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
