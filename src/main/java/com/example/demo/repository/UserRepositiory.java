package com.example.demo.repository;

import com.example.demo.model.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositiory extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
