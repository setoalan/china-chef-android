package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class ChinaChefFragment extends Fragment {

    public static final String ARG_APPETIZER_ID = "appetizer_id";

    private Appetizer mAppetizer;
    private int quantity = 0;

    private TextView mNameTextView;
    private TextView mPriceTextView;
    private Button mDecreaseButton;
    private TextView mQuantityTextView;
    private Button mIncreaseTextView;
    private TextView mDescriptionTextView;

    public static ChinaChefFragment newInstance(int appetizerId) {
        Bundle args = new Bundle();
        args.putInt(ARG_APPETIZER_ID, appetizerId);

        ChinaChefFragment fragment = new ChinaChefFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int appetizerId = getArguments().getInt(ARG_APPETIZER_ID);

        mAppetizer = Menu.get(getActivity()).getAppetizer(appetizerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_china_chef, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.appetizer_name_text_view);
        mNameTextView.setText(mAppetizer.getName());

        mPriceTextView = (TextView) view.findViewById(R.id.appetizer_price_text_view);
        mPriceTextView.setText(NumberFormat.getCurrencyInstance().format(mAppetizer.getPrice()));

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

        mDescriptionTextView = (TextView) view.findViewById(R.id.appetizer_description_text_view);
        mDescriptionTextView.setText(mAppetizer.getDescription());

        return view;
    }

}
