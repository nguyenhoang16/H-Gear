package com.example.h_gear.sql;

import android.content.Context;

import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.cart.adapter.IonClickContact;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.SQLRegisterHelper;

import java.util.List;

public class SQLPresenter {
    ISQLHelper isqlHelper;
    Context context;
    SQLHelper sqlHelper;

    public SQLPresenter(IonClickContact ionClickContact, Context context) {
    }

    public SQLPresenter(ISQLHelper isqlHelper, Context context) {
        this.isqlHelper = isqlHelper;
        this.context = context;
    }

    public void onSaveProductInCart(String nameOfProduct, double priceOfProduct, int amount, String image){
        try{
            if(sqlHelper == null)sqlHelper = new SQLHelper(context);
            sqlHelper.insertProductInCart(nameOfProduct,priceOfProduct,amount,image);
            isqlHelper.onSuccessful();
        }catch (Exception e){
            isqlHelper.onMessager(e.getMessage());
        }
    }
    public void onShowListContact(){
        if(sqlHelper == null)sqlHelper = new SQLHelper(context);
        List<ContactCart> contactList = sqlHelper.getAllContact();
        isqlHelper.onUpdateView(contactList);
    }
    public void onDeleteContact(String name){
        try {
            sqlHelper.deleteContact(name);
        }catch (Exception e){
            isqlHelper.onMessager(e.getMessage());
        }
    }
    public void onDeleteAllContact(){
        try {
            sqlHelper.deleteAllContact();
        }catch (Exception e){
            isqlHelper.onMessager(e.getMessage());
        }
    }
    public void onEditContact(int amount,String name){
        try{
            sqlHelper.updateContact(amount, name);
        }catch (Exception e){
            isqlHelper.onMessager(e.getMessage());
        }
    }
}
