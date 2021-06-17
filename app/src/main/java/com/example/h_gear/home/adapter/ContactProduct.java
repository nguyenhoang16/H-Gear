package com.example.h_gear.home.adapter;

public class ContactProduct {
    String name;
    String oldPrice;
    String image;

    public ContactProduct() {
    }

    public ContactProduct(String name, String oldPrice, String image) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
