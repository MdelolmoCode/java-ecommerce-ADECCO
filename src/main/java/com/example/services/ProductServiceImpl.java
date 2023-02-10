package com.example.services;

import com.example.entities.Product;
import com.example.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // add log
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    @Override
    public List<Product> findAll() {
        log.info("findAll");
        return this.productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return this.productRepo.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        log.info("findByName {}", name);
        if (name == null)
            return Optional.empty();
        return this.productRepo.findByName(name);
    }

    @Override
    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        log.info("findByPriceBetween {} and {}", minPrice, maxPrice);
        List<Product> products = this.productRepo.findAll();


        return this.productRepo.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByPriceGreaterThan(Double price) {
        log.info("findByPriceGreaterThan {}", price);
        return null;
    }

    @Override
    public List<Product> findByPriceLessThan(Double price) {
        log.info("findByPriceLessThan {}", price);
        return null;
    }

    @Override
    public List<Product> findByStockLeftLessThan(Long amount) {
        log.info("findByStockLeftLessThan {}", amount);
        return null;
    }

    @Override
    public List<Product> findByAvailableTrue() {
        log.info("findByAvailableTrue");
        return null;
    }

    @Override
    public List<Product> findByCategories_Name(String name) {
        log.info("findByCategories_Name {}", name);
        return null;
    }

    @Override
    public List<Product> findByManufacturer_Cif(String cif) {
        log.info("findByManufacturer_Cif {}", cif);
        return null;
    }

    @Override
    public Product save(Product product) {
        log.info("save Product {}", product);
        try {
            this.productRepo.save(product);
        } catch (Exception e) {
            log.error("Error al guardar Product", e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        log.info("update Product {}", product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        try {
            this.productRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting Product", e);
        }
    }

    @Override
    public void addStock(Product product, Long amount) {
        log.info("addStock product: {}, amount: {}", product, amount);

    }

    @Override
    public void removeStock(Product product, Long amount) {
        log.info("removeStock product: {}, amount: {}", product, amount);

    }

    @Override
    public boolean isAvailable(Product product) {
        log.info("isAvailable product: {}", product);
        return false;
    }

    @Override
    public Product changeAvailability(Product product) {
        log.info("changeAvailability product: {}", product);
        return null;
    }
}
