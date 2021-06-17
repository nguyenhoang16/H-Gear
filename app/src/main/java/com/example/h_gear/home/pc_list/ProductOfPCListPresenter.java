package com.example.h_gear.home.pc_list;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.h_gear.api.ApiService;

import com.example.h_gear.home.laptop_list.IProductOfLaptopList;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.pc_list.adapter.ContactProductOfPC;

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

public class ProductOfPCListPresenter {
    IProductOfPCList iProductOfPCList;
    Context context;
    List<ContactProductOfPC> contactProductOfPCList = new ArrayList<>();

    public ProductOfPCListPresenter(IProductOfPCList iProductOfPCList) {
        this.iProductOfPCList = iProductOfPCList;
    }

    public void onShowListPC() {
        ApiService.apiService.getListPC().enqueue(new Callback<List<ContactProductOfPC>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfPC>> call, Response<List<ContactProductOfPC>> response) {
                insertContact(response.body());
                iProductOfPCList.onShowList(contactProductOfPCList);

            }

            @Override
            public void onFailure(Call<List<ContactProductOfPC>> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void insertContact(List<ContactProductOfPC> listAPI){
        for(ContactProductOfPC contact : listAPI){
            contactProductOfPCList.add(contact);
        }
    }
}
