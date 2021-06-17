package com.example.h_gear.home.details.contact_details;

public class ContactDetailsPC {
    private String name,CPU,OS,RAM,GPU,Monitor,SSD,Lan,WirelessLan,Connectors,KeyBoard,Weight;

    public ContactDetailsPC() {
    }

    public ContactDetailsPC(String name, String CPU, String OS, String RAM, String GPU, String monitor, String SSD, String lan, String wirelessLan, String connectors, String keyBoard, String weight) {
        this.name = name;
        this.CPU = CPU;
        this.OS = OS;
        this.RAM = RAM;
        this.GPU = GPU;
        Monitor = monitor;
        this.SSD = SSD;
        Lan = lan;
        WirelessLan = wirelessLan;
        Connectors = connectors;
        KeyBoard = keyBoard;
        Weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getMonitor() {
        return Monitor;
    }

    public void setMonitor(String monitor) {
        Monitor = monitor;
    }

    public String getSSD() {
        return SSD;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }

    public String getLan() {
        return Lan;
    }

    public void setLan(String lan) {
        Lan = lan;
    }

    public String getWirelessLan() {
        return WirelessLan;
    }

    public void setWirelessLan(String wirelessLan) {
        WirelessLan = wirelessLan;
    }

    public String getConnectors() {
        return Connectors;
    }

    public void setConnectors(String connectors) {
        Connectors = connectors;
    }

    public String getKeyBoard() {
        return KeyBoard;
    }

    public void setKeyBoard(String keyBoard) {
        KeyBoard = keyBoard;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
