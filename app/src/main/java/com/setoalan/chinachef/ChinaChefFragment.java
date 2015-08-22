package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ChinaChefFragment extends Fragment {

    private int quantity = 0;

    private TextView mNameTextView;
    private TextView mPriceTextView;
    private Button mDecreaseButton;
    private TextView mQuantityTextView;
    private Button mIncreaseTextView;
    private TextView mDescriptionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importJSONAppetizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_china_chef, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.appetizer_name_text_view);
        mNameTextView.setText("Appetizer");

        mPriceTextView = (TextView) view.findViewById(R.id.appetizer_price_text_view);
        mPriceTextView.setText("$9.99");

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
        mDescriptionTextView.setText("Description of the appetizer");

        return view;
    }

    private void importJSONAppetizer() {
        try {
            InputStream inputStream = getActivity().getAssets().open("appetizers.json");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String jsonMenu = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONObject(jsonMenu).getJSONArray("appetizers");
            Menu.get(getActivity()).setAppetizers(jsonArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
