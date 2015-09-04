package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EntreePagerFragment extends Fragment implements Toolbar.OnClickListener {

    public static final String ARG_ENTREE_ID = "entree_id";

    private int entreeId;
    private List<Entree> mEntrees;

    @Bind(R.id.fragment_entree_pager_view_pager)  ViewPager mViewPager;

    public static EntreePagerFragment newInstance(int entreeId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ENTREE_ID, entreeId);

        EntreePagerFragment fragment = new EntreePagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sToolbar.setNavigationIcon(R.drawable.ic_action_back);
        //sToolbar.setNavigationOnClickListener(this);
        entreeId = getArguments().getInt(ARG_ENTREE_ID);
        mEntrees = Menu.get(getActivity()).getEntrees();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entree_pager, container, false);

        ButterKnife.bind(this, view);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Entree entree = mEntrees.get(position);
                return EntreeDetailFragment.newInstance(entree.getId());
            }

            @Override
            public int getCount() {
                return mEntrees.size();
            }
        });

        for (int i = 0; i < mEntrees.size(); i++) {
            if (mEntrees.get(i).getId() == entreeId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .remove(this)
                .commit();
        appCompatActivity.onBackPressed();
    }

}
