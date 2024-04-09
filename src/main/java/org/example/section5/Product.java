package org.example.section5;

public class Product {
    private double price;
    private String name;
    private long quantity;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
