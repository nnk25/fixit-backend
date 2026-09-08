package com.nikaru.fixit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nikaru.fixit.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email);
}
