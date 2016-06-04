package com.spardha.ritesh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spardha.ritesh.R;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportContacts extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_contacts,container,false);
        return superView;
    }
}
