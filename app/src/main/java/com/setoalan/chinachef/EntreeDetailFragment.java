package com.setoalan.chinachef;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EntreeDetailFragment extends DialogFragment {

    public static final String ARG_ENTREE_ID = "entree_id";

    private Entree mEntree;
    private int quantity = 0;

    @Bind(R.id.entree_price_text_view) TextView mPriceTextView;
    @Bind(R.id.decrease_button) Button mDecreaseButton;
    @Bind(R.id.quantity_text_view) TextView mQuantityTextView;
    @Bind(R.id.increase_button) Button mIncreaseTextView;
    @Bind(R.id.entree_description_text_view) TextView mDescriptionTextView;

    public static EntreeDetailFragment newInstance(int entreeId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ENTREE_ID, entreeId);

        EntreeDetailFragment fragment = new EntreeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int entreeId = getArguments().getInt(ARG_ENTREE_ID);
        mEntree = Menu.get(getActivity()).getEntree(entreeId);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_entree_detail, null);

        ButterKnife.bind(this, view);

        mPriceTextView.setText(NumberFormat.getCurrencyInstance().format(mEntree.getPrice()));

        mDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0) quantity--;
                mQuantityTextView.setText(String.valueOf(quantity));
            }
        });

        mQuantityTextView.setText(String.valueOf(quantity));

        mIncreaseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantityTextView.setText(String.valueOf(++quantity));
            }
        });

        mDescriptionTextView.setText(mEntree.getDescription());

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(mEntree.getName())
                .setPositiveButton(R.string.add_to_order, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("TAG", "TAG");
                    }
                })
                .create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
