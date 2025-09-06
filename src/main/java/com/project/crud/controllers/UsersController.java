package com.project.crud.controllers;

import com.project.crud.dtos.UsersDto;
import com.project.crud.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{username}")
    public ResponseEntity<UsersDto> getUser(@PathVariable String username) {
        UsersDto returnValue = usersService.getUser(username);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        return ResponseEntity.status(usersService.deleteUser(username)).build();
    }
}
