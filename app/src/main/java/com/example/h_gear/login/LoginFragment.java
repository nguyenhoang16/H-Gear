package com.example.h_gear.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentLoginBinding;
import com.example.h_gear.event.Bus;
import com.example.h_gear.event.EtradeAccount;
import com.example.h_gear.event.EtradeLogin;
import com.example.h_gear.personal.PersonalFragment;
import com.example.h_gear.personal.adapter.ProductOfCustomer;
import com.example.h_gear.register_an_account.FragmentRegister;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class LoginFragment extends Fragment implements ISQLRegister {
    FragmentLoginBinding binding;
    RegisterPresenter presenter;
    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
        presenter = new RegisterPresenter(this,getContext());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edPassword.getText().toString().equals("") || binding.edUsername.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Chưa nhập thông tin", Toast.LENGTH_LONG).show();
                }
                else {
                    presenter.checkAccount(binding.edUsername.getText().toString(),binding.edPassword.getText().toString());

                }
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment(FragmentRegister.newInstance());
            }
        });
        return binding.getRoot();
    }

    private void closefragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
    @Override
    public void onStart() {
        super.onStart();
        Bus.getInstance().register(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        Bus.getInstance().unRegister(getContext());
    }

    @Override
    public void onSuccessful() {
        Toast.makeText(getContext(),"OK",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessager(String mes) {
        Toast.makeText(getContext(),mes,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowAccount(ContactAccount contactAccount) {
        if(contactAccount.username.equals("")||contactAccount.password.equals("")){
            Toast.makeText(getContext(),"Tên tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(),"Login thành công",Toast.LENGTH_LONG).show();
            Bus.getInstance().checkLogin(new EtradeLogin(true));
            EventBus.getDefault().postSticky(contactAccount.getUsername()+"");
            closefragment(PersonalFragment.newInstance());
        }
    }

    @Override
    public void onShowContact(List<ProductOfCustomer> productOfCustomerList) {

    }

}
