package com.example.h_gear.home.pc_list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactPcListBinding;
import com.example.h_gear.home.pc_list.IonItemClickPC;


import java.text.DecimalFormat;
import java.util.List;

public class AdapterContactProductOfPC extends RecyclerView.Adapter<AdapterContactProductOfPC.ViewHoder>{
    List<ContactProductOfPC> contactProductOfPCList;
    IonItemClickPC ionItemClickPC;
    Context context;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    public AdapterContactProductOfPC() {
    }


    public void setIonItemClickPC(IonItemClickPC ionItemClickPC) {
        this.ionItemClickPC = ionItemClickPC;
    }

    public AdapterContactProductOfPC(List<ContactProductOfPC> contactProductOfPCList, Context context) {
        this.contactProductOfPCList = contactProductOfPCList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterContactProductOfPC.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactPcListBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_contact_pc_list,parent,false);
        ViewHoder viewHoder =new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactProductOfPC.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ContactProductOfPC contactProductOfPC = contactProductOfPCList.get(position);
        double price = Double.parseDouble(contactProductOfPC.getOldPrice());
        holder.binding.tvName.setText(contactProductOfPC.getName());
        holder.binding.tvOldPrice.setText(decimalFormat.format(price));
        holder.binding.tvIntelCore.setText(contactProductOfPC.getIntelCore());
        holder.binding.tvNvidiaGeforce.setText(contactProductOfPC.getNvidiaGeforce());
        holder.binding.tvMonitor.setText(contactProductOfPC.getMonitor());
        holder.binding.tvSSD.setText(contactProductOfPC.getSsd());
        holder.binding.tvRam.setText(contactProductOfPC.getRam());

        holder.binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(contactProductOfPC.getImage()).into(holder.binding.imProduct);
        holder.binding.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionItemClickPC.onImageListPC(contactProductOfPC);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contactProductOfPCList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactPcListBinding binding;
        public ViewHoder(@NonNull ItemContactPcListBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }
}
