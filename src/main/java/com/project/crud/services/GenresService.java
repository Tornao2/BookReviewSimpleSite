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
    private final GenresRepository genresRepository;
    private final GenresMapper genresMapper;

    public GenresService(GenresRepository genresRepository, GenresMapper genresMapper) {
        this.genresRepository = genresRepository;
        this.genresMapper = genresMapper;
    }

    public List<GenresDto> getAllGenres(){
        return StreamSupport.stream(genresRepository.findAll().spliterator(), false).
                map(genresMapper::toDto).collect(Collectors.toList());
    }

    public GenresDto getGenre(Integer id){
        return genresMapper.toDto(genresRepository.findById(id).orElse(null));
    }
}
