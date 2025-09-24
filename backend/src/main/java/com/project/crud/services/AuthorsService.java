package com.project.crud.services;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.backend.entities.Authors;
import com.project.crud.exceptionHandling.ForeignKeyFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.AuthorsMapper;
import com.project.crud.repositories.AuthorsRepository;
import com.project.crud.repositories.BooksAuthorsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final AuthorsMapper authorsMapper;
    private final BooksAuthorsRepository booksAuthorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository, AuthorsMapper authorsMapper, BooksAuthorsRepository booksAuthorsRepository) {
        this.authorsRepository = authorsRepository;
        this.authorsMapper = authorsMapper;
        this.booksAuthorsRepository = booksAuthorsRepository;
    }

    public List<AuthorsDto> getAllAuthors(){
        return StreamSupport.stream(authorsRepository.findAll().spliterator(), false).
                map(authorsMapper::toDto).collect(Collectors.toList());
    }

    public AuthorsDto getAuthor(Integer id){
        return authorsMapper.toDto(authorsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("author", id.toString())));
    }

    public HttpStatus deleteAuthor(Integer id){
        if (!booksAuthorsRepository.findByIdAuthorId(id).isEmpty()){
            throw new ForeignKeyFoundException("author", id.toString(), "BooksAuthors");
        }
        if (!authorsRepository.existsById(id)){
            throw new ResourceNotFoundException("author", id.toString());
        }
        authorsRepository.deleteById(id);
        return HttpStatus.OK;
    }

    public ResponseEntity<AuthorsDto> postAuthor(AuthorsDto body) {
        body.setName(Arrays.stream(body.getName().toLowerCase().split("\\s+"))
                .map(StringUtils::capitalize) //
                .collect(Collectors.joining(" ")));
        body.setCountryOfBirth(Arrays.stream(body.getCountryOfBirth().toLowerCase().split("\\s+"))
                .map(StringUtils::capitalize) //
                .collect(Collectors.joining(" ")));
        if (authorsRepository.existsByNameAndYearOfBirthAndCountryOfBirth(body.getName(), body.getYearOfBirth(), body.getCountryOfBirth())){
            throw new ResourceAlreadyExistsException("author", "combination of name, country of birth and year of birth");
        }
        Authors entity = authorsMapper.toEntity(body);
        entity.setAuthorId(null);
        Authors saved = authorsRepository.save(entity);
        AuthorsDto dto = authorsMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<AuthorsDto> updateAuthor(AuthorsDto body, Integer id) {
        body.setName(Arrays.stream(body.getName().toLowerCase().split("\\s+"))
                .map(StringUtils::capitalize) //
                .collect(Collectors.joining(" ")));
        body.setCountryOfBirth(Arrays.stream(body.getCountryOfBirth().toLowerCase().split("\\s+"))
                .map(StringUtils::capitalize) //
                .collect(Collectors.joining(" ")));
        body.setAuthorId(id);
        if (!authorsRepository.existsById(id)){
            throw new ResourceNotFoundException("author", body.getAuthorId().toString());
        }
        Authors author = authorsRepository.save(authorsMapper.toEntity(body));
        return ResponseEntity.status(HttpStatus.OK).body(authorsMapper.toDto(author));
    }
}
