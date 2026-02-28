package com.job.tracker.controller;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.entity.User;
import com.job.tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/me")
    public CustomUserDetails getUserInfo(Authentication authentication, HttpServletRequest request){
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user;
    }
}
