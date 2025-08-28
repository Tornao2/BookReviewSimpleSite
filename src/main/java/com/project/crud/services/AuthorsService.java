package com.project.crud.services;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.mappers.AuthorsMapper;
import com.project.crud.repositories.AuthorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final AuthorsMapper authorsMapper;

    public AuthorsService(AuthorsRepository authorsRepository, AuthorsMapper authorsMapper) {
        this.authorsRepository = authorsRepository;
        this.authorsMapper = authorsMapper;
    }

    public List<AuthorsDto> getAllAuthors(){
        return StreamSupport.stream(authorsRepository.findAll().spliterator(), false).
                map(authorsMapper::toDto).collect(Collectors.toList());
    }
}
