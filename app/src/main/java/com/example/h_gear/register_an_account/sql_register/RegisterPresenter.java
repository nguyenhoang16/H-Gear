package com.example.h_gear.register_an_account.sql_register;

import android.content.Context;

import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.personal.adapter.ProductOfCustomer;

import java.util.List;

public class RegisterPresenter {
    ISQLRegister isqlRegister;
    Context context;
    SQLRegisterHelper sqlHelper;

    public RegisterPresenter() {
    }

    public RegisterPresenter(ISQLRegister isqlRegister, Context context) {
        this.isqlRegister = isqlRegister;
        this.context = context;
    }
    public void onSaveAccount(String nameOfCustomer,String phoneOfCustomer, String username, String password){
        try{
            if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
            sqlHelper.insertContact(nameOfCustomer,phoneOfCustomer,username,password);
            isqlRegister.onSuccessful();
        }catch (Exception e){
            isqlRegister.onMessager(e.getMessage());
        }
    }
    public void checkAccount(String username, String password){
        if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
        ContactAccount contactAccount =sqlHelper.getAccount(username,password);
        isqlRegister.onShowAccount(contactAccount);
    }
    public void checkCustomer(String username){
        if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
        ContactAccount contactAccount =sqlHelper.getCustomer(username);
        isqlRegister.onShowAccount(contactAccount);
    }
    public void onSaveProduct(String userName,String nameProduct,String image, int amount, double price,String date){
        try{
            if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
            sqlHelper.insertContactInAccount(userName,nameProduct,image,amount,price,date);
            isqlRegister.onSuccessful();
        }catch (Exception e){
            isqlRegister.onMessager(e.getMessage());
        }
    }
    public void onShowListContact(String username){
        if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
        List<ProductOfCustomer> contactList = sqlHelper.getHistory(username);
        isqlRegister.onShowContact(contactList);
    }
    public boolean checkUsername(String username){
        if(sqlHelper == null)sqlHelper = new SQLRegisterHelper(context);
        return sqlHelper.checkUser(username);
    }
}
