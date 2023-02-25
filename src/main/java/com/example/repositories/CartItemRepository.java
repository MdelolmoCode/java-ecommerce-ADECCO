package com.example.repositories;

import com.example.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    List<CartItem> findAllByProductId(Long id);
    //List<CartItem> findAllByShoppingCartId(Long id);

    List<CartItem> findAllByAmountIs(Long amount);
    List<CartItem> findAllByAmountGreaterThanEqual(Long minAmount);
    List<CartItem> findAllByAmountIsLessThanEqual(Long maxAmount);
    List<CartItem> findAllByAmountIsBetween(long minAmount, Long maxAmount);

//    List<CartItem> findAllByPriceIs(double price);
//    List<CartItem> findAllByPriceIsGreaterThanEqual(double price);
//    List<CartItem> findAllByPriceIsLessThanEqual(double price);
//    List<CartItem> findAllByPriceIsBetween(double minPrice, double maxPrice);
}