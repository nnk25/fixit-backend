package com.nikaru.fixit.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nikaru.fixit.domain.entities.User;
import com.nikaru.fixit.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public User regUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        
        return userService.login(user);
    }
    
    

}
