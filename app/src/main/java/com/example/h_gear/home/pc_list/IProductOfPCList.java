package com.example.h_gear.home.pc_list;

import com.example.h_gear.home.laptop_list.adapter.ContactProductOfLaptop;
import com.example.h_gear.home.pc_list.adapter.ContactProductOfPC;

import java.util.List;

public interface IProductOfPCList {
    void onShowList(List<ContactProductOfPC> contactList);
}
