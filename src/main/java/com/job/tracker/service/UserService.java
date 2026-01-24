package com.job.tracker.service;

import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void save(User theUser){
        theUser.setPassword(encoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
    }


}
