package com.setoalan.chinachef;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class OrderFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_order, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.new_order)
                .setPositiveButton(R.string.create_order, null)
                .create();
    }

}
