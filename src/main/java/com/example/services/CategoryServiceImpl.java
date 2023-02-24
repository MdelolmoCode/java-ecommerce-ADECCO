package com.example.services;

import com.example.entities.Address;
import com.example.entities.Category;
import com.example.entities.Product;
import com.example.exception.EntitySavingException;
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
    public List<Category> findAllByName(String name) {
        log.info("findAllByName {}", name);
        return categoryRepo.findAllByName(name);
    }

    @Override
    public List<Category> findAllByMatureFalse() {
        log.info("findAllByMatureFalse");
        return categoryRepo.findAllByMatureFalse();
    }

    @Override
    public Category save(Category category) {
        log.info("save {}", category);
        if(category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }
        if(category.getId() != null)
            update(category);

        return categoryRepo.save(category);
        /*try {
            this.categoryRepo.save(category);
        } catch (Exception e) {
            log.error("Error saving Category", e);
        }
        throw new EntitySavingException("Error saving Category");*/
    }

    @Override
    public Category update(Category category) {
        if(category == null)
            throw new IllegalArgumentException("Category cannot be null");

        if(category.getId() == null)
            throw new IllegalArgumentException("Category ID cannot be null");

        if(!categoryRepo.existsById(category.getId()))
            throw new EntityNotFoundException("Category does not exist");

        Category categoryFromDB = categoryRepo.findById(category.getId()).get();
        categoryFromDB.setName(category.getName());
        categoryFromDB.setMature(category.getMature());

        try{
            return categoryRepo.save(categoryFromDB);
        } catch(Exception e){
            log.error("Error saving Category", e);
        }
        throw new EntitySavingException("Error saving Category");
    }

    @Override
    public void deleteById(Long id) {
        log.info("DeleteById {}", id);
        categoryRepo.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        log.info("deleteAllById");
        categoryRepo.deleteAllById(ids);
    }

    @Override
    public void saveAll(List<Category> categories) {
        log.info("saveAll");
        categoryRepo.saveAll(categories);
    }

    @Override
    public void changeMature(Category category) {
        log.info("changeMature {}", category);

        if(category == null)
            throw new IllegalArgumentException("Category cannot be null");

        if(category.getId() == null)
            throw new IllegalArgumentException("Category ID cannot be null");

        Category categoryFromDB = categoryRepo.findById(category.getId()).get();

        if(categoryFromDB.getMature() == true)
            categoryFromDB.setMature(false);
        else categoryFromDB.setMature(true);
    }
}
