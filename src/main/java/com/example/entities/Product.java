package com.example.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    @ManyToMany
    private List<Category> category = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private Long stockLeft;
    private boolean available;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, List<Category> category, Manufacturer manufacturer, Long stockLeft, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
        this.stockLeft = stockLeft;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public List<Category> getCategory() {
        return category;
    }

    public Product setCategory(List<Category> category) {
        this.category = category;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Product setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Long getStockLeft() {
        return stockLeft;
    }

    public Product setStockLeft(Long stockLeft) {
        this.stockLeft = stockLeft;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public Product setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stockLeft=" + stockLeft +
                '}';
    }

}
