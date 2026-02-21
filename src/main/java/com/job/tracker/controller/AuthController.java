package com.job.tracker.controller;

import com.job.tracker.dto.LoginRequest;
import com.job.tracker.dto.RegisterRequest;
import com.job.tracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        System.out.println(req.getEmail());
        System.out.println(req.getPassword());
        if(req.getEmail().trim().isEmpty() || req.getPassword().trim().isEmpty()){
            return "Please fill all important fields";
        }
        return authService.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req){
        System.out.println(req.getEmail());
        System.out.println(req.getPassword());

        return authService.login(req);
    }


}
