package com.example.h_gear.home.cart.adapter;

public class ContactCart {
    private String name,image;
    private double price;
    private int count;

    public ContactCart() {
    }

    public ContactCart(String name, String image, double price, int count) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
