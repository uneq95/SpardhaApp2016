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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.activity.ActivitySports;
import com.spardha.ritesh.adapter.AdapterSportContacts;
import com.spardha.ritesh.models.Contact;
import com.spardha.ritesh.models.Sport;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportContacts extends Fragment {

    RecyclerView recyclerView;
    AdapterSportContacts adapter;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference dbRef;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_contacts,container,false);
        recyclerView =(RecyclerView)superView.findViewById(R.id.rvContacts);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.grid_spacing);
        recyclerView.addItemDecoration(itemDecoration);
        Bundle bundle =this.getArguments();

        String SPORTS_NAME = bundle.getString("sport_name");
        //String SPORTS_NAME ="cricket";
                firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef=firebaseDatabase.getReference(SPORTS_NAME);
        final ValueEventListener sportsDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sport sport = dataSnapshot.getValue(Sport.class);
                //TODO update the contents of fragments on data retrieval

                DataSnapshot contactSnapshot = dataSnapshot.child("contacts");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                ArrayList<Contact> contacts = new ArrayList<>();
                for (DataSnapshot contact : contactChildren
                        ) {

                    Contact c = contact.getValue(Contact.class);
                    Log.d("contact:: ", c.name + " " + c.phone);
                    contacts.add(c);
                    //TODO use this arraylist containing contact and update it in FragmentSportContact fragment instance from the AdapterViewPagerSports

                }
                setRecyclerView(contacts);
                //String string = String.format("header:%s\nrule:%s\nrunner up:%s\nwinner:%s", sport.header_url, sport.rules, sport.hall_of_fame.runner_up, sport.hall_of_fame.winner);
                //Log.d("Data from firebase: ", string);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_LONG).show();

            }
        };
        dbRef.addValueEventListener(sportsDataListener);

        return superView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setRecyclerView(ArrayList<Contact> contacts){
        adapter= new AdapterSportContacts(contacts,getContext());
        recyclerView.setAdapter(adapter);
    }
}
