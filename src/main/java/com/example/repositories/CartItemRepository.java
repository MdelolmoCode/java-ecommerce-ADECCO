package com.example.repositories;

import com.example.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{


    List<CartItem> findAllByShoppingCart(Long id);
}