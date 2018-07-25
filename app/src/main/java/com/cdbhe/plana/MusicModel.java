package com.cdbhe.plana;

import java.io.Serializable;

/**
 * Created by Kevin on 2018/1/30.
 */

public class MusicModel implements Serializable {
    private String image;
    private String title;
    private String time;

    public MusicModel() {
    }

    public MusicModel(String image, String title, String time) {
        this.image = image;
        this.title = title;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
