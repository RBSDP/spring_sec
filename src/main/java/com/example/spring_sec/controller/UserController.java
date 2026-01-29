package com.example.spring_sec.controller;

import com.example.spring_sec.model.User;
import com.example.spring_sec.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("users")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
        
    }
    
}