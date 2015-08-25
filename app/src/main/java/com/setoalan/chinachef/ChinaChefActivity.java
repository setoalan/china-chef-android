package com.setoalan.chinachef;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public abstract class ChinaChefActivity extends AppCompatActivity {

    public static Toolbar sToolbar;
    public static DrawerLayout sDrawerLayout;
    public static ActionBarDrawerToggle sDrawerToggle;

    private NavigationView mNavigationView;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        sToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);

        sDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        sDrawerToggle = new ActionBarDrawerToggle(this, sDrawerLayout, sToolbar, R.string.plus, R.string.minus) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        return true;
                    case R.id.navigation_item_2:
                        return true;
                    default:
                        return true;
                }
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
