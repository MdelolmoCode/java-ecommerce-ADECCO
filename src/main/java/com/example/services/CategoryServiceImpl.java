package com.example.services;

import com.example.entities.Address;
import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;
    @Override
    public List<Category> findAll() {
        log.info("findAll");
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return categoryRepo.findById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        log.info("findByName {}", name);
        return categoryRepo.findByName(name);
    }

    @Override
    public List<Category> findByMatureFalse() {
        log.info("findByMatureFalse");
        return categoryRepo.findByMatureFalse();
    }

    @Override
    public Category save(Category category) {
        log.info("save {}", category);
        if(category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }
        if(category != null)
            update(category);
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        if(category == null)
            throw new IllegalArgumentException("Category can't be null");

        if(category.getId() == null)
            throw new IllegalArgumentException("Category ID can't null");

        if(!categoryRepo.existsById(category.getId()))
            throw new EntityNotFoundException("Category does not exist");

        Address categoryFromDB = categoryRepo.findById(category.getId()).get();
        categoryFromDB.setName(category.getName());
        categoryFromDB.setCity(category.getMature());

        return categoryRepo.save(categoryFromDB);
    }

    @Override
    public void deleteById(Long id) {
        log.info("DeleteById {}", id);
        categoryRepo.deleteById(id);
    }

    @Override
    public void changeMature(Category category) {
        log.info("changeMature {}", category);
        if(category.getMature() == true) {
            category.setMature(false);
        }
        category.setMature(true);
    }
}
