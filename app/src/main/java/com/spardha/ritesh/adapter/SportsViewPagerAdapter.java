package com.spardha.ritesh.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spardha.ritesh.fragment.FragmentSportContacts;
import com.spardha.ritesh.fragment.FragmentSportFixtures;
import com.spardha.ritesh.fragment.FragmentSportHallOfFame;
import com.spardha.ritesh.fragment.FragmentSportResults;
import com.spardha.ritesh.fragment.FragmentSportRules;
import com.spardha.ritesh.fragment.FragmentSportUpdates;
import com.spardha.ritesh.models.Contact;
import com.spardha.ritesh.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ritesh on 5/31/16.
 */
public class SportsViewPagerAdapter extends FragmentStatePagerAdapter {

    FragmentSportHallOfFame fragmentSportHallOfFame;
    FragmentSportContacts fragmentSportContacts;
    FragmentSportFixtures fragmentSportFixtures;
    FragmentSportUpdates fragmentSportUpdates;
    FragmentSportRules fragmentSportRules;
    FragmentSportResults fragmentSportResults;

    public SportsViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentSportHallOfFame = new FragmentSportHallOfFame();
        fragmentSportContacts = new FragmentSportContacts();
        fragmentSportFixtures = new FragmentSportFixtures();
        fragmentSportUpdates = new FragmentSportUpdates();
        fragmentSportResults = new FragmentSportResults();
        fragmentSportRules = new FragmentSportRules();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentSportUpdates;
            case 1:
                return fragmentSportFixtures;
            case 2:
                return fragmentSportResults;
            case 3:
                return fragmentSportRules;
            case 4:
                return fragmentSportContacts;
            case 5:
                return fragmentSportHallOfFame;
        }
        return null;
    }

    @Override
    public int getCount() {
        return Constants.TAB_SPORTS_TITLES.length;
    }

    public Fragment getFragmentInstance() {
        return fragmentSportHallOfFame;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.TAB_SPORTS_TITLES[position];
    }

    public String getFragmentTag(int viewPagerID, int pos) {
        return "android:switcher:" + viewPagerID + ":" + pos;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public void updateFragment(String w, String r) {
        if (fragmentSportHallOfFame != null) {
            //TODO crashes sometimes
            fragmentSportHallOfFame.tvWinner.setText(w);
            fragmentSportHallOfFame.tvRunnerUp.setText(r);
        }
    }

    public void updateContacts(ArrayList<Contact> contacts) {
        if (fragmentSportContacts != null) {

        }
    }
}
