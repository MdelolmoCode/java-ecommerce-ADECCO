package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetType;
    private String name;
    private String city;
    private String state;
    private String country;
    private String zipcode; // Código postal
//
//    public Address(String streetType, String name, String city, String state, String country, String zipcode) {
//        this.streetType = streetType;
//        this.name = name;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.zipcode = zipcode;
//    }
}