package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EntreeDetailFragment extends Fragment {

    public static final String ARG_ENTREE_ID = "entree_id";

    private Entree mEntree;
    private int quantity = 0;

    @Bind(R.id.entree_name_text_view) private TextView mNameTextView;
    @Bind(R.id.entree_price_text_view) private TextView mPriceTextView;
    @Bind(R.id.decrease_button ) private Button mDecreaseButton;
    @Bind(R.id.quantity_text_view) private TextView mQuantityTextView;
    @Bind(R.id.increase_button) private Button mIncreaseTextView;
    @Bind(R.id.entree_description_text_view) private TextView mDescriptionTextView;

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

        ButterKnife.bind(this, view);

        mNameTextView.setText(mEntree.getName());

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

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
