package com.example.h_gear.discount.adapter;

public class ContactProductOfDiscount {
    private String name;
    private String oldPrice ;
    private String discount;
    private String intelCore;
    private String nvidiaGeforce;
    private String monitor;
    private String ssd;
    private String ram;
    private String image;

    public ContactProductOfDiscount() {
    }

    public ContactProductOfDiscount(String name, String oldPrice, String discount, String intelCore, String nvidiaGeforce, String monitor, String ssd, String ram, String image) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.discount = discount;
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
        return discount;
    }

    public void setDisCount(String disCount) {
        this.discount = discount;
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
