package com.example.h_gear.home.laptop_list;

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

import com.example.h_gear.R;

import com.example.h_gear.databinding.FragmentLaptopListBinding;

import com.example.h_gear.home.details.DetailsLapTopFragment;
import com.example.h_gear.home.laptop_list.adapter.AdapterContactProductOfLaptop;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.laptop_list.details.DetailsLapTopListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class LaptopListFragment extends Fragment implements IProductOfLaptopList{
    FragmentLaptopListBinding binding;
    ProductOfLaptopListPresenter presenter;
    AdapterContactProductOfLaptop adapterContactProductOfLaptop;
    public static LaptopListFragment newInstance() {

        Bundle args = new Bundle();

        LaptopListFragment fragment = new LaptopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_laptop_list,container,false);
        presenter = new ProductOfLaptopListPresenter(this);
        presenter.onShowListLaptop();
        return binding.getRoot();
    }

    @Override
    public void onShowList(List<ContactProductOfLaptop> contactList) {
        adapterContactProductOfLaptop = new AdapterContactProductOfLaptop(contactList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.rcViewLaptopList.setAdapter(adapterContactProductOfLaptop);
        binding.rcViewLaptopList.setLayoutManager(layoutManager);
        adapterContactProductOfLaptop.setIonItemClickContact(new IonItemClickLaptop() {
            @Override
            public void onImageListLapTop(ContactProductOfLaptop contactProductOfLaptop) {
                EventBus.getDefault().postSticky(contactProductOfLaptop);
                getFragment(DetailsLapTopListFragment.newInstance());
            }
        });
    }
    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}
