package com.spardha.ritesh.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.SportsViewPagerAdapter;
import com.spardha.ritesh.models.Contact;
import com.spardha.ritesh.models.Sport;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/3/16.
 */
public class SportsActivity extends AppCompatActivity {


    private String SPORTS_NAME = "Cricket";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    Sport referenceValue ;
    SportsViewPagerAdapter sportsViewPagerAdapter;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the instance of firebase database
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("cricket");
        referenceValue = new Sport();

        setContentView(R.layout.activity_sports_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(SPORTS_NAME);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        ImageView header = (ImageView) findViewById(R.id.htab_header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header_cricket);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });
    }


    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager(ViewPager viewPager) {
        sportsViewPagerAdapter = new SportsViewPagerAdapter(getSupportFragmentManager());
        //sportsViewPagerAdapter.
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(sportsViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ValueEventListener sportsDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sport sport = dataSnapshot.getValue(Sport.class);
                //TODO update the contents of fragments on data retrieval

                DataSnapshot contactSnapshot=dataSnapshot.child("contacts");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                ArrayList<Contact> contacts = new ArrayList<>();
                for (DataSnapshot contact:contactChildren
                     ) {

                    Contact c = contact.getValue(Contact.class);
                    Log.d("contact:: ",c.name+" "+c.phone);
                    contacts.add(c);
                    //TODO use this arraylist containing contact and update it in FragmentSportContact fragment instance from the SportsViewPagerAdapter

                }

                System.out.println(getSupportFragmentManager().getFragments());
                sportsViewPagerAdapter.updateFragment(sport.hall_of_fame.winner,sport.hall_of_fame.runner_up);
                String string = String.format("header:%s\nrule:%s\nrunner up:%s\nwinner:%s", sport.header_url, sport.rules, sport.hall_of_fame.runner_up, sport.hall_of_fame.winner);
                Log.d("Data from firebase: ", string);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SportsActivity.this, "Network error", Toast.LENGTH_LONG).show();

            }
        };
        myRef.addValueEventListener(sportsDataListener);

    }
}
