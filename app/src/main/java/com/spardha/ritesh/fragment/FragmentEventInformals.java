package com.spardha.ritesh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterRVInformalsList;
import com.spardha.ritesh.models.Informals;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 9/30/16.
 */

public class FragmentEventInformals extends Fragment {

    private ArrayList<Informals> informalsList;
    private RecyclerView recyclerView;
    String TAG="informals fragment ";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_events_grid,container,false);
        recyclerView = (RecyclerView) superView.findViewById(R.id.rvEventGrid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.grid_spacing);
        recyclerView.addItemDecoration(itemDecoration);
        return superView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("informals");

        final ValueEventListener availableEventsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> eventsChildren = dataSnapshot.getChildren();
                informalsList = new ArrayList<>();
                for (DataSnapshot event : eventsChildren
                        ) {

                    Informals informalEvent = event.getValue(Informals.class);
                    Log.d(TAG,"name: "+informalEvent.name+" link: "+informalEvent.img_link);
                    informalsList.add(informalEvent);
                }
                updateEvents(informalsList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(availableEventsListener);
    }

    private void updateEvents(ArrayList<Informals> events){
        AdapterRVInformalsList adapterRVInformalsList = new AdapterRVInformalsList(getActivity(), events);
        recyclerView.setAdapter(adapterRVInformalsList);
        Log.e(TAG,"setting rv");
    }
}
