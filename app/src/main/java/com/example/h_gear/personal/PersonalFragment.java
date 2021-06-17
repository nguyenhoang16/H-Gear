package com.example.h_gear.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_gear.MainActivity;
import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentPersonalBinding;
import com.example.h_gear.home.cart.adapter.AdapterContactCart;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.login.LoginFragment;
import com.example.h_gear.personal.adapter.AdapterContactOfCustomer;
import com.example.h_gear.personal.adapter.ProductOfCustomer;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class PersonalFragment extends Fragment implements ISQLRegister {
    FragmentPersonalBinding binding;
    RegisterPresenter presenter;
    AdapterContactOfCustomer adapter;

    public static PersonalFragment newInstance() {

        Bundle args = new Bundle();

        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_personal,container,false);
        presenter = new RegisterPresenter(this,getContext());
        binding.tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.isCheckLogin = false;
                Toast.makeText(getContext(),"Bạn đã đăng xuất",Toast.LENGTH_SHORT).show();
                getFragment(LoginFragment.newInstance());
            }
        });
        return binding.getRoot();
    }
    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getAccount(String name){
        presenter.checkCustomer(name);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccessful() {

    }

    @Override
    public void onMessager(String mes) {

    }

    @Override
    public void onShowAccount(ContactAccount contactAccount) {
        binding.tvNameOfCustomer.setText("Xin chào "+ contactAccount.getName()+"!");
        binding.tvUserName.setText("Tên tài khoản: "+contactAccount.getUsername());
        binding.tvUserName.setText("Số điện thoại: "+contactAccount.getSdt());
        presenter.onShowListContact(contactAccount.getUsername());
    }

    @Override
    public void onShowContact(List<ProductOfCustomer> productOfCustomerList) {
        adapter = new AdapterContactOfCustomer(productOfCustomerList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.rcProductOfCustomer.setAdapter(adapter);
        binding.rcProductOfCustomer.setLayoutManager(layoutManager);
    }
}

