package com.project.crud.services;

import com.project.crud.dtos.UsersDto;
import com.project.crud.entities.Users;
import com.project.crud.exceptionHandling.ForeignKeyFoundException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.UsersMapper;
import com.project.crud.repositories.ReviewsRepository;
import com.project.crud.repositories.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final ReviewsRepository reviewsRepository;

    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper, ReviewsRepository reviewsRepository) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.reviewsRepository = reviewsRepository;
    }

    public List<UsersDto> getAllUsers(){
        return StreamSupport.stream(usersRepository.findAll().spliterator(), false).
                map(usersMapper::toDto).collect(Collectors.toList());
    }

    public UsersDto getUser(String username){
        return usersMapper.toDto(usersRepository.findById(username).orElseThrow(
                () -> new ResourceNotFoundException("user", username)));
    }

    public HttpStatus deleteUser(String username){
        if (usersRepository.findById(username).isEmpty()){
            throw new ResourceNotFoundException("user", username);
        }
        if (!reviewsRepository.findByIdUsername(username).isEmpty()) {
            throw new ForeignKeyFoundException("user", username, "Reviews");
        }
        usersRepository.deleteById(username);
        return HttpStatus.OK;
    }

    public ResponseEntity<UsersDto> postUser(UsersDto body, String password) {
        if (usersRepository.existsById(body.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
        if (password.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        Users entity = usersMapper.toEntity(body);
        entity.setPassword(password);
        entity.setIsAdmin(false);
        Users user = usersRepository.save(entity);
        UsersDto dto = usersMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
