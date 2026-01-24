package com.job.tracker.controller;

import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import com.job.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User theUser){
        userService.save(theUser);
        return "success";
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User theUser){
        return userService.compare(theUser);
    }
}
