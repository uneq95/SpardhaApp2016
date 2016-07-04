package com.spardha.ritesh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spardha.ritesh.fragment.FragmentEventGrid;
import com.spardha.ritesh.fragment.FragmentSportContacts;
import com.spardha.ritesh.models.SportEvent;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/29/16.
 */
public class AdapterViewPagerHome extends FragmentStatePagerAdapter {

    String[] TAB_TITLES = {"UPDATES", "EVENTS", "INFORMALS", "PHOTO WALL", "VIDEO WALL", "TESTIMONIALS"};

    ArrayList<SportEvent> availableSportsList;

    public AdapterViewPagerHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1://Bundle bundle = new Bundle();
                //bundle.putSerializable("EVENTS_LIST",availableSportsList);
                FragmentEventGrid fragmentEventGrid= new FragmentEventGrid();
                //fragmentEventGrid.setArguments(bundle);
                return fragmentEventGrid;
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
    public void eventData(ArrayList<SportEvent> sportEvents){
        ((FragmentEventGrid)this.getItem(1)).updateEvents(sportEvents);
    }
}
