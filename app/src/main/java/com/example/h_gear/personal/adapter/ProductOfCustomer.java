package com.example.h_gear.personal.adapter;

public class ProductOfCustomer {
    private String userName;
    private String nameProduct;
    private String image;
    private int amount;
    private double price;
    private String date;

    public ProductOfCustomer() {
    }

    public ProductOfCustomer(String userName, String nameProduct, String image, int amount, double price, String date) {
        this.userName = userName;
        this.nameProduct = nameProduct;
        this.image = image;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
