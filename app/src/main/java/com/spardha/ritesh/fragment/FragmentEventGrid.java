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
import com.spardha.ritesh.adapter.AdapterRVEventList;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/30/16.
 */
public class FragmentEventGrid extends Fragment {

    View superView;
    ArrayList<SportEvent> availableSportsList;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference dbRef;
    static boolean calledAlready = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.fragment_events_grid,container,false);
        recyclerView = (RecyclerView) superView.findViewById(R.id.rvEventGrid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.grid_spacing);
        recyclerView.addItemDecoration(itemDecoration);
        return superView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef=firebaseDatabase.getReference("sport_list");
        final ValueEventListener availableEventsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> eventsChildren = dataSnapshot.getChildren();
                availableSportsList = new ArrayList<>();
                for (DataSnapshot sportEvent:eventsChildren
                        ) {

                    SportEvent se = sportEvent.getValue(SportEvent.class);
                    Log.d("contact:: ",se.sport_name+" "+se.header_url);
                    availableSportsList.add(se);
                }
                updateEvents(availableSportsList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(availableEventsListener);

    }

    public void updateEvents(ArrayList<SportEvent> availableSportsList){
        AdapterRVEventList adapterRVEventGrid = new AdapterRVEventList(getActivity(),availableSportsList);
        recyclerView.setAdapter(adapterRVEventGrid);
    }

}
