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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderNumber;
    /* Descomentar tras crear clase Cart
    @OneToOne
    private Cart cart;*/
    /* Descomentar tras crear clase Customer
    @OneToOne
    private Customer customer;*/
    @OneToOne
    private Address address;

}
