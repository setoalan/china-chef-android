package com.setoalan.chinachef;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class ChinaChefActivity extends SingleFragmentActivity {

    private static final String EXTRA_APPETIZER_ID =
            "com.setoalan.chinachef.appetizer_id";

    public static Intent newIntent(Context packageContext, int appetizerId) {
        Intent intent = new Intent(packageContext, ChinaChefActivity.class);
        intent.putExtra(EXTRA_APPETIZER_ID, appetizerId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        int appetizerId = getIntent().getIntExtra(EXTRA_APPETIZER_ID, 0);
        return ChinaChefFragment.newInstance(appetizerId);
    }

}
