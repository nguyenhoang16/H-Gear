package com.example.h_gear.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.h_gear.R;
import com.example.h_gear.databinding.FragmentLocationBinding;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapScheme;

public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;

    public static LocationFragment newInstance() {

        Bundle args = new Bundle();

        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_location,container,false);
        binding.mvMap.onCreate(savedInstanceState);
        loadMap();
        return binding.getRoot();
    }
    public void loadMap(){
        binding.mvMap.getMapScene().loadScene(MapScheme.NORMAL_DAY, mapError -> {
            if(mapError == null){
                binding.mvMap.getCamera().lookAt(new GeoCoordinates(21.02889,105.85464),1000);
            } else{

            }
        });
    }
}
