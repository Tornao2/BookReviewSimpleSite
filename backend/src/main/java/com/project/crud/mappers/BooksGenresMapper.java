package com.project.crud.mappers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.backend.entities.BooksGenres;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BooksGenresMapper {
    @Mapping(source = "id.isbn", target = "isbn")
    @Mapping(source = "id.title", target = "title")
    BooksGenresDto toDto(BooksGenres entity);
    @Mapping(source = "isbn", target = "id.isbn")
    @Mapping(source = "title", target = "id.title")
    BooksGenres toEntity(BooksGenresDto dto);
}
