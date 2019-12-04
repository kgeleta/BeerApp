package com.vastmoths.beerapp.database.model;

public class Beer {

    private int id;
    private String name;
    private int rate;
    private String type;
    private String picturePath;

    public Beer(int id, String name, int rate, String type, String picturePath) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.type = type;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
