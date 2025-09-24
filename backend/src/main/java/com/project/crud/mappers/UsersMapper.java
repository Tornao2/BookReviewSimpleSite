package com.project.crud.mappers;

import com.project.crud.dtos.UsersDto;
import com.project.crud.backend.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto toDto(Users users);
    Users toEntity(UsersDto usersDto);
}
