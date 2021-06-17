package com.example.h_gear.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactOfCustomerBinding;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterContactOfCustomer extends RecyclerView.Adapter<AdapterContactOfCustomer.ViewHoder> {
    List<ProductOfCustomer> productOfCustomerList;
    Context context;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;

    public AdapterContactOfCustomer() {
    }

    public AdapterContactOfCustomer(List<ProductOfCustomer> productOfCustomerList, Context context) {
        this.productOfCustomerList = productOfCustomerList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterContactOfCustomer.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactOfCustomerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_contact_of_customer,parent,false);
        ViewHoder viewHoder = new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactOfCustomer.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ProductOfCustomer product = productOfCustomerList.get(position);
        holder.binding.imAvatar.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(product.getImage()).into(holder.binding.imAvatar);
        holder.binding.tvAmount.setText(product.getAmount()+"");
        holder.binding.tvNameProduct.setText(product.getNameProduct());
        holder.binding.tvPrice.setText(decimalFormat.format(product.getPrice()));
        holder.binding.tvDate.setText(product.getDate());
        holder.binding.tvThanhTien.setText(decimalFormat.format(product.getPrice()*product.getAmount()));
    }

    @Override
    public int getItemCount() {
        return productOfCustomerList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactOfCustomerBinding binding;
        public ViewHoder(@NonNull ItemContactOfCustomerBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
