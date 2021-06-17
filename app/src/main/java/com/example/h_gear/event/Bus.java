package com.example.h_gear.event;

import org.greenrobot.eventbus.EventBus;

public class Bus {
    static Bus instance;

    public static Bus getInstance() {
        if(instance == null) instance = new Bus();
        return instance;
    }
    public void post(Object o){
        EventBus.getDefault().post(o);
    }
    public void checkLogin(EtradeLogin k){
        EventBus.getDefault().post(k);
    }
    public void register(Object o){
        if(!EventBus.getDefault().isRegistered(o)) EventBus.getDefault().register(o);
    }
    public void unRegister(Object o){
        EventBus.getDefault().unregister(o);
    }
}
