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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterRVTestimonials;
import com.spardha.ritesh.models.Testimonial;
import com.spardha.ritesh.utils.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by ritesh on 7/11/16.
 */
public class FragmentTestimonials extends Fragment {

    View superView;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.fragment_testimonials, container, false);
        recyclerView = (RecyclerView) superView.findViewById(R.id.rvTestimonials);

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
        DatabaseReference databaseReference = firebaseDatabase.getReference("testimonials");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> eventsChildren = dataSnapshot.getChildren();
                ArrayList<Testimonial> testimonials = new ArrayList<>();
                for (DataSnapshot testimonialObject : eventsChildren
                        ) {

                    Testimonial se = testimonialObject.getValue(Testimonial.class);
                    testimonials.add(se);
                }
                updateTestimonials(testimonials);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    private void updateTestimonials(ArrayList<Testimonial> testimonials) {
        AdapterRVTestimonials adapterRVTestimonials = new AdapterRVTestimonials(getActivity(), testimonials);
        recyclerView.setAdapter(adapterRVTestimonials);
    }
}
