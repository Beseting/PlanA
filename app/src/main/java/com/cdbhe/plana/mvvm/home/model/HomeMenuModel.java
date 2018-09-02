package com.cdbhe.plana.mvvm.home.model;

public class HomeMenuModel {
    private int icon;
    private String name;

    public HomeMenuModel() {
    }

    public HomeMenuModel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
