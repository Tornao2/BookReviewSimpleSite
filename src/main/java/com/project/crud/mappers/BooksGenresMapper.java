package com.project.crud.mappers;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.entities.BooksGenres;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BooksGenresMapper {
    @Mapping(source = "id.isbn", target = "isbn")
    @Mapping(source = "id.genreId", target = "genreId")
    BooksGenresDto toDto(BooksGenres entity);
    @Mapping(source = "isbn", target = "id.isbn")
    @Mapping(source = "genreId", target = "id.genreId")
    BooksGenres toEntity(BooksGenresDto dto);
}
