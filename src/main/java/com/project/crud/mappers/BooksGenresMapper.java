package com.project.crud.mappers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.entities.BooksGenres;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksGenresMapper {
    BooksGenresDto toDto(BooksGenres entity);
    BooksGenres toEntity(BooksGenresDto dto);
}
