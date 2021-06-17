package com.example.h_gear.sql;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class AppRegister extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
