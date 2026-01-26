package com.job.tracker.service;

import com.job.tracker.dao.UserRepository;
import com.job.tracker.dto.LoginRequest;
import com.job.tracker.dto.RegisterRequest;
import com.job.tracker.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder, JWTService jwtService){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest req){
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));

        userRepository.save(user);

        return jwtService.generateToken(user.getEmail());
    }

    public String login(LoginRequest req){
        User user = userRepository.findByEmail(req.getEmail());
        if(user == null){
            throw new RuntimeException("User doesn't exist");
        }

        if(!encoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Password doesn't match");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
