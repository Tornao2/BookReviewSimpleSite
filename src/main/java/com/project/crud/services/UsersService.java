package com.project.crud.services;

import com.project.crud.dtos.UsersDto;
import com.project.crud.mappers.UsersMapper;
import com.project.crud.repositories.UsersRepository;
import org.springframework.http.HttpStatus;
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

    public UsersDto getUser(String username){
        return usersMapper.toDto(usersRepository.findById(username).orElse(null));
    }

    public HttpStatus deleteUser(String username){
        if (usersRepository.findById(username).isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        usersRepository.deleteById(username);
        return HttpStatus.OK;
    }
}
