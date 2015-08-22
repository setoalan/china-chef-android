package com.setoalan.chinachef;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

public class EntreePagerActivity extends FragmentActivity {

    private static final String EXTRA_ENTREE_ID = "com.setoalan.chinachef.appetizer_id";

    private List<Entree> mEntrees;

    private ViewPager mViewPager;

    public static Intent newIntent(Context packageContext, int entreeId) {
        Intent intent = new Intent(packageContext, EntreePagerActivity.class);
        intent.putExtra(EXTRA_ENTREE_ID, entreeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entree_pager);

        int appetizerId = getIntent().getIntExtra(EXTRA_ENTREE_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_entree_pager_view_pager);

        mEntrees = Menu.get(this).getEntrees();
        FragmentManager fragmentManager = getSupportFragmentManager();
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
            if (mEntrees.get(i).getId() == appetizerId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
