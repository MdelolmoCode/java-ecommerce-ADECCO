package com.example.repositories;

import com.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findAllByName(String name);
    List<Category> findAllByMatureFalse();

}