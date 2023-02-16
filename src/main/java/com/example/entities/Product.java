package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// @ToString
@Builder

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long stockLeft;
    private Boolean available;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    // constructor sin ID
    public Product(String name, String description, Double price, Long stockLeft, Boolean available, List<Category> categories, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLeft = stockLeft;
        this.available = available;
        this.categories = categories;
        this.manufacturer = manufacturer;
    }

    // constructor sin ID, Category ni Manufacturer
    public Product(String name, String description, Double price, Long stockLeft, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLeft = stockLeft;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockLeft=" + stockLeft +
                ", available=" + available +
                '}';
    }
}
