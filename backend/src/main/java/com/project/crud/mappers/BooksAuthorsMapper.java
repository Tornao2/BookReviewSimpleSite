package com.project.crud.mappers;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.backend.entities.BooksAuthors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BooksAuthorsMapper {
    @Mapping(source = "id.isbn", target = "isbn")
    @Mapping(source = "id.authorId", target = "authorId")
    BooksAuthorsDto toDto(BooksAuthors entity);
    @Mapping(source = "isbn", target = "id.isbn")
    @Mapping(source = "authorId", target = "id.authorId")
    BooksAuthors toEntity(BooksAuthorsDto dto);
}
