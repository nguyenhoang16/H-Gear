package com.example.h_gear.event;

import com.example.h_gear.login.ContactAccount;

public class EtradeAccount {
    ContactAccount contactAccount;

    public EtradeAccount(ContactAccount contactAccount) {
        this.contactAccount = contactAccount;
    }

    public EtradeAccount() {
    }

    public ContactAccount getContactAccount() {
        return contactAccount;
    }

    public void setContactAccount(ContactAccount contactAccount) {
        this.contactAccount = contactAccount;
    }
}
