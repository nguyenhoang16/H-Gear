package com.example.h_gear.discount;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_gear.MainActivity;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ActivityMainBinding;
import com.example.h_gear.databinding.FragmentDiscountBinding;
import com.example.h_gear.discount.adapter.AdapterContactProductOfDiscount;
import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
import com.example.h_gear.discount.details.DetailsDiscountListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class DisCountFragment extends Fragment implements IProductOfDiscount {
    FragmentDiscountBinding binding;
    ProductOfDiscountPresenter presenter;
    AdapterContactProductOfDiscount adapterContactProductOfDiscount;
    SearchView searchView;
    public static DisCountFragment newInstance() {

        Bundle args = new Bundle();

        DisCountFragment fragment = new DisCountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discount,container,false);
        presenter = new ProductOfDiscountPresenter(this);
        presenter.onShowListDiscount();
        return binding.getRoot();
    }

    @Override
    public void onShowList(List<ContactProductOfDiscount> contactList) {
        adapterContactProductOfDiscount = new AdapterContactProductOfDiscount(contactList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.rcViewDiscount.setAdapter(adapterContactProductOfDiscount);
        binding.rcViewDiscount.setLayoutManager(layoutManager);
        adapterContactProductOfDiscount.setIonItemClickDiscount(new IonItemClickDiscount() {
            @Override
            public void onImageListDiscount(ContactProductOfDiscount contactProductOfDiscount) {
                EventBus.getDefault().postSticky(contactProductOfDiscount);
                getFragment(DetailsDiscountListFragment.newInstance());
            }
        });

    }
    private void getFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }




//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_main,menu);
//        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapterContactProductOfDiscount.getFilter().filter(query);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapterContactProductOfDiscount.getFilter().filter(newText);
//                return false;
//            }
//        });
//    }
}
