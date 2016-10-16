package com.spardha.ritesh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.spardha.ritesh.fragment.FragmentEventGrid;
import com.spardha.ritesh.fragment.FragmentEventInformals;
import com.spardha.ritesh.fragment.FragmentTestimonials;
import com.spardha.ritesh.fragment.FragmentVideoWall;
import com.spardha.ritesh.utils.Constants;

/**
 * Created by ritesh on 6/29/16.
 */
public class AdapterViewPagerHome extends FragmentStatePagerAdapter {


    public AdapterViewPagerHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentEventGrid();
            case 2:
                Log.d("Viepager home :: ", "launching informals frgmnet");
                return new FragmentEventInformals();
            case 3:
                return new FragmentVideoWall();
            case 4:
                return new FragmentTestimonials();
            default:
                return new FragmentTestimonials();
        }

    }

    @Override
    public int getCount() {
        return Constants.TAB_HOME_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.TAB_HOME_TITLES[position];
    }


}
