package com.example.h_gear;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.h_gear.databinding.ActivityMainBinding;
import com.example.h_gear.discount.DisCountFragment;
import com.example.h_gear.event.EtradeAddProduct;
import com.example.h_gear.home.MainFragment;

import com.example.h_gear.home.adapter.AdapterContactProduct;
import com.example.h_gear.home.cart.CartFragment;
import com.example.h_gear.location.LocationFragment;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.login.LoginFragment;
import com.example.h_gear.event.EtradeLogin;
import com.example.h_gear.personal.PersonalFragment;
import com.example.h_gear.personal.adapter.ProductOfCustomer;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.sql.SQLPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.example.h_gear.R.color.dark_gray;
import static com.example.h_gear.R.color.yellow;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    static public boolean isCheckLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragment(MainFragment.newInstance());
        binding.imgHome.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                binding.tvHome.setTextSize(14f);
                binding.tvHistory.setTextSize(11f);
                binding.tvSpecialOffers.setTextSize(11f);
                binding.tvLocation.setTextSize(11f);
                binding.tvLocation.setTextColor(getResources().getColor(dark_gray));
                binding.tvHome.setTextColor(getResources().getColor(yellow));
                binding.tvHistory.setTextColor(getResources().getColor(dark_gray));
                binding.tvSpecialOffers.setTextColor(getResources().getColor(R.color.dark_gray));
                binding.imgHome.getBackground().setTint(getResources().getColor(yellow));
                binding.imgPerson.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgLocation.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgSpecialOffers.getBackground().setTint(getResources().getColor(dark_gray));
                getFragment(MainFragment.newInstance());
            }
        });
        binding.imgSpecialOffers.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                binding.tvHome.setTextSize(11f);
                binding.tvHistory.setTextSize(11f);
                binding.tvSpecialOffers.setTextSize(14f);
                binding.tvLocation.setTextSize(11f);
                binding.tvLocation.setTextColor(getResources().getColor(dark_gray));
                binding.tvHome.setTextColor(getResources().getColor(dark_gray));
                binding.tvHistory.setTextColor(getResources().getColor(dark_gray));
                binding.tvSpecialOffers.setTextColor(getResources().getColor(yellow));
                binding.imgHome.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgPerson.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgLocation.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgSpecialOffers.getBackground().setTint(getResources().getColor(yellow));
                getFragment(DisCountFragment.newInstance());
            }
        });
        binding.imgLocation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                binding.tvHome.setTextSize(11f);
                binding.tvHistory.setTextSize(11f);
                binding.tvSpecialOffers.setTextSize(11f);
                binding.tvLocation.setTextSize(14f);
                binding.tvLocation.setTextColor(getResources().getColor(yellow));
                binding.tvHome.setTextColor(getResources().getColor(dark_gray));
                binding.tvHistory.setTextColor(getResources().getColor(dark_gray));
                binding.tvSpecialOffers.setTextColor(getResources().getColor(R.color.dark_gray));
                binding.imgHome.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgPerson.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgLocation.getBackground().setTint(getResources().getColor(yellow));
                binding.imgSpecialOffers.getBackground().setTint(getResources().getColor(dark_gray));
                getFragment(LocationFragment.newInstance());
            }
        });
        binding.imgPerson.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                binding.tvHome.setTextSize(11f);
                binding.tvHistory.setTextSize(14f);
                binding.tvSpecialOffers.setTextSize(11f);
                binding.tvLocation.setTextSize(11f);
                binding.tvLocation.setTextColor(getResources().getColor(dark_gray));
                binding.tvHome.setTextColor(getResources().getColor(dark_gray));
                binding.tvHistory.setTextColor(getResources().getColor(yellow));
                binding.tvSpecialOffers.setTextColor(getResources().getColor(R.color.dark_gray));
                binding.imgHome.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgPerson.getBackground().setTint(getResources().getColor(yellow));
                binding.imgLocation.getBackground().setTint(getResources().getColor(dark_gray));
                binding.imgSpecialOffers.getBackground().setTint(getResources().getColor(dark_gray));

                if(isCheckLogin == true){
                    getFragment(PersonalFragment.newInstance());
                }
                else getFragment(LoginFragment.newInstance());
            }
        });
        binding.imCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.tvShopCart.getText().equals("0")){
                    AlertDialog alertDialog =new AlertDialog.Builder(MainActivity.this).setTitle("Thông báo").setMessage("Giỏ hàng trống").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
                    alertDialog.show();
                }
                else{
                    binding.tvHome.setTextSize(11f);
                    binding.tvHistory.setTextSize(14f);
                    binding.tvSpecialOffers.setTextSize(11f);
                    binding.tvLocation.setTextSize(11f);
                    binding.tvLocation.setTextColor(getResources().getColor(dark_gray));
                    binding.tvHome.setTextColor(getResources().getColor(dark_gray));
                    binding.tvHistory.setTextColor(getResources().getColor(yellow));
                    binding.tvSpecialOffers.setTextColor(getResources().getColor(R.color.dark_gray));
                    binding.imgHome.getBackground().setTint(getResources().getColor(dark_gray));
                    binding.imgPerson.getBackground().setTint(getResources().getColor(yellow));
                    binding.imgLocation.getBackground().setTint(getResources().getColor(dark_gray));
                    binding.imgSpecialOffers.getBackground().setTint(getResources().getColor(dark_gray));
                    getFragment(CartFragment.newInstance());
                }
            }
        });
        if(isOnline() == false){
            AlertDialog alertDialog =new AlertDialog.Builder(MainActivity.this).setTitle("Thông báo").setMessage("Không có kết nối mạng").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create();
            alertDialog.show();
        }
    }
    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(EtradeLogin etrade){
        isCheckLogin = etrade.isCheck();
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void addProduct(EtradeAddProduct etrade){
        binding.tvShopCart.setText(Integer.parseInt(binding.tvShopCart.getText().toString())+etrade.getCount()+"");
    }

}