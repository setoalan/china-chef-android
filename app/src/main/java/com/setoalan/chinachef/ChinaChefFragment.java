package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ChinaChefFragment extends Fragment {

    private TextView mNameTextView;
    private TextView mPriceTextView;
    private TextView mDescriptionTextView;
    private Button mDecreaseQuantityButton;
    private TextView mQuantityTextView;
    private Button mIncreaseQuantityTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_china_chef, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.appetizer_name_text_view);
        mPriceTextView = (TextView) view.findViewById(R.id.appetizer_price_text_view);
        mDescriptionTextView = (TextView) view.findViewById(R.id.appetizer_description_text_view);
        mDecreaseQuantityButton = (Button) view.findViewById(R.id.decrease_quantity_button);
        mQuantityTextView = (TextView) view.findViewById(R.id.quantity_text_view);
        mIncreaseQuantityTextView = (Button) view.findViewById(R.id.increase_quantity_button);

        return view;
    }

}
