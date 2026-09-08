package com.nikaru.fixit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nikaru.fixit.domain.entity.User;
import com.nikaru.fixit.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getOrCreateUser(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setEmail(email);
            return userRepository.save(user);
        });
    }
}
