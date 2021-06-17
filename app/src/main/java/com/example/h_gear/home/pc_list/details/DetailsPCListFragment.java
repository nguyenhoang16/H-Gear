package com.example.h_gear.home.pc_list.details;

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
import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.details.contact_details.ContactDetailsLaptop;
import com.example.h_gear.home.details.contact_details.ContactDetailsPC;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.pc_list.adapter.ContactProductOfPC;
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

public class DetailsPCListFragment extends Fragment implements ISQLHelper {
    FragmentDetailBinding binding;
    String name;
    boolean kt = true;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    SQLPresenter presenter;
    ContactDetailsPC contactDetailsPC;
    ContactProductOfPC contactProductOfPC = new ContactProductOfPC();

    public static DetailsPCListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DetailsPCListFragment fragment = new DetailsPCListFragment();
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
                String name = contactProductOfPC.getName();
                double price = Double.parseDouble(contactProductOfPC.getOldPrice());
                int amount = 1;
                String image = contactProductOfPC.getImage();
                presenter.onSaveProductInCart(name,price,amount,image);
            }
        });
        return binding.getRoot();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getContactProduct(ContactProductOfPC con){
        name = con.getName();
        ApiService.apiService.getListPC().enqueue(new Callback<List<ContactProductOfPC>>() {
            @Override
            public void onResponse(Call<List<ContactProductOfPC>> call, Response<List<ContactProductOfPC>> response) {
                checkProduct(response.body());
                decimalFormat = new DecimalFormat(pattern);
                Double price = Double.parseDouble(contactProductOfPC.getOldPrice());
                binding.tvName.setText(contactProductOfPC.getName());
                binding.tvOldPrice.setText(decimalFormat.format(price));
                binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
                Glide.with(getContext()).load(contactProductOfPC.getImage()).into(binding.imProduct);
            }

            @Override
            public void onFailure(Call<List<ContactProductOfPC>> call, Throwable t) {

            }
        });
        ApiService.apiService.getDetailPC().enqueue(new Callback<List<ContactDetailsPC>>() {
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
    public void checkProduct(List<ContactProductOfPC> listAPI){
        for(ContactProductOfPC contact : listAPI){
            if(contact.getName().equals(name)){
                contactProductOfPC = contact;
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
        Toast.makeText(getContext(),"Đã thêm "+contactProductOfPC.getName()+" vào giỏ hàng",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessager(String mes) {
        Toast.makeText(getContext(),mes,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateView(List<ContactCart> contactCartList) {
        for(ContactCart con : contactCartList){
            if(con.getName().equals(contactProductOfPC.getName())){
                kt = false;
            }
        }
    }
}
