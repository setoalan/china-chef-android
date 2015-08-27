package com.setoalan.chinachef;

public class Customer {

    private String mName;
    private String mPhoneNumber;
    private String mAddress;
    private String mCardNumber;
    private String mCardExpDate;

    public Customer(String name, String phoneNumber, String address, String cardNumber,
                    String cardExpDate) {
        mName = name;
        mPhoneNumber = phoneNumber;
        mAddress = address;
        mCardNumber = cardNumber;
        mCardExpDate = cardExpDate;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCardNumber() {
        return mCardNumber;
    }

    public void setCardNumber(String cardNumber) {
        mCardNumber = cardNumber;
    }

    public String getCardExpDate() {
        return mCardExpDate;
    }

    public void setCardExpDate(String cardExpDate) {
        mCardExpDate = cardExpDate;
    }

}
