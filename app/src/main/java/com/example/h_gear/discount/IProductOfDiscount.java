package com.example.h_gear.discount;

import com.example.h_gear.discount.adapter.ContactProductOfDiscount;

import java.util.List;

public interface IProductOfDiscount {
    void onShowList(List<ContactProductOfDiscount> contactList);
}
