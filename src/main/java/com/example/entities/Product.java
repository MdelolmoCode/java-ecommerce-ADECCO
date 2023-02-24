package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long stock;
    private Boolean available;
    @ToString.Exclude
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    /*// constructor sin ID
    public Product(String name, String description, Double price, Long stock, Boolean available, List<Category> categories, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.available = available;
        this.categories = categories;
        this.manufacturer = manufacturer;
    }*/

    /*// constructor sin ID ni manufacturer
    public Product(String name, String description, Double price, Long stock, Boolean available, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.available = available;
        this.categories = categories;
    }

    // constructor sin ID, Category ni Manufacturer
    public Product(String name, String description, Double price, Long stock, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.available = available;
    }*/

}
