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
import com.spardha.ritesh.adapter.AdapterRVResultsList;
import com.spardha.ritesh.models.Result;
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportResults extends Fragment {

    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private String SPORTS_NAME;
    private TextView tvResultsUnavailable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_results, container, false);
        recyclerView = (RecyclerView) superView.findViewById(R.id.rvSportResults);
        tvResultsUnavailable = (TextView) superView.findViewById(R.id.tvResultsUnavailable);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        Bundle bundle = this.getArguments();
        SPORTS_NAME = bundle.getString(Constants.INTENT_STRING_SPORT_NAME);
        recyclerView.setLayoutManager(gridLayoutManager);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.grid_spacing);
        recyclerView.addItemDecoration(itemDecoration);
        return superView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("results");
        ValueEventListener resultsChangeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                results = new ArrayList<>();
                Iterable<DataSnapshot> resultsIterable = dataSnapshot.getChildren();
                for (DataSnapshot resultSnapshot : resultsIterable) {
                    Result result = resultSnapshot.getValue(Result.class);
                    if (result.sport.toLowerCase().equals(SPORTS_NAME.toLowerCase()))
                        results.add(result);
                }

                updateFixturesList(results);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(resultsChangeListener);
    }

    private void updateFixturesList(ArrayList<Result> results) {

        if (results.size() > 0)
            if (tvResultsUnavailable != null) {
                if (tvResultsUnavailable.getVisibility() == View.VISIBLE) {
                    tvResultsUnavailable.setVisibility(View.GONE);
                }
            }
        AdapterRVResultsList adapterRVResultsList = new AdapterRVResultsList(getActivity(), results);
        recyclerView.setAdapter(adapterRVResultsList);
    }
}
