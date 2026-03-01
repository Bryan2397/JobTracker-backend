package com.job.tracker.controller;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.entity.User;
import com.job.tracker.service.JobService;
import com.job.tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private JobService jobService;

    @GetMapping("/me")
    public CustomUserDetails getUserInfo(Authentication authentication){
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user;
    }


}
