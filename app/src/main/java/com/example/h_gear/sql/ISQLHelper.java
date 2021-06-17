package com.example.h_gear.sql;

import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.login.ContactAccount;

import java.util.List;

public interface ISQLHelper {
    void onSuccessful();
    void onMessager(String mes);
    void onUpdateView(List<ContactCart> contactCartList);
}
