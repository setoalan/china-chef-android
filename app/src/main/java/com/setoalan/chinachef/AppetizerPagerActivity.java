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

public class AppetizerPagerActivity extends FragmentActivity {

    private static final String EXTRA_APPETIZER_ID =
            "com.setoalan.chinachef.appetizer_id";

    private List<Appetizer> mAppetizers;

    private ViewPager mViewPager;

    public static Intent newIntent(Context packageContext, int appetizerId) {
        Intent intent = new Intent(packageContext, AppetizerPagerActivity.class);
        intent.putExtra(EXTRA_APPETIZER_ID, appetizerId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer_pager);

        int appetizerId = getIntent().getIntExtra(EXTRA_APPETIZER_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_appetizer_pager_view_pager);

        mAppetizers = Menu.get(this).getAppetizers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Appetizer appetizer = mAppetizers.get(position);
                return ChinaChefFragment.newInstance(appetizer.getId());
            }

            @Override
            public int getCount() {
                return mAppetizers.size();
            }
        });

        for (int i = 0; i < mAppetizers.size(); i++) {
            if (mAppetizers.get(i).getId() == appetizerId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
