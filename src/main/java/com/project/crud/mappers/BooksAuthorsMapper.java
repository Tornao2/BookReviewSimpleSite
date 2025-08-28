package com.project.crud.mappers;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.entities.BooksAuthors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksAuthorsMapper {
    BooksAuthorsDto toDto(BooksAuthors entity);
    BooksAuthors toEntity(BooksAuthorsDto dto);
}
