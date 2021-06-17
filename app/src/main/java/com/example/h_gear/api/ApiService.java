package com.example.h_gear.api;

import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
import com.example.h_gear.home.adapter.ContactProduct;
import com.example.h_gear.home.details.contact_details.ContactDetailsLaptop;
import com.example.h_gear.home.details.contact_details.ContactDetailsPC;

import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.pc_list.adapter.ContactProductOfPC;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder().baseUrl("https://demo6461759.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.class);
    @GET("laptop")
    Call<List<ContactProduct>> getListViewLapTop();
    @GET("pc")
    Call<List<ContactProduct>> getListViewPC();
    @GET("laptop")
    Call<List<ContactProductOfLaptop>> getListLaptop();
    @GET("pc")
    Call<List<ContactProductOfPC>> getListPC();

    @GET("specialOffers")
    Call<List<ContactProductOfDiscount>> getListDiscount();
    @GET("pc")
    Call<List<ContactDetailsPC>> getDetailPC();
    @GET("laptop")
    Call<List<ContactDetailsLaptop>> getDetailLaptop();
    @GET("specialOffers")
    Call<List<ContactDetailsPC>> getDetailDiscount();
}


