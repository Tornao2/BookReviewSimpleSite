package com.project.crud.mappers;

import com.project.crud.dtos.GenresDto;
import com.project.crud.backend.entities.Genres;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenresMapper {
    GenresDto toDto(Genres genres);
    Genres toEntity(GenresDto genresDto);
}
