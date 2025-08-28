package com.project.crud.controllers;

import com.project.crud.dtos.UsersDto;
import com.project.crud.services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService UsersService;

    public UsersController(UsersService UsersService) {
        this.UsersService = UsersService;
    }

    @GetMapping
    public List<UsersDto> getAllUsers() {
        return UsersService.getAllUsers();
    }
}
