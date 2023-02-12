package com.example.repositories;

import com.example.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
//    List<CartItem> findAll();
//
//    boolean existsById(Long id);
//    Optional<CartItem> findById(Long id);
//
//    List<CartItem> findByProductId(Long id);
//    List<CartItem> findByShoppinCart(Long id);
//
//    List<CartItem> findByAmountIs(Long amount);
//    List<CartItem> findByAmountGreaterThanEqual(Long minAmount);
//    List<CartItem> findByAmountIsLessThanEqual(Long maxAmount);
//    List<CartItem> findByAmountIsBetween(long minAmount, Long maxAmount);
//
//    List<CartItem> findByPriceIs(double price);
//    List<CartItem> findByPriceIsGreaterThanEqual(double price);
//    List<CartItem> findByPriceIsLessThanEqual(double price);
//    List<CartItem> findByPriceIsBetween(double minPrice, double maxPrice);
}