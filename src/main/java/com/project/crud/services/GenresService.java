package com.project.crud.services;

import com.project.crud.dtos.GenresDto;
import com.project.crud.mappers.GenresMapper;
import com.project.crud.repositories.GenresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenresService {
    private final GenresRepository GenresRepository;
    private final GenresMapper GenresMapper;

    public GenresService(GenresRepository GenresGenresRepository, GenresMapper GenresGenresMapper) {
        this.GenresRepository = GenresGenresRepository;
        this.GenresMapper = GenresGenresMapper;
    }

    public List<GenresDto> getAllGenres(){
        return StreamSupport.stream(GenresRepository.findAll().spliterator(), false).
                map(GenresMapper::toDto).collect(Collectors.toList());
    }
}
