package com.example.demo.repository;

import com.example.demo.model.classes.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepositiory extends JpaRepository<Show,Long> {
}
