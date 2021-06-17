package com.example.h_gear.home.laptop_list.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactDiscountBinding;
import com.example.h_gear.databinding.ItemContactLaptopListBinding;
import com.example.h_gear.discount.adapter.ContactProductOfDiscount;
import com.example.h_gear.home.IonItemClickContact;
import com.example.h_gear.home.laptop_list.IonItemClickLaptop;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterContactProductOfLaptop extends RecyclerView.Adapter<AdapterContactProductOfLaptop.ViewHoder>{
    List<ContactProductOfLaptop> contactProductOfLaptopList;
    IonItemClickLaptop ionItemClickContact;
    Context context;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    public AdapterContactProductOfLaptop() {
    }

    public void setIonItemClickContact(IonItemClickLaptop ionItemClickContact) {
        this.ionItemClickContact = ionItemClickContact;
    }

    public AdapterContactProductOfLaptop(List<ContactProductOfLaptop> contactProductOfLaptopList, Context context) {
        this.contactProductOfLaptopList = contactProductOfLaptopList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterContactProductOfLaptop.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactLaptopListBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_contact_laptop_list,parent,false);
        ViewHoder viewHoder =new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactProductOfLaptop.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ContactProductOfLaptop contactProductOfLaptop = contactProductOfLaptopList.get(position);
        double price = Double.parseDouble(contactProductOfLaptop.getOldPrice());
        holder.binding.tvName.setText(contactProductOfLaptop.getName());
        holder.binding.tvOldPrice.setText(decimalFormat.format(price));
        holder.binding.tvIntelCore.setText(contactProductOfLaptop.getIntelCore());
        holder.binding.tvNvidiaGeforce.setText(contactProductOfLaptop.getNvidiaGeforce());
        holder.binding.tvMonitor.setText(contactProductOfLaptop.getMonitor());
        holder.binding.tvSSD.setText(contactProductOfLaptop.getSsd());
        holder.binding.tvRam.setText(contactProductOfLaptop.getRam());

        holder.binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(contactProductOfLaptop.getImage()).into(holder.binding.imProduct);
        holder.binding.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionItemClickContact.onImageListLapTop(contactProductOfLaptop);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contactProductOfLaptopList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactLaptopListBinding binding;
        public ViewHoder(@NonNull ItemContactLaptopListBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }
}
