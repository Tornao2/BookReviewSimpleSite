package com.project.crud.apicontrollers;

import com.project.crud.dtos.UsersDto;
import com.project.crud.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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
        return ResponseEntity.ok(usersService.getUser(username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        return ResponseEntity.status(usersService.deleteUser(username)).build();
    }

    @PostMapping("/{password}")
    public ResponseEntity<UsersDto> postUsers(@RequestBody UsersDto body, @PathVariable String password){
        return usersService.postUser(body, password);
    }

    @PutMapping("/{username}/{oldpassword}/{newpassword}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable String username, @PathVariable String oldpassword, @PathVariable String newpassword){
        return usersService.updateUser(username, oldpassword, newpassword);
    }
}
