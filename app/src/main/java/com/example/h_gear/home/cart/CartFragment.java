package com.example.h_gear.home.cart;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_gear.MainActivity;
import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentCartBinding;
import com.example.h_gear.databinding.FragmentLoginBinding;
import com.example.h_gear.event.Bus;
import com.example.h_gear.event.EtradeAddProduct;
import com.example.h_gear.home.MainFragment;
import com.example.h_gear.home.cart.adapter.AdapterContactCart;
import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.cart.adapter.IonClickContact;
import com.example.h_gear.login.ContactAccount;
import com.example.h_gear.login.LoginFragment;
import com.example.h_gear.personal.PersonalFragment;
import com.example.h_gear.personal.adapter.ProductOfCustomer;
import com.example.h_gear.register_an_account.sql_register.ISQLRegister;
import com.example.h_gear.register_an_account.sql_register.RegisterPresenter;
import com.example.h_gear.register_an_account.sql_register.SQLRegisterHelper;
import com.example.h_gear.sql.ISQLHelper;
import com.example.h_gear.sql.SQLPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment implements ISQLHelper, ISQLRegister {
    FragmentCartBinding binding;
    SQLPresenter presenter;
    AdapterContactCart adapterContactCart;
    Double tong = 0.0;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    RegisterPresenter registerPresenter;
    int countCart = 0;
    List<ContactCart> contactCarts = new ArrayList<>();
    ContactAccount contactAccount;

    public static CartFragment newInstance() {

        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container,false);
        presenter = new SQLPresenter(this,getContext());
        registerPresenter = new RegisterPresenter(this,getContext());
        presenter.onShowListContact();
        decimalFormat = new DecimalFormat(pattern);
        binding.tvTong.setText(decimalFormat.format(tong));
        binding.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.isCheckLogin == false)
                {
                    AlertDialog alertDialog =new AlertDialog.Builder(getContext()).setTitle("Thông báo").setMessage("Bạn cần phải đăng nhập").setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getFragment(LoginFragment.newInstance());
                        }
                    }).create();
                    alertDialog.show();
                }
                else{
                    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    for(ContactCart con : contactCarts){
                        registerPresenter.onSaveProduct(contactAccount.getUsername(),con.getName(),con.getImage(),con.getCount(),con.getPrice(),currentDate);
                    }
                    presenter.onDeleteAllContact();
                    Toast.makeText(getContext(),"Cảm ơn bạn đã mua hàng",Toast.LENGTH_LONG).show();
                    Bus.getInstance().post(new EtradeAddProduct(-countCart));
                    getFragment(PersonalFragment.newInstance());
                }
            }
        });

        return binding.getRoot();
    }

    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
    @Override
    public void onSuccessful() {

    }

    @Override
    public void onMessager(String mes) {

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getAccount(String name){
        registerPresenter.checkCustomer(name);
    }
    @Override
    public void onShowAccount(ContactAccount contactAccount) {
        this.contactAccount = contactAccount;
    }

    @Override
    public void onShowContact(List<ProductOfCustomer> productOfCustomerList) {

    }

    @Override
    public void onUpdateView(List<ContactCart> contactCartList) {
        adapterContactCart = new AdapterContactCart(contactCartList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.rcViewCart.setAdapter(adapterContactCart);
        binding.rcViewCart.setLayoutManager(layoutManager);
        for(ContactCart con : contactCartList){
            tong+= con.getPrice()*con.getCount();
            countCart+=con.getCount();
            contactCarts.add(con);

        }
        adapterContactCart.setIsqlHelper(new IonClickContact() {
            @Override
            public void onBtnDelete(ContactCart contactCart) {
                int count = -contactCart.getCount();
                Bus.getInstance().post(new EtradeAddProduct(count));
                presenter.onDeleteContact(contactCart.getName());
                getFragment(CartFragment.newInstance());
            }

            @Override
            public void onBtnUp(ContactCart contactCart) {
                int amount = contactCart.getCount() + 1;
                presenter.onEditContact(amount,contactCart.getName());
                int count = 1;
                Bus.getInstance().post(new EtradeAddProduct(count));
                getFragment(CartFragment.newInstance());
            }

            @Override
            public void onBtnDown(ContactCart contactCart) {
                int amount = contactCart.getCount() - 1;
                presenter.onEditContact(amount,contactCart.getName());
                int count = -1;
                Bus.getInstance().post(new EtradeAddProduct(count));
                getFragment(CartFragment.newInstance());
            }
        });
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

}


