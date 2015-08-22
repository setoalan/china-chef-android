package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppetizerListFragment extends Fragment {

    private AppetizerAdapter mAdapter;

    private RecyclerView mAppetizerRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importJSONAppetizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appetizer_list, container, false);

        mAppetizerRecyclerView = (RecyclerView) view.findViewById(R.id.appetizer_recycler_view);
        mAppetizerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Menu menu = Menu.get(getActivity());
        List<Appetizer> appetizers = menu.getAppetizers();

        mAdapter = new AppetizerAdapter(appetizers);
        mAppetizerRecyclerView.setAdapter(mAdapter);
    }

    private class AppetizerHolder extends RecyclerView.ViewHolder {

        public TextView mNameTextView;

        public AppetizerHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView;
        }

    }

    private class AppetizerAdapter extends RecyclerView.Adapter<AppetizerHolder> {

        private List<Appetizer> mAppetizers;

        public AppetizerAdapter(List<Appetizer> appetizers) {
            mAppetizers = appetizers;
        }

        @Override
        public AppetizerHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new AppetizerHolder(view);
        }

        @Override
        public void onBindViewHolder(AppetizerHolder appetizerHolder, int position) {
            Appetizer appetizer = mAppetizers.get(position);
            appetizerHolder.mNameTextView.setText(appetizer.getName());
        }

        @Override
        public int getItemCount() {
            return mAppetizers.size();
        }

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
