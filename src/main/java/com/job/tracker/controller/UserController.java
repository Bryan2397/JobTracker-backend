package com.job.tracker.controller;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.entity.User;
import com.job.tracker.service.JobService;
import com.job.tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public User getUserInfo(Authentication authentication){
        return userService.getUser(authentication);
    }

    @PostMapping("/aiTimer")
    public void setAiResetTimer(Authentication authentication){
        userService.setUsersAiDate(authentication);
    }
}
