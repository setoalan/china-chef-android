package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class EntreeDetailFragment extends Fragment {

    public static final String ARG_ENTREE_ID = "entree_id";

    private Entree mEntree;
    private int quantity = 0;

    private TextView mNameTextView;
    private TextView mPriceTextView;
    private Button mDecreaseButton;
    private TextView mQuantityTextView;
    private Button mIncreaseTextView;
    private TextView mDescriptionTextView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entree_detail, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.entree_name_text_view);
        mNameTextView.setText(mEntree.getName());

        mPriceTextView = (TextView) view.findViewById(R.id.entree_price_text_view);
        mPriceTextView.setText(NumberFormat.getCurrencyInstance().format(mEntree.getPrice()));

        mDecreaseButton = (Button) view.findViewById(R.id.decrease_button);
        mDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0) quantity--;
                mQuantityTextView.setText(String.valueOf(quantity));
            }
        });
        mQuantityTextView = (TextView) view.findViewById(R.id.quantity_text_view);
        mQuantityTextView.setText(String.valueOf(quantity));

        mIncreaseTextView = (Button) view.findViewById(R.id.increase_button);
        mIncreaseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantityTextView.setText(String.valueOf(++quantity));
            }
        });

        mDescriptionTextView = (TextView) view.findViewById(R.id.entree_description_text_view);
        mDescriptionTextView.setText(mEntree.getDescription());

        return view;
    }

}
