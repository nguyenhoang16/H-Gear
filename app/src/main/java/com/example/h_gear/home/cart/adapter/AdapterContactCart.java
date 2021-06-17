package com.example.h_gear.home.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactOfCartBinding;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterContactCart extends RecyclerView.Adapter<AdapterContactCart.ViewHoder> {
    List<ContactCart> contactCartList;
    Context context;
    IonClickContact ionClickContact;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    public void setIsqlHelper(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    public AdapterContactCart() {
    }

    public AdapterContactCart(List<ContactCart> contactCartList, Context context) {
        this.contactCartList = contactCartList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterContactCart.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactOfCartBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_contact_of_cart,parent,false);
        ViewHoder viewHoder = new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactCart.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ContactCart contactCart = contactCartList.get(position);
        holder.binding.tvNameProduct.setText(contactCart.getName());
        holder.binding.tvPrice.setText(decimalFormat.format(contactCart.getPrice()));
        holder.binding.tvAmount.setText(contactCart.getCount()+"");
        Double thanhTien = contactCart.getCount()*contactCart.getPrice();
        holder.binding.tvThanhTien.setText(decimalFormat.format(thanhTien));
        holder.binding.imAvatar.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(contactCart.getImage()).into(holder.binding.imAvatar);
        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickContact.onBtnDelete(contactCart);
            }
        });
        holder.binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickContact.onBtnDown(contactCart);
            }
        });
        holder.binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickContact.onBtnUp(contactCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactCartList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactOfCartBinding binding;
        public ViewHoder(@NonNull ItemContactOfCartBinding itemView) {
            super(itemView.getRoot());
            this.binding= itemView;
        }
    }
}
