package com.example.h_gear.home.pc_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentLaptopListBinding;
import com.example.h_gear.databinding.FragmentPcListBinding;
import com.example.h_gear.databinding.ItemContactPcListBinding;
import com.example.h_gear.home.laptop_list.IProductOfLaptopList;
import com.example.h_gear.home.laptop_list.ProductOfLaptopListPresenter;
import com.example.h_gear.home.laptop_list.adapter.AdapterContactProductOfLaptop;
import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.laptop_list.details.DetailsLapTopListFragment;
import com.example.h_gear.home.pc_list.adapter.AdapterContactProductOfPC;
import com.example.h_gear.home.pc_list.adapter.ContactProductOfPC;
import com.example.h_gear.home.pc_list.details.DetailsPCListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class PCListFragment extends Fragment implements IProductOfPCList {
    FragmentPcListBinding binding;
    ProductOfPCListPresenter presenter;
    AdapterContactProductOfPC adapterContactProductOfPC;
    public static PCListFragment newInstance() {

        Bundle args = new Bundle();

        PCListFragment fragment = new PCListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pc_list,container,false);
        presenter = new ProductOfPCListPresenter(this);
        presenter.onShowListPC();
        return binding.getRoot();
    }

    @Override
    public void onShowList(List<ContactProductOfPC> contactList) {
        adapterContactProductOfPC = new AdapterContactProductOfPC(contactList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.rcViewPCList.setAdapter(adapterContactProductOfPC);
        binding.rcViewPCList.setLayoutManager(layoutManager);
        adapterContactProductOfPC.setIonItemClickPC(new IonItemClickPC() {
            @Override
            public void onImageListPC(ContactProductOfPC contactProductOfPC) {
                EventBus.getDefault().postSticky(contactProductOfPC);
                getFragment(DetailsPCListFragment.newInstance());
            }
        });
    }
    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}
