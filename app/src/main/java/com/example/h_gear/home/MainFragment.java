package com.example.h_gear.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_gear.MainActivity;
import com.example.h_gear.databinding.ActivityMainBinding;
import com.example.h_gear.home.adapter.AdapterContactProduct;
import com.example.h_gear.home.adapter.ContactProduct;
import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentMainBinding;

import com.example.h_gear.home.cart.adapter.ContactCart;
import com.example.h_gear.home.details.DetailsLapTopFragment;
import com.example.h_gear.home.details.DetailsPCFragment;
import com.example.h_gear.home.laptop_list.LaptopListFragment;
import com.example.h_gear.home.pc_list.PCListFragment;
import com.example.h_gear.event.Bus;
import com.example.h_gear.sql.ISQLHelper;
import com.example.h_gear.sql.SQLPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainFragment extends Fragment implements IProduct {
    FragmentMainBinding binding;
    AdapterContactProduct adapterContactProduct;
    ProductPresenter presenter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        presenter = new ProductPresenter(this);
        presenter.disPlayListPC();
        presenter.disPlayListLapTop();
        AnimationDrawable animationDrawable = (AnimationDrawable) binding.lvImage.getDrawable();
        animationDrawable.start();


        binding.imLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(LaptopListFragment.newInstance());
            }
        });
        binding.imPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(PCListFragment.newInstance());
            }
        });
        binding.imPCof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(PCListFragment.newInstance());
            }
        });


        return binding.getRoot();
    }

    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
    @Override
    public void onUpdateViewLaptop(List<ContactProduct> contactList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adapterContactProduct = new AdapterContactProduct(contactList,getContext());
        binding.rcViewLapTop.setAdapter(adapterContactProduct);
        binding.rcViewLapTop.setLayoutManager(layoutManager);
        adapterContactProduct.setIonItemClickContact(new IonItemClickContact() {
            @Override
            public void onImage(ContactProduct contactProduct) {
                EventBus.getDefault().postSticky(contactProduct);
                getFragment(DetailsLapTopFragment.newInstance());
            }
        });
    }

    @Override
    public void onUpdateViewPC(List<ContactProduct> contactList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adapterContactProduct = new AdapterContactProduct(contactList,getContext());
        binding.rcViewPC.setAdapter(adapterContactProduct);
        binding.rcViewPC.setLayoutManager(layoutManager);
        adapterContactProduct.setIonItemClickContact(new IonItemClickContact() {
            @Override
            public void onImage(ContactProduct contactProduct) {
                EventBus.getDefault().postSticky(contactProduct);
                getFragment(DetailsPCFragment.newInstance());
            }
        });

    }

    @Override
    public void onUpdateViewGamingGear(List<ContactProduct> contactList) {

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

}
