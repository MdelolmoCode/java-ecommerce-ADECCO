package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.Product;
import com.example.entities.ShoppingCart;
import com.example.exception.EntitySavingException;
import com.example.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j // add log
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private final  CartItemService cartItemService;

    @Override
    public Optional<Product> findByIdWithCategories(Long id) {
        return productRepo.findByIdWithCategories(id);
    }

    @Override
    public List<Product> findAll() {
        log.info("findAll");
        return productRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if(id == null)
            return false;
        return productRepo.existsById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return productRepo.findById(id);
    }

    @Override
    public List<Product> findAllByNameContainsIgnoreCase(String name) {
        log.info("findAllByNameContainsIgnoreCase {}", name);

        if(!StringUtils.hasLength(name))
            return new ArrayList<>();

        return productRepo.findAllByNameContainsIgnoreCase(name);
    }

/*
    @Override
    public Optional<Product> findByName(String name) {
        log.info("findByName {}", name);
        if (name == null)
            return Optional.empty();
        return productRepo.findByName(name);
    }
*/

    @Override
    public List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice) {
        log.info("findByPriceBetween {} and {}", minPrice, maxPrice);
        return productRepo.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAllByPriceGreaterThan(Double price) {
        log.info("findByPriceGreaterThan {}", price);
        return productRepo.findAllByPriceGreaterThan(price);
    }

    @Override
    public List<Product> findAllByPriceLessThan(Double price) {
        log.info("findByPriceLessThan {}", price);
        return productRepo.findAllByPriceLessThan(price);
    }

    @Override
    public List<Product> findAllByStockLeftLessThan(Long amount) {
        log.info("findByStockLeftLessThan {}", amount);
        return productRepo.findAllByStockLessThan(amount);
    }

    @Override
    public List<Product> findAllByAvailableTrue() {
        log.info("findByAvailableTrue");
        return productRepo.findAllByAvailableTrue();
    }

    @Override
    public List<Product> findAllByCategories_Name(String name) {
        log.info("findByCategories_Name {}", name);
        return productRepo.findAllByCategories_Name(name);
    }

    @Override
    public List<Product> findAllByManufacturer_Cif(String cif) {
        log.info("findByManufacturer_Cif {}", cif);
        return productRepo.findAllByManufacturer_Cif(cif);
    }

    @Override
    public List<Product> findAllByCategories_MatureFalse() {
        log.info("findAllByCategories_MatureFalse");
        return productRepo.findAllByCategories_MatureFalse();
    }

    @Override
    public List<Product> findByCategories_Id(Long id) {
        return productRepo.findByCategories_Id(id);
    }

    @Override
    public Product save(Product product) {
        log.info("save Product {}", product);

        if(product == null)
            throw new IllegalArgumentException("Product cannot be null");

        if(product.getId() != null)
            update(product);

        return productRepo.save(product);

        /*try {
            this.productRepo.save(product);
        } catch (Exception e) {
            log.error("Error saving Product", e);
        }
        throw new EntitySavingException("Error saving Product");*/
    }

    @Override
    public Product update(Product product) {
        log.info("update Product {}", product);

        if(product == null)
            throw new IllegalArgumentException("Product cannot be null");

        if(product.getId() == null)
            throw new IllegalArgumentException("Product ID cannot be null");

        Product productFromDB = productRepo.findById(product.getId()).get();
        productFromDB.setName(product.getName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setPrice(product.getPrice());
        try{
            return productRepo.save(productFromDB);
        } catch(Exception e){
            log.error("Error saving product", e);
        }
        throw new EntitySavingException("Error saving product");
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
    public void deleteAllById(List<Long> ids) {
        log.info("deleteAllById");
        productRepo.deleteAllById(ids);
    }

    @Override
    public void saveAll(List<Product> products) {
        log.info("saveAll");
        productRepo.saveAll(products);
    }

    @Override
    public void addStock(Product product, Long amount) {
        log.info("addStock product: {}, amount: {}", product, amount);

        Product productFromDB = productRepo.findById(product.getId()).get();

        Long finalAmount = productFromDB.getStock() + amount;
        productFromDB.setStock(finalAmount);

        productRepo.save(productFromDB);
    }

    @Override
    public void removeStock(Product product, Long amount) {
        log.info("removeStock product: {}, amount: {}", product, amount);

        if(isAvailable(product) != true)
            throw new IllegalArgumentException("Product not available");

        if (product.getStock() < amount)
            throw new IllegalArgumentException("Error. Not enough stock left to remove");

        Product productFromDB = productRepo.findById(product.getId()).get();

        if(productFromDB.getStock() == amount)
            changeAvailability(productFromDB);

        Long newAmount = productFromDB.getStock() - amount;
        productFromDB.setStock(newAmount);

        productRepo.save(productFromDB);
    }

    @Override
    public boolean isAvailable(Product product) {
        log.info("isAvailable product: {}", product);

        if(product.getAvailable() == true)
            return true;

        return false;
    }

    @Override
    public Product changeAvailability(Product product) {
        log.info("changeAvailability product: {}", product);

        if(product == null)
            throw new IllegalArgumentException("Product cannot be null");

        if(product.getId() == null)
            throw new IllegalArgumentException("Product ID cannot be null");

        Product productFromDB = productRepo.findById(product.getId()).get();

        if(productFromDB.getAvailable() == true)
            productFromDB.setAvailable(false);
        else productFromDB.setAvailable(true);

        try{
            return productRepo.save(productFromDB);
        } catch(Exception e){
            log.error("Error saving product", e);
        }
        throw new EntitySavingException("Error saving product");
    }

    @Override
    public List<CartItem> addProductToShoopingCart(Long id, ShoppingCart shoppingCart) {

        List<CartItem> cartItemList = shoppingCart.getCartItems();

        Optional <CartItem> optionalCartItem = cartItemService.findById(id);
        cartItemList.add(optionalCartItem.get());


        return cartItemList;
    }

}
