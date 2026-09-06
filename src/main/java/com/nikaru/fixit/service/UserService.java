package com.nikaru.fixit.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikaru.fixit.domain.entities.User;
import com.nikaru.fixit.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final TokenService tokenService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository repo, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User register(User userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return repo.save(userEntity);
    }

    public String login(User user) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(auth.isAuthenticated()) return tokenService.generateToken(user.getUsername());
        return null;
    }
}
