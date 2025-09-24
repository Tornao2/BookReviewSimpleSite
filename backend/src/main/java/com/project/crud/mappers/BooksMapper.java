package com.project.crud.mappers;

import com.project.crud.dtos.BooksDto;
import com.project.crud.backend.entities.Books;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BooksDto toDto(Books books);
    Books toEntity(BooksDto booksDto);
}
