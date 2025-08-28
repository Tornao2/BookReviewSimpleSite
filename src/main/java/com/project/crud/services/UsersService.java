package com.project.crud.services;

import com.project.crud.dtos.UsersDto;
import com.project.crud.mappers.UsersMapper;
import com.project.crud.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {
    private final UsersRepository UsersRepository;
    private final UsersMapper UsersMapper;

    public UsersService(UsersRepository UsersUsersRepository, UsersMapper UsersUsersMapper) {
        this.UsersRepository = UsersUsersRepository;
        this.UsersMapper = UsersUsersMapper;
    }

    public List<UsersDto> getAllUsersAuthors(){
        return StreamSupport.stream(UsersRepository.findAll().spliterator(), false).
                map(UsersMapper::toDto).collect(Collectors.toList());
    }
}
