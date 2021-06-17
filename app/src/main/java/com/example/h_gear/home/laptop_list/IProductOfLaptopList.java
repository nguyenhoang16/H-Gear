package com.example.h_gear.home.laptop_list;

import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;

import java.util.List;

public interface IProductOfLaptopList {
    void onShowList(List<ContactProductOfLaptop> contactList);
}
