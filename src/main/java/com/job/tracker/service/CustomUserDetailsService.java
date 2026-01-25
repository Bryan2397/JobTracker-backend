package com.job.tracker.service;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
