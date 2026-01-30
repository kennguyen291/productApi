package com.example.product_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @PositiveOrZero(message = "Price cannot be negative")
    private double price;

    private boolean isDiscount;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private int quantity;

    private boolean needsRestock; // The specific property Staff can edit

    // Default constructor (Required by JPA)
    public Product() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isDiscount() { return isDiscount; }
    public void setDiscount(boolean isDiscount) { this.isDiscount = isDiscount; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isNeedsRestock() { return needsRestock; }
    public void setNeedsRestock(boolean needsRestock) { this.needsRestock = needsRestock; }
}