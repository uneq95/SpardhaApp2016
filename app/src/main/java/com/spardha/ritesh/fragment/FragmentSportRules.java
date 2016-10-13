package com.spardha.ritesh.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.Utilities;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportRules extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_rules, container, false);
        Bundle bundle = this.getArguments();
        final CardView downloadCard = (CardView) superView.findViewById(R.id.downloadCard);
        final String SPORTS_NAME = bundle.getString(Constants.INTENT_STRING_SPORT_NAME);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference(SPORTS_NAME);
        final ValueEventListener sportsDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO update the contents of fragments on data retrieval
                DataSnapshot contactSnapshot = dataSnapshot.child("rules");
                final String url = contactSnapshot.getValue(String.class);

                downloadCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ToDo start downloading rule book
                        if (Utilities.isInternetAvailable(getContext())) {
                            if (url.length() > 0) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                browserIntent.setData(Uri.parse(url));
                                startActivity(browserIntent);
                            } else {
                                Toast.makeText(getContext(), "Rule book for " + SPORTS_NAME + " isn't available yet!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getContext(), "Internet Unavailable!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_LONG).show();

            }
        };
        dbRef.addValueEventListener(sportsDataListener);

        return superView;
    }
}
