package com.project.crud.controllers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.dtos.UsersDto;
import com.project.crud.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public List<UsersDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getUser(@PathVariable Integer id) {
        UsersDto returnValue = usersService.getUser(id);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }
}
