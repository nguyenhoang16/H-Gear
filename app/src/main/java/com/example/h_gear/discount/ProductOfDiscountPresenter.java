package com.example.h_gear.discount;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.h_gear.api.ApiService;
import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
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
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductOfDiscountPresenter {
    IProductOfDiscount iProductOfDiscount;
    Context context;
    List<ContactProductOfDiscount> contactProductOfDiscountList = new ArrayList<>();

    public ProductOfDiscountPresenter(IProductOfDiscount iProductOfDiscount) {
        this.iProductOfDiscount = iProductOfDiscount;
    }

    public void onShowListDiscount() {
        ApiService.apiService.getListDiscount().enqueue(new Callback<List<ContactProductOfDiscount>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfDiscount>> call, Response<List<ContactProductOfDiscount>> response) {
                insertContact(response.body());
                iProductOfDiscount.onShowList(contactProductOfDiscountList);
            }

            @Override
            public void onFailure(Call<List<ContactProductOfDiscount>> call, Throwable t) {

            }
        });
    }
    private void insertContact(List<ContactProductOfDiscount> listAPI){
        for(ContactProductOfDiscount contact : listAPI){
            contactProductOfDiscountList.add(contact);
        }
    }

}
