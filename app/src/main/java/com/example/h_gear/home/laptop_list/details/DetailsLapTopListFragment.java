package com.example.h_gear.home.laptop_list.details;

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
import com.example.h_gear.event.Bus;
import com.example.h_gear.event.EtradeAddProduct;
import com.example.h_gear.home.adapter.ContactProduct;
import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.details.contact_details.ContactDetailsLaptop;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
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

public class DetailsLapTopListFragment extends Fragment implements ISQLHelper {
    FragmentDetailBinding binding;
    String name;
    boolean kt = true;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    SQLPresenter presenter;
    ContactDetailsLaptop contactDetailsLaptop;
    ContactProductOfLaptop contactProductOfLaptop = new ContactProductOfLaptop();

    public static DetailsLapTopListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DetailsLapTopListFragment fragment = new DetailsLapTopListFragment();
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
                String name = contactProductOfLaptop.getName();
                double price = Double.parseDouble(contactProductOfLaptop.getOldPrice());
                int amount = 1;
                String image = contactProductOfLaptop.getImage();
                presenter.onSaveProductInCart(name,price,amount,image);
            }
        });
        return binding.getRoot();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getContactProduct(ContactProductOfLaptop con){
        name = con.getName();
        ApiService.apiService.getListLaptop().enqueue(new Callback<List<ContactProductOfLaptop>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfLaptop>> call, Response<List<ContactProductOfLaptop>> response) {
                checkProduct(response.body());
                decimalFormat = new DecimalFormat(pattern);
                Double price = Double.parseDouble(contactProductOfLaptop.getOldPrice());
                binding.tvName.setText(contactProductOfLaptop.getName());
                binding.tvOldPrice.setText(decimalFormat.format(price));
                binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
                Glide.with(getContext()).load(contactProductOfLaptop.getImage()).into(binding.imProduct);
            }

            @Override
            public void onFailure(Call<List<ContactProductOfLaptop>> call, Throwable t) {

            }
        });
        ApiService.apiService.getDetailLaptop().enqueue(new Callback<List<ContactDetailsLaptop>>() {
            @Override
            public void onResponse(Call<List<ContactDetailsLaptop>> call, Response<List<ContactDetailsLaptop>> response) {
                checkDetail(response.body());
                binding.tvCPU.setText(contactDetailsLaptop.getCPU());
                binding.tvMonitor.setText(contactDetailsLaptop.getMonitor());
                binding.tvKeyBoard.setText(contactDetailsLaptop.getKeyBoard());
                binding.tvConnector.setText(contactDetailsLaptop.getConnectors());
                binding.tvGPU.setText(contactDetailsLaptop.getGPU());
                binding.tvOS.setText(contactDetailsLaptop.getOS());
                binding.tvLan.setText(contactDetailsLaptop.getLan());
                binding.tvWireLess.setText(contactDetailsLaptop.getWirelessLan());
                binding.tvRAM.setText(contactDetailsLaptop.getRAM());
                binding.tvSSD.setText(contactDetailsLaptop.getSSD());
                binding.tvWeight.setText(contactDetailsLaptop.getWeight());
            }

            @Override
            public void onFailure(Call<List<ContactDetailsLaptop>> call, Throwable t) {

            }
        });
    }
    public void checkProduct(List<ContactProductOfLaptop> listAPI){
        for(ContactProductOfLaptop contact : listAPI){
            if(contact.getName().equals(name)){
                contactProductOfLaptop = contact;
            }
        }
    }
    public void checkDetail(List<ContactDetailsLaptop> listAPI){
        for(ContactDetailsLaptop contactDetail : listAPI){
            if(contactDetail.getName().equals(name)){
                contactDetailsLaptop = contactDetail;
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
        Toast.makeText(getContext(),"Đã thêm "+contactProductOfLaptop.getName()+" vào giỏ hàng",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessager(String mes) {
        Toast.makeText(getContext(),mes,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateView(List<ContactCart> contactCartList) {
        for(ContactCart con : contactCartList){
            if(con.getName().equals(contactProductOfLaptop.getName())){
                kt = false;
            }
        }
    }
}
