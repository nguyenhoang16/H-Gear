package com.example.h_gear.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactBinding;
import com.example.h_gear.home.IonItemClickContact;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterContactProduct extends RecyclerView.Adapter<AdapterContactProduct.ViewHoder> {

    List<ContactProduct> contactList;
    Context context;
    IonItemClickContact ionItemClickContact;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;

    public void setIonItemClickContact(IonItemClickContact ionItemClickContact) {
        this.ionItemClickContact = ionItemClickContact;
    }

    public AdapterContactProduct() {
    }

    public AdapterContactProduct(List<ContactProduct> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_contact,parent,false);

        ViewHoder viewHoder = new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactProduct.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ContactProduct contactProduct = contactList.get(position);
        double price = Double.parseDouble(contactProduct.getOldPrice());
        holder.binding.tvNameProduct.setText(contactProduct.getName());
        holder.binding.tvPrice.setText(decimalFormat.format(price));
        holder.binding.imAvatar.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(contactProduct.getImage()).into(holder.binding.imAvatar);
        holder.binding.imAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionItemClickContact.onImage(contactProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactBinding binding;

        public ViewHoder(@NonNull ItemContactBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
