package com.java.pojo;

import java.util.List;

public class Series {
    private List<Float> value;
    private String name;

    public Series(List<Float> value, String name) {
        this.value = value;
        this.name = name;
    }

    public List<Float> getValue() {
        return value;
    }

    public void setValue(List<Float> value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
