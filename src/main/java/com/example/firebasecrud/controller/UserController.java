package com.example.firebasecrud.controller;

import com.example.firebasecrud.model.User;
import com.example.firebasecrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }
    @PostMapping
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return userService.getUser(userId);
    }
    @PutMapping
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return userService.deleteUser(userId);
    }
}
