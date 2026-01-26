package com.job.tracker.service;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 *
 * This service loads a user from the database using their email address
 * and wraps it in a CustomUserDetails object for authentication.
 * If no user is found, a UsernameNotFoundException is thrown.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User theUser = userRepository.findByEmail(email);

        if(theUser == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(theUser);
    }
}
