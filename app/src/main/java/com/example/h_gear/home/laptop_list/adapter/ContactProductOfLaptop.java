package com.example.h_gear.home.laptop_list.adapter;

public class ContactProductOfLaptop {
    private String name;
    private String oldPrice ;
    private String disCount;
    private String intelCore;
    private String nvidiaGeforce;
    private String monitor;
    private String ssd;
    private String ram;
    private String image;

    public ContactProductOfLaptop() {
    }

    public ContactProductOfLaptop(String name, String oldPrice, String disCount, String intelCore, String nvidiaGeforce, String monitor, String ssd, String ram, String image) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.disCount = disCount;
        this.intelCore = intelCore;
        this.nvidiaGeforce = nvidiaGeforce;
        this.monitor = monitor;
        this.ssd = ssd;
        this.ram = ram;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDisCount() {
        return disCount;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public String getIntelCore() {
        return intelCore;
    }

    public void setIntelCore(String intelCore) {
        this.intelCore = intelCore;
    }

    public String getNvidiaGeforce() {
        return nvidiaGeforce;
    }

    public void setNvidiaGeforce(String nvidiaGeforce) {
        this.nvidiaGeforce = nvidiaGeforce;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
