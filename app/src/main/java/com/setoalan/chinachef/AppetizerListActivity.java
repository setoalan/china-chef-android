package com.setoalan.chinachef;

import android.support.v4.app.Fragment;

public class AppetizerListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AppetizerListFragment();
    }

}
