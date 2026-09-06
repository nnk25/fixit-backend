package com.nikaru.fixit.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nikaru.fixit.domain.CustomUserDetails;
import com.nikaru.fixit.domain.entities.User;
import com.nikaru.fixit.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }
        return new CustomUserDetails(user);
    }
    
}
