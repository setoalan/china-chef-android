package com.setoalan.chinachef;

import android.support.v4.app.Fragment;

public class EntreeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EntreeListFragment();
    }

}
