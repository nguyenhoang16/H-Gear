package com.example.h_gear.home;

import com.example.h_gear.home.adapter.ContactProduct;

import java.util.List;

public interface IProduct {
    void onUpdateViewLaptop(List<ContactProduct> contactList);
    void onUpdateViewPC(List<ContactProduct> contactList);
    void onUpdateViewGamingGear(List<ContactProduct> contactList);
}
