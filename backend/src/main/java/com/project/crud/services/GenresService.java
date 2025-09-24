package com.project.crud.services;

import com.project.crud.dtos.GenresDto;
import com.project.crud.backend.entities.Genres;
import com.project.crud.exceptionHandling.ForeignKeyFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.GenresMapper;
import com.project.crud.repositories.BooksGenresRepository;
import com.project.crud.repositories.GenresRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        return genresMapper.toDto(genresRepository.findById(StringUtils.capitalize(title.toLowerCase())).orElseThrow(
                () -> new ResourceNotFoundException("genre", title)));
    }

    public HttpStatus deleteGenre(String title){
        title = StringUtils.capitalize(title.toLowerCase());
        if (!booksGenresRepository.findByIdTitle(title).isEmpty()){
            throw new ForeignKeyFoundException("genre", title, "BooksGenres");
        }
        if (!genresRepository.existsById(title)){
            throw new ResourceNotFoundException("genre", title);
        }
        genresRepository.deleteById(title);
        return HttpStatus.OK;
    }

    public ResponseEntity<GenresDto> postGenre(GenresDto body) {
        body.setTitle(StringUtils.capitalize(body.getTitle().toLowerCase()));
        if (genresRepository.existsById(body.getTitle())){
            throw new ResourceAlreadyExistsException("genre", body.getTitle());
        }
        Genres entity = genresMapper.toEntity(body);
        genresRepository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
