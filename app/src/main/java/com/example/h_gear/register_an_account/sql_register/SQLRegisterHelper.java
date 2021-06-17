package com.example.h_gear.register_an_account.sql_register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.personal.adapter.ProductOfCustomer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SQLRegisterHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "ListAccount.db";
    static final String DB_TABLE_CONTACT = "Contact";
    static final String DB_TABLE_CUSTOMER = "Customer";
    static final int DB_VERSION = 1;

    public SQLRegisterHelper(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE Contact(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nameOfCustomer TEXT," +
                "phoneOfCustomer TEXT," +
                "username TEXT," +
                "password TEXT)";
        db.execSQL(queryCreateTable);
        String queryCreateTable1 = "CREATE TABLE Customer(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username TEXT,"+
                "nameProduct TEXT,"+
                "image TEXT,"+
                "amount INTERGER,"+
                "price DOUBLE,"+
                "date TEXT)";
        db.execSQL(queryCreateTable1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!= oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_CONTACT);
            onCreate(db);
        }
    }
    public void insertContact(String nameOfCustomer, String phoneOfCustomer, String username, String password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nameOfCustomer",nameOfCustomer);
        contentValues.put("phoneOfCustomer",phoneOfCustomer);
        contentValues.put("username",username);
        contentValues.put("password",password);

        sqLiteDatabase.insert(DB_TABLE_CONTACT,null,contentValues);
    }
    public ContactAccount getAccount(String username, String password){
        ContactAccount contactAccount;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_CONTACT,
                null,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            String usernameInSQL = cursor.getString(cursor.getColumnIndex("username"));
            String passwordInSQL = cursor.getString(cursor.getColumnIndex("password"));
            if(username.equals(usernameInSQL)){
                if(password.equals(passwordInSQL)){
                    String name = cursor.getString(cursor.getColumnIndex("nameOfCustomer"));
                    String sdt = cursor.getString(cursor.getColumnIndex("phoneOfCustomer"));
                    contactAccount = new ContactAccount(name,usernameInSQL,passwordInSQL,sdt);
                    return contactAccount;
                }
            }
        }
        return contactAccount = new ContactAccount("","","","");
    }
    public ContactAccount getCustomer(String username){
        ContactAccount contactAccount = new ContactAccount();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_CONTACT,
                null,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            String usernameInSQL = cursor.getString(cursor.getColumnIndex("username"));
            if(username.equals(usernameInSQL)){
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String name = cursor.getString(cursor.getColumnIndex("nameOfCustomer"));
                String sdt = cursor.getString(cursor.getColumnIndex("phoneOfCustomer"));
                contactAccount = new ContactAccount(name,usernameInSQL,password,sdt);
            }
        }
        return contactAccount;
    }
    /////
    public void insertContactInAccount(String username,String nameProduct,String image, int amount, double price,String date){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("nameProduct",nameProduct);
        contentValues.put("image",image);
        contentValues.put("amount",amount);
        contentValues.put("price",price);
        contentValues.put("date",date);

        sqLiteDatabase.insert(DB_TABLE_CUSTOMER,null,contentValues);
    }
    public List<ProductOfCustomer> getHistory(String username){
        List<ProductOfCustomer> productOfCustomerList = new ArrayList<>();
        ProductOfCustomer productOfCustomer;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_CUSTOMER,
                null,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            String usernameInSQL = cursor.getString(cursor.getColumnIndex("username"));
            if(usernameInSQL.equals(username)){
                String nameProduct = cursor.getString(cursor.getColumnIndex("nameProduct"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                int amount = cursor.getInt(cursor.getColumnIndex("amount"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                productOfCustomer = new ProductOfCustomer(username,nameProduct,image,amount,price,date);
                productOfCustomerList.add(productOfCustomer);
            }
        }
        return productOfCustomerList;
    }
    public boolean checkUser(String username){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_CUSTOMER,
                null,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            String usernameInSQL = cursor.getString(cursor.getColumnIndex("username"));
            if(usernameInSQL.equals(username)){
                return true;
            }
        }
        return false;
    }
}
