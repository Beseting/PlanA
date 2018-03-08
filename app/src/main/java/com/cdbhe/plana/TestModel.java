package com.cdbhe.plana;

import java.io.Serializable;

/**
 * Created by Kevin on 2018/1/30.
 */

public class TestModel implements Serializable {
    private String name;

    public TestModel() {
    }

    public TestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
