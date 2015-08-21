package com.setoalan.chinachef;

public class Appetizer {

    private int mId;
    private String mName;
    private int mQuantity;
    private double mPrice;
    private boolean mSpicy;

    public void Appetizer(int id, String name, double price, boolean spicy) {
        mId = id;
        mName = name;
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

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
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
