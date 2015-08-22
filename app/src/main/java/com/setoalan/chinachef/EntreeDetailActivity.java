package com.setoalan.chinachef;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class EntreeDetailActivity extends SingleFragmentActivity {

    private static final String EXTRA_ENTREE_ID =
            "com.setoalan.chinachef.appetizer_id";

    public static Intent newIntent(Context packageContext, int entreeId) {
        Intent intent = new Intent(packageContext, EntreeDetailActivity.class);
        intent.putExtra(EXTRA_ENTREE_ID, entreeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        int entreeId = getIntent().getIntExtra(EXTRA_ENTREE_ID, 0);
        return EntreeDetailFragment.newInstance(entreeId);
    }

}
