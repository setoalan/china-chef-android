package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
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
import java.text.NumberFormat;
import java.util.List;

import static com.setoalan.chinachef.ChinaChefActivity.*;

public class EntreeListFragment extends Fragment {

    private EntreeAdapter mAdapter;

    private RecyclerView mEntreeRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importJSONAppetizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entree_list, container, false);

        mEntreeRecyclerView = (RecyclerView) view.findViewById(R.id.entree_recycler_view);
        mEntreeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sToolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        sToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        sDrawerLayout.setDrawerListener(sDrawerToggle);
        sDrawerToggle.syncState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Menu.get(getActivity()).destroyMenu();
    }

    private void updateUI() {
        Menu menu = Menu.get(getActivity());
        List<Entree> entrees = menu.getEntrees();

        if (mAdapter == null)
            mAdapter = new EntreeAdapter(entrees);
        mEntreeRecyclerView.setAdapter(mAdapter);
    }

    private class EntreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Entree mEntree;

        private TextView mNameTextView;
        private TextView mPriceTextView;

        public EntreeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_entree_name_text_view);
            mPriceTextView = (TextView) itemView.findViewById(R.id.list_item_entree_price_text_view);
        }

        public void bindCrime(Entree entree) {
            mEntree = entree;
            mNameTextView.setText(mEntree.getName());
            mPriceTextView.setText(NumberFormat.getCurrencyInstance().format(mEntree.getPrice()));
        }

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment fragment = EntreePagerFragment.newInstance(mEntree.getId());
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.abc_fade_out)
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    private class EntreeAdapter extends RecyclerView.Adapter<EntreeHolder> {

        private List<Entree> mEntrees;

        public EntreeAdapter(List<Entree> entrees) {
            mEntrees = entrees;
        }

        @Override
        public EntreeHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_entree, viewGroup, false);
            return new EntreeHolder(view);
        }

        @Override
        public void onBindViewHolder(EntreeHolder entreeHolder, int position) {
            Entree entree = mEntrees.get(position);
            entreeHolder.bindCrime(entree);
        }

        @Override
        public int getItemCount() {
            return mEntrees.size();
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
            Menu.get(getActivity()).setEntrees(jsonArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
