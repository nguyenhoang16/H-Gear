package com.example.h_gear.home.laptop_list;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.h_gear.api.ApiService;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductOfLaptopListPresenter {
    IProductOfLaptopList iProductOfLaptopList;
    Context context;
    List<ContactProductOfLaptop> contactProductOfLaptopList = new ArrayList<>();

    public ProductOfLaptopListPresenter(IProductOfLaptopList iProductOfLaptopList) {
        this.iProductOfLaptopList = iProductOfLaptopList;
    }

    public void onShowListLaptop() {
        ApiService.apiService.getListLaptop().enqueue(new Callback<List<ContactProductOfLaptop>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfLaptop>> call, Response<List<ContactProductOfLaptop>> response) {
                insertContact(response.body());
                iProductOfLaptopList.onShowList(contactProductOfLaptopList);
            }

            @Override
            public void onFailure(Call<List<ContactProductOfLaptop>> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertContact(List<ContactProductOfLaptop> listAPI){
        for(ContactProductOfLaptop contact : listAPI){
            contactProductOfLaptopList.add(contact);
        }
    }

}
