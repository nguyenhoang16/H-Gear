package com.example.h_gear.register_an_account;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.h_gear.MainActivity;
import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentRegisterAnAccountBinding;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.login.LoginFragment;
import com.example.h_gear.personal.adapter.ProductOfCustomer;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.RegisterPresenter;

import java.util.List;

public class FragmentRegister extends Fragment implements ISQLRegister {
    FragmentRegisterAnAccountBinding binding;
    RegisterPresenter presenter;
    boolean kt;
    public static FragmentRegister newInstance() {

        Bundle args = new Bundle();

        FragmentRegister fragment = new FragmentRegister();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_an_account,container,false);
        presenter = new RegisterPresenter(this,getContext());
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOfCustomer = binding.edNameOfCustomer.getText().toString();
                String phoneOfCustomer = binding.edPhoneOfCustomer.getText().toString();
                String username = binding.edUsernameRegister.getText().toString();
                String password = binding.edPasswordRegister.getText().toString();
                String confirmPassword = binding.edConfirmPassWord.getText().toString();
                kt = presenter.checkUsername(username);
                if (binding.edUsernameRegister.getText().toString().equals("")||binding.edPhoneOfCustomer.getText().toString().equals("")||binding.edPasswordRegister.getText().toString().equals("")||binding.edNameOfCustomer.getText().toString().equals("")||binding.edConfirmPassWord.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Không được để trống",Toast.LENGTH_LONG).show();
                }
                else if(kt == true){
                    AlertDialog alertDialog =new AlertDialog.Builder(getContext()).setTitle("Thông báo").setMessage("Tên tài khoản đã tồn tại").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
                    alertDialog.show();
                }
                else if(password.length() <8){
                    AlertDialog alertDialog =new AlertDialog.Builder(getContext()).setTitle("Thông báo").setMessage("Mật khẩu phải lớn hơn 8 ký tự").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
                    alertDialog.show();
                }
                else if(password.compareTo(confirmPassword) != 0){
                    AlertDialog alertDialog =new AlertDialog.Builder(getContext()).setTitle("Thông báo").setMessage("Xác nhận mật khẩu không khớp").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
                    alertDialog.show();
                }
                else {
                    presenter.onSaveAccount(nameOfCustomer,phoneOfCustomer,username,password);
                    closefragment(LoginFragment.newInstance());
                }
            }
        });
        return binding.getRoot();
    }
    private void closefragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onSuccessful() {
        Toast.makeText(getContext(),"Đăng kí tài khoản thành công",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessager(String mes) {
        Toast.makeText(getContext(),mes,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowAccount(ContactAccount contactAccount) {

    }

    @Override
    public void onShowContact(List<ProductOfCustomer> productOfCustomerList) {

    }
}
