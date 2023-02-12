package com.example.services;

import com.example.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findByName(String name);
    List<Category> findByMatureFalse();

    Category save(Category category);
    Category update(Category category);
    void deleteById(Long id);

    void changeMature(Category category);

}
