package com.setoalan.chinachef;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static Menu sMenu;

    private List<Appetizer> mAppetizers;

    public static Menu get(Context context) {
        if (sMenu == null) {
            sMenu = new Menu(context);
        }
        return sMenu;
    }

    private Menu(Context context) {
        mAppetizers = new ArrayList<>();
    }

    public List<Appetizer> getAppetizers() {
        return mAppetizers;
    }

    public void setAppetizers(JSONArray appetizers) {
        try {
            JSONObject jsonObject;
            int size = appetizers.length();
            for (int i = 0; i < size; i++) {
                jsonObject = appetizers.getJSONObject(i);
                mAppetizers.add(new Appetizer(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description"),
                        jsonObject.getDouble("price"),
                        jsonObject.getBoolean("spicy")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Appetizer getAppetizer(int appetizerId) {
        for (Appetizer appetizer : mAppetizers) {
            if (appetizer.getId() == appetizerId)
                return appetizer;
        }
        return null;
    }

    public void destroyMenu() {
        sMenu = null;
    }

}
