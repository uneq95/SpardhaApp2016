package com.spardha.ritesh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterViewPagerHome;

/**
 * Created by ritesh on 6/29/16.
 */
public class ActivityHome extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spardha'16");
        ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterViewPagerHome adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(5);
        //TODO remove this limit and handle memory issues
        viewPager.setAdapter(adapterViewPagerHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuContacts:
                intent = new Intent(ActivityHome.this, ActivityContacts.class);
                startActivity(intent);
                return true;
            case R.id.menuNavigate:
                intent = new Intent(ActivityHome.this, ActivityMaps.class);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);

    }
}
