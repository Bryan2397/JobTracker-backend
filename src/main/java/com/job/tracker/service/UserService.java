package com.job.tracker.service;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return null;
        }
        user.setPassword(null);
        user.setId(-1);
        return user;
    }



    public void setUsersAiDate(Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return;
        }
        if (user.getAiUsage() == 0 && user.getAiResetDate().plusMinutes(1).isBefore(LocalDateTime.now())) {
            userRepository.resetAiUsage(user.getId());
        }
    }

}
