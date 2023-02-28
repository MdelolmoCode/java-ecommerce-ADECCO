package com.example.entities;

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

@Table(name = "shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    Customer customer;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<CartItem> cartItems = new ArrayList<>();

}
