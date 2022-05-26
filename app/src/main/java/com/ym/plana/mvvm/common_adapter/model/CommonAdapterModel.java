package com.ym.plana.mvvm.common_adapter.model;

public class CommonAdapterModel {
    private String img;
    private String name;

    public CommonAdapterModel() {
    }

    public CommonAdapterModel(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
