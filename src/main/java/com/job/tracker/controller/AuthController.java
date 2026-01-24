package com.job.tracker.controller;

import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepository;


    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @PostMapping("/register")
    public String register(@RequestBody User theUser){

        userRepository.save(theUser);
        return "success";
    }
}
