package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name= "shoppingCart")
public class ShoppingCart {

    @Id
    private Long Id;

    @OneToOne
    Customer customer;

    Double totalPrice;

    @OneToMany
    List<CartItem> cartItem;


}
