package com.job.tracker.service;

import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(Authentication authentication){
        return userRepository.findByEmail(authentication.getName());
    }



}
