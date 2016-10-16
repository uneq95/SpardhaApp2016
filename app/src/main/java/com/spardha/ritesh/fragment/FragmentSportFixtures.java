package com.spardha.ritesh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterRVFixturesList;
import com.spardha.ritesh.models.Fixture;
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportFixtures extends Fragment {


    private ArrayList<Fixture> fixtures;
    private RecyclerView recyclerView;
    private String SPORTS_NAME;
    private TextView tvFixtureunavailable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_fixtures, container, false);
        recyclerView = (RecyclerView) superView.findViewById(R.id.rvSportFixtures);
        tvFixtureunavailable = (TextView) superView.findViewById(R.id.tvFixtureUnavailable);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        Bundle bundle = this.getArguments();
        SPORTS_NAME = bundle.getString(Constants.INTENT_STRING_SPORT_NAME);
        recyclerView.setLayoutManager(gridLayoutManager);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.recycler_view_item_spacing);
        recyclerView.addItemDecoration(itemDecoration);
        return superView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("fixtures");
        ValueEventListener fixturesChangeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fixtures = new ArrayList<>();
                Iterable<DataSnapshot> fixturesIterable = dataSnapshot.getChildren();
                for (DataSnapshot fixtureSnapshot : fixturesIterable) {
                    Fixture fixture = fixtureSnapshot.getValue(Fixture.class);
                    if (fixture.sport.toLowerCase().equals(SPORTS_NAME.toLowerCase()))
                        fixtures.add(fixture);
                }

                updateFixturesList(fixtures);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(fixturesChangeListener);
    }

    private void updateFixturesList(ArrayList<Fixture> fixtures) {

        if (fixtures.size() > 0)
            if (tvFixtureunavailable != null) {
                if (tvFixtureunavailable.getVisibility() == View.VISIBLE) {
                    tvFixtureunavailable.setVisibility(View.GONE);
                }
            }
        AdapterRVFixturesList adapterRVFixturesList = new AdapterRVFixturesList(getActivity(), fixtures);
        recyclerView.setAdapter(adapterRVFixturesList);
    }
}