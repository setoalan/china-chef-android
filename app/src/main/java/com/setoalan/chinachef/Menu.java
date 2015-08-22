package com.setoalan.chinachef;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static Menu sMenu;

    private List<Entree> mEntrees;

    public static Menu get(Context context) {
        if (sMenu == null) {
            sMenu = new Menu(context);
        }
        return sMenu;
    }

    private Menu(Context context) {
        mEntrees = new ArrayList<>();
    }

    public List<Entree> getEntrees() {
        return mEntrees;
    }

    public void setEntrees(JSONArray entrees) {
        try {
            JSONObject jsonObject;
            int size = entrees.length();
            for (int i = 0; i < size; i++) {
                jsonObject = entrees.getJSONObject(i);
                mEntrees.add(new Entree(
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

    public Entree getEntree(int entreeId) {
        for (Entree entree : mEntrees) {
            if (entree.getId() == entreeId)
                return entree;
        }
        return null;
    }

    public void destroyMenu() {
        sMenu = null;
    }

}
