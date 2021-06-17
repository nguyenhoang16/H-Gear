package com.example.h_gear.discount.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.api.ApiService;
import com.example.h_gear.databinding.FragmentDetailBinding;
import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
import com.example.h_gear.event.EtradeAddProduct;
import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.details.contact_details.ContactDetailsPC;
import com.example.h_gear.event.Bus;
import com.example.h_gear.event.EtradeLogin;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.RegisterPresenter;
import com.example.h_gear.sql.ISQLHelper;
import com.example.h_gear.sql.SQLPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsDiscountListFragment extends Fragment implements ISQLHelper {
    FragmentDetailBinding binding;
    String name;
    boolean kt = true;
    SQLPresenter presenter;
    ContactDetailsPC contactDetailsPC;
    ContactProductOfDiscount contactProductOfDiscount = new ContactProductOfDiscount();
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    public static DetailsDiscountListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DetailsDiscountListFragment fragment = new DetailsDiscountListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false);
        presenter = new SQLPresenter(this,getContext());
        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                presenter.onShowListContact();
                if(kt == false){
                    Toast.makeText(getContext(),"Sản phẩm đã có trong giỏ hàng",Toast.LENGTH_LONG).show();
                    return;
                }
                int count = 1;
                Bus.getInstance().post(new EtradeAddProduct(count));
                String name = contactProductOfDiscount.getName();
                double price = Double.parseDouble(contactProductOfDiscount.getDisCount());
                int amount = 1;
                String image = contactProductOfDiscount.getImage();
                presenter.onSaveProductInCart(name,price,amount,image);
            }
        });
        return binding.getRoot();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getContactProduct(ContactProductOfDiscount con){
        name = con.getName();
        ApiService.apiService.getListDiscount().enqueue(new Callback<List<ContactProductOfDiscount>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfDiscount>> call, Response<List<ContactProductOfDiscount>> response) {
                checkProduct(response.body());
                decimalFormat = new DecimalFormat(pattern);
                Double price = Double.parseDouble(contactProductOfDiscount.getDisCount());
                binding.tvName.setText(contactProductOfDiscount.getName());
                binding.tvOldPrice.setText(decimalFormat.format(price));
                binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
                Glide.with(getContext()).load(contactProductOfDiscount.getImage()).into(binding.imProduct);
            }

            @Override
            public void onFailure(Call<List<ContactProductOfDiscount>> call, Throwable t) {

            }
        });
        ApiService.apiService.getDetailDiscount().enqueue(new Callback<List<ContactDetailsPC>>() {
            @Override
            public void onResponse(Call<List<ContactDetailsPC>> call, Response<List<ContactDetailsPC>> response) {
                checkDetail(response.body());
                binding.tvCPU.setText(contactDetailsPC.getCPU());
                binding.tvMonitor.setText(contactDetailsPC.getMonitor());
                binding.tvKeyBoard.setText(contactDetailsPC.getKeyBoard());
                binding.tvConnector.setText(contactDetailsPC.getConnectors());
                binding.tvGPU.setText(contactDetailsPC.getGPU());
                binding.tvOS.setText(contactDetailsPC.getOS());
                binding.tvLan.setText(contactDetailsPC.getLan());
                binding.tvWireLess.setText(contactDetailsPC.getWirelessLan());
                binding.tvRAM.setText(contactDetailsPC.getRAM());
                binding.tvSSD.setText(contactDetailsPC.getSSD());
                binding.tvWeight.setText(contactDetailsPC.getWeight());
            }

            @Override
            public void onFailure(Call<List<ContactDetailsPC>> call, Throwable t) {

            }
        });
    }
    public void checkProduct(List<ContactProductOfDiscount> listAPI){
        for(ContactProductOfDiscount contact : listAPI){
            if(contact.getName().equals(name)){
                contactProductOfDiscount = contact;
            }
        }
    }
    public void checkDetail(List<ContactDetailsPC> listAPI){
        for(ContactDetailsPC contactDetail : listAPI){
            if(contactDetail.getName().equals(name)){
                contactDetailsPC = contactDetail;
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Bus.getInstance().register(getContext());
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Bus.getInstance().unRegister(getContext());
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccessful() {
        Toast.makeText(getContext(),"Đã thêm "+contactProductOfDiscount.getName()+" vào giỏ hàng",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessager(String mes) {
        Toast.makeText(getContext(),mes,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateView(List<ContactCart> contactCartList) {
        for(ContactCart con : contactCartList){
            if(con.getName().equals(contactProductOfDiscount.getName())){
                kt = false;
            }
        }
    }

}
