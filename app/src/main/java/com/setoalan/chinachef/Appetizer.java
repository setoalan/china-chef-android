package com.setoalan.chinachef;

public class Appetizer {

    private int mId;
    private String mName;
    private String mDescription;
    private double mPrice;
    private boolean mSpicy;

    public Appetizer(int id, String name, String description, double price, boolean spicy) {
        mId = id;
        mName = name;
        mDescription = description;
        mPrice = price;
        mSpicy = spicy;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public boolean isSpicy() {
        return mSpicy;
    }

    public void setSpicy(boolean spicy) {
        mSpicy = spicy;
    }

}
