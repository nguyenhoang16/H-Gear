package com.example.h_gear.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.h_gear.home.cart.adapter.ContactCart;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "ListContactInCart.db";
    static final String DB_TABLE_CONTACT = "Contact";
    static final int DB_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE Contact(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nameOfProduct TEXT," +
                "priceOfProduct DOUBLE," +
                "amount INTEGER," +
                "image TEXT)";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!= oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_CONTACT);
            onCreate(db);
        }
    }
    public void insertProductInCart(String nameOfProduct,double priceOfProduct, int amount, String image){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nameOfProduct",nameOfProduct);
        contentValues.put("priceOfProduct",priceOfProduct);
        contentValues.put("amount",amount);
        contentValues.put("image",image);

        sqLiteDatabase.insert(DB_TABLE_CONTACT,null,contentValues);
    }
    public List<ContactCart> getAllContact(){
        List<ContactCart> contactList = new ArrayList<>();
        ContactCart contact;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_CONTACT,
                null,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("nameOfProduct"));
            double price = cursor.getDouble(cursor.getColumnIndex("priceOfProduct"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            contact = new ContactCart(name,image,price,amount);
            contactList.add(contact);
        }
        return contactList;
    }
    public void deleteContact(String name){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_CONTACT,"nameOfProduct=?",new String[]{String.valueOf(name)});
    }
    public void deleteAllContact(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_CONTACT,null,null);
    }
    public void updateContact(int amount,String nameOfProduct){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("priceOfProduct",priceOfProduct);
        contentValues.put("amount",amount);
        //contentValues.put("image",image);
        sqLiteDatabase.update(DB_TABLE_CONTACT,contentValues,"nameOfProduct=?",new String[]{nameOfProduct});
    }
}
