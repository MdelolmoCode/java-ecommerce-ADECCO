package com.example.entities;

import com.example.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders") // No puede llamarse 'order' porque es una keyword en SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long orderNumber;
    @OneToOne
    private ShoppingCart shoppingCart;
    @ManyToOne
    private Address address;
    private PaymentMethod paymentMethod;

    public List<CartItem> getCartItems() {
        if (getShoppingCart() == null || getShoppingCart().getCartItems() == null) {
            return new ArrayList<>();
        }
        return getShoppingCart().getCartItems();
    }

}
