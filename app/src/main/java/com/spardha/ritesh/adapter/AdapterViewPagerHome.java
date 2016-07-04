package com.spardha.ritesh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spardha.ritesh.fragment.FragmentEventGrid;
import com.spardha.ritesh.fragment.FragmentSportContacts;

/**
 * Created by ritesh on 6/29/16.
 */
public class AdapterViewPagerHome extends FragmentStatePagerAdapter {

    String[] TAB_TITLES = {"UPDATES", "EVENTS", "INFORMALS", "PHOTO WALL", "VIDEO WALL", "TESTIMONIALS"};


    public AdapterViewPagerHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentEventGrid();
            default:
                return new FragmentSportContacts();
        }

    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

}
