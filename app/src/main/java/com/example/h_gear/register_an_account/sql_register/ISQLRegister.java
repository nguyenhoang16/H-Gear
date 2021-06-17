package com.example.h_gear.register_an_account.sql_register;

import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.personal.adapter.ProductOfCustomer;

import java.util.List;

public interface ISQLRegister {
    void onSuccessful();
    void onMessager(String mes);
    void onShowAccount(ContactAccount contactAccount);
    void onShowContact(List<ProductOfCustomer> productOfCustomerList);
}
