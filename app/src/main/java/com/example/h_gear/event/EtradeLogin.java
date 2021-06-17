package com.example.h_gear.event;

import com.example.h_gear.login.ContactAccount;

public class EtradeLogin {
    boolean check;
    public EtradeLogin() {
    }

    public EtradeLogin(boolean check) {
        this.check = check;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


}
