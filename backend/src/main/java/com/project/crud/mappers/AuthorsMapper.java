package com.project.crud.mappers;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.backend.entities.Authors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {
    AuthorsDto toDto(Authors authors);
    Authors toEntity(AuthorsDto authorsDto);
}
