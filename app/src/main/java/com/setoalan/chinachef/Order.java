package com.setoalan.chinachef;

import android.content.Context;

public class Order {

    private static Order sOrder;

    private Customer mCustomer;
    private boolean mPickUp;

    public static Order newOrder(Context context, String customerName, String customerPhoneNumber,
                                 boolean pickUp, String customerAddress, String customerCardNumber,
                                 String customerCardExpDate) {
        if (sOrder == null) {
            sOrder = new Order(context, customerName, customerPhoneNumber, pickUp,
                    customerAddress, customerCardNumber, customerCardExpDate);
        }
        return sOrder;
    }

    private Order(Context context, String customerName, String customerPhoneNumber, boolean pickUp,
                  String customerAddress, String customerCardNumber,
                  String customerCardExpDate) {
        mCustomer = new Customer(customerName, customerPhoneNumber, customerAddress,
                customerCardNumber, customerCardExpDate);
        mPickUp = pickUp;
    }

}
