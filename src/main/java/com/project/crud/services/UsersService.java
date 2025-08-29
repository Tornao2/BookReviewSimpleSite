package com.project.crud.services;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.dtos.UsersDto;
import com.project.crud.mappers.UsersMapper;
import com.project.crud.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public List<UsersDto> getAllUsers(){
        return StreamSupport.stream(usersRepository.findAll().spliterator(), false).
                map(usersMapper::toDto).collect(Collectors.toList());
    }

    public UsersDto getUser(Integer id){
        return usersMapper.toDto(usersRepository.findById(id).orElse(null));
    }
}
