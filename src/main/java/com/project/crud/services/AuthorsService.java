package com.project.crud.services;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.mappers.AuthorsMapper;
import com.project.crud.repositories.AuthorsRepository;
import com.project.crud.repositories.BooksAuthorsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
        return authorsMapper.toDto(authorsRepository.findById(id).orElse(null));
    }

    public HttpStatus deleteAuthor(Integer id){
        if (!booksAuthorsRepository.findByIdAuthorId(id).isEmpty()){
            return HttpStatus.CONFLICT;
        }
        if (!authorsRepository.existsById(id)){
            return HttpStatus.NOT_FOUND;
        }
        authorsRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
