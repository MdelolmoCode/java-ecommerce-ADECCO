package com.example.services;

import com.example.entities.Category;
import com.example.entities.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findAllByName(String name);
    List<Category> findAllByMatureFalse();

    Category save(Category category);
    Category update(Category category);
    void deleteById(Long id);
    void deleteAllById(List<Long> ids);
    //void saveAll(List<Category> categories);

    void changeMature(Category category);

}
