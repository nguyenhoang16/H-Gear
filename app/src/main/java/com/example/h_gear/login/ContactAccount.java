package com.example.h_gear.login;

public class ContactAccount {
    String name,username,password,sdt;

    public ContactAccount() {
    }

    public ContactAccount(String name, String username, String password, String sdt) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
