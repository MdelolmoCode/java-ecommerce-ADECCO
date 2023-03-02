package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "cart_item")
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private ShoppingCart shoppingCart;

    @OneToOne
    private Product product;

    private Long amount;

    public double getPrice()
    {
        if(product == null)
            return 0;


        return Math.round(product.getPrice() * 100.0) / 100.0;
    }
}