package com.spardha.ritesh.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterViewPagerHome;

/**
 * Created by ritesh on 6/29/16.
 */
public class ActivityHome extends AppCompatActivity {

    AdapterViewPagerHome adapterViewPagerHome;
    ViewPager viewPager;
    TabLayout tabLayout;
    static boolean calledAlready = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spardha'16");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        ImageView header = (ImageView) findViewById(R.id.htab_header);
        //TODO change header with respect to tab selection

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header_cricket);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(6);
        viewPager.setAdapter(adapterViewPagerHome);
    }

}
