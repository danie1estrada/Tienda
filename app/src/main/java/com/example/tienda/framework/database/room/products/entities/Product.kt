package com.example.tienda.framework.database.room.products.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float price;
    private int quantity;
    private String provider;

    public Product(int id, String name, float price, int quantity, String provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.provider = provider;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    public void subtract(int quantity) {
        this.quantity -= quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return name;
    }
}
