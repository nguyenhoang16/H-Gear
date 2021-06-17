package com.example.h_gear.discount.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h_gear.R;
import com.example.h_gear.databinding.ItemContactDiscountBinding;
import com.example.h_gear.discount.IonItemClickDiscount;
import com.example.h_gear.home.adapter.AdapterContactProduct;
import com.example.h_gear.home.adapter.ContactProduct;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterContactProductOfDiscount extends RecyclerView.Adapter<AdapterContactProductOfDiscount.ViewHoder> implements Filterable {
    List<ContactProductOfDiscount> contactProductOfDiscountList;
    List<ContactProductOfDiscount> listSearch;
    IonItemClickDiscount ionItemClickDiscount;
    Context context;
    String pattern = "#,##0 VNĐ";
    DecimalFormat decimalFormat;
    public AdapterContactProductOfDiscount() {
    }
    public void setIonItemClickDiscount(IonItemClickDiscount ionItemClickDiscount){
        this.ionItemClickDiscount = ionItemClickDiscount;
    }
    public AdapterContactProductOfDiscount(List<ContactProductOfDiscount> contactProductOfDiscountList, Context context) {
        this.contactProductOfDiscountList = contactProductOfDiscountList;
        this.context = context;
        this.listSearch = contactProductOfDiscountList;
    }

    @NonNull
    @Override
    public AdapterContactProductOfDiscount.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContactDiscountBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_contact_discount,parent,false);
        ViewHoder viewHoder = new ViewHoder(binding);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContactProductOfDiscount.ViewHoder holder, int position) {
        decimalFormat = new DecimalFormat(pattern);
        ContactProductOfDiscount contactProductOfDiscount = contactProductOfDiscountList.get(position);
        Double price = Double.parseDouble(contactProductOfDiscount.getDisCount());
        holder.binding.tvName.setText(contactProductOfDiscount.getName());
        holder.binding.tvOldPrice.setText(contactProductOfDiscount.getOldPrice());
        holder.binding.tvOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.binding.tvDiscount.setText(decimalFormat.format(price));
        holder.binding.tvIntelCore.setText(contactProductOfDiscount.getIntelCore());
        holder.binding.tvNvidiaGeforce.setText(contactProductOfDiscount.getNvidiaGeforce());
        holder.binding.tvMonitor.setText(contactProductOfDiscount.getMonitor());
        holder.binding.tvSSD.setText(contactProductOfDiscount.getSsd());
        holder.binding.tvRam.setText(contactProductOfDiscount.getRam());

        holder.binding.imProduct.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(contactProductOfDiscount.getImage()).into(holder.binding.imProduct);
        holder.binding.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionItemClickDiscount.onImageListDiscount(contactProductOfDiscount);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contactProductOfDiscountList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    contactProductOfDiscountList = listSearch;
                }
                else{
                    List<ContactProductOfDiscount> list = new ArrayList<>();
                    for(ContactProductOfDiscount contact : list){
                        if(contact.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(contact);
                        }
                    }
                    contactProductOfDiscountList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contactProductOfDiscountList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactProductOfDiscountList = (List<ContactProductOfDiscount>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemContactDiscountBinding binding;
        public ViewHoder(@NonNull ItemContactDiscountBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }
}
