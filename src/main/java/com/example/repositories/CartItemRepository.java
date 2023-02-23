package com.example.repositories;

import com.example.entities.CartItem;
import com.example.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{

}