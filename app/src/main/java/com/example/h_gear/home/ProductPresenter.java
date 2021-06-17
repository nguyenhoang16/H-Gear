package com.example.h_gear.home;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.h_gear.api.ApiService;
import com.example.h_gear.discount.ProductOfDiscountPresenter;
import com.example.h_gear.home.adapter.ContactProduct;
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

public class ProductPresenter {
    IProduct iProduct;
    List<ContactProduct> contactProductListPC = new ArrayList<>();
    List<ContactProduct> contactProductListLapTop = new ArrayList<>();
    public ProductPresenter(IProduct iProduct) {
        this.iProduct = iProduct;
    }

    public void disPlayListLapTop(){

        ApiService.apiService.getListViewLapTop().enqueue(new Callback<List<ContactProduct>>() {
            @Override
            public void onResponse(Call<List<ContactProduct>> call, Response<List<ContactProduct>> response) {
                insertContactLapTop(response.body());
                iProduct.onUpdateViewLaptop(contactProductListLapTop);
            }

            @Override
            public void onFailure(Call<List<ContactProduct>> call, Throwable t) {

            }
        });
    }
    private void insertContactLapTop(List<ContactProduct> listAPI){
        for(ContactProduct contact : listAPI){
            contactProductListLapTop.add(contact);
        }
    }
    public void disPlayListPC() {

        ApiService.apiService.getListViewPC().enqueue(new Callback<List<ContactProduct>>() {
            @Override
            public void onResponse(Call<List<ContactProduct>> call, Response<List<ContactProduct>> response) {
                insertContactPC(response.body());
                iProduct.onUpdateViewPC(contactProductListPC);
            }

            @Override
            public void onFailure(Call<List<ContactProduct>> call, Throwable t) {

            }
        });
    }
    private void insertContactPC(List<ContactProduct> listAPI){
        for(ContactProduct contact : listAPI){
            contactProductListPC.add(contact);
        }
    }



}
