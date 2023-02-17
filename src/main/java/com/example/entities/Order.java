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

}
