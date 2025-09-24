package com.project.crud.services;

import com.project.crud.dtos.UsersDto;
import com.project.crud.backend.entities.Users;
import com.project.crud.exceptionHandling.ForeignKeyFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
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
            throw new ResourceAlreadyExistsException("user", body.getUsername());
        }
        if (password.length() < 8) {
            throw new RuntimeException("Password must at least have 8 characters");
        }
        Users entity = usersMapper.toEntity(body);
        entity.setPassword(password);
        entity.setIsAdmin(false);
        Users user = usersRepository.save(entity);
        UsersDto dto = usersMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<UsersDto> updateUser(String username, String oldpassword, String newpassword) {
        if (newpassword.length() < 8) {
            throw new RuntimeException("Password must at least have 8 characters");
        }
        if (!usersRepository.existsById(username)){
            throw new ResourceNotFoundException("user", username);
        }
        Users user = usersRepository.findById(username).get();
        if (!user.getPassword().equals(oldpassword)){
            throw new RuntimeException("Wrong old password");
        }
        user.setPassword(newpassword);
        Users afterUpdateUser = usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(usersMapper.toDto(afterUpdateUser));
    }
}
