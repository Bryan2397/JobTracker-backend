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
        if(userRepository.findByEmail(req.getEmail()) != null){
            return "User Already Exists";
        }
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setAiUsage(1);

        userRepository.save(user);

        return jwtService.generateToken(user.getId());
    }


    public String login(LoginRequest req){
        User user = userRepository.findByEmail(req.getEmail());
        if(user == null){
            throw new RuntimeException("Error");
        }

        if(!encoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Error");
        }

        return jwtService.generateToken(user.getId());
    }
}
