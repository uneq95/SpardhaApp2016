package com.spardha.ritesh.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spardha.ritesh.R;
import com.spardha.ritesh.views.fab.FloatingActionButton;
import com.spardha.ritesh.views.fab.FloatingActionsMenu;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {


    private GoogleMap mMap;
    float zoomlevel = (float) 14.55;
    private FloatingActionButton button_hospital, button_venue, button_atm, button_hostel, button_miscellaneous;
    private FloatingActionsMenu fam;
    private final int LOCATION_REQUEST_CODE = 1;

    private final String[] HOSTEL_NAME = {"Visweshwarayya", "SN Bose", "Aryabhatta", "Ramanujan", "Dhanrajgiri", "Morvi", "CV Raman", "Rajputana", "Limbdi", "SC De", "Vivekanand", "Vishwakarma", "GSMC"};
    private final double[] HOSTEL_LATITUDE = {25.262834, 25.263125, 25.264012, 25.263124, 25.263912, 25.265025, 25.265865, 25.262418, 25.261312, 25.260184, 25.259272, 25.257690, 25.260601};
    private final double[] HOSTEL_LONGITUDE = {82.983902, 82.983886, 82.984352, 82.984826, 82.986277, 82.986382, 82.986481, 82.986349, 82.986524, 82.986859, 82.987251, 82.985695, 82.983670};

    private final String[] ATM_NAME = {"SBI Hyderabad Gate", "Axis Bank Hyderabad Gate", "SBI-BHU", "SBI VT", "Bank of Baroda"};
    private final double[] ATM_LATITUDE = {25.261717, 25.261593, 25.263738, 25.265331, 25.265386};
    private final double[] ATM_LONGITUDE = {82.981647, 82.981642, 82.994762, 82.989635, 82.989643};

    private final String[] VENUE_NAME = {"IIT-BHU Gymkhana", "Rajputana Ground", "Amphitheatre", "Arun Dream Village(ADV)"};
    private final double[] VENUE_LATITUDE = {25.259176, 25.262499, 25.265730, 25.259040};
    private final double[] VENUE_LONGITUDE = {82.989229, 82.986691, 82.995231, 82989331};

    private final String[] HOSPITAL_NAME = {"Sir Sunderlal Hospital"};
    private final double[] HOSPITAL_LATITUDE = {25.275727};
    private final double[] HOSPITAL_LONGITUDE = {83.000570};

    private final String[] OTHER_NAMES = {"DG Corner", "Limbdi Corner", "Vishvanath Temple", "Swatantrata Bhawan"};
    private final double[] OTHER_LATITUDE = {25.263023, 25.260777, 25.265676, 25.260636};
    private final double[] OTHER_LONGITUDE = {82.986498, 82.986884, 82.989475, 82.993676};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fam = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        button_hospital = (FloatingActionButton) findViewById(R.id.button_hospital);
        button_venue = (FloatingActionButton) findViewById(R.id.button_venue);
        button_atm = (FloatingActionButton) findViewById(R.id.button_atm);
        button_hostel = (FloatingActionButton) findViewById(R.id.button_hostel);
        button_miscellaneous = (FloatingActionButton) findViewById(R.id.button_miscellaneous);

    }

    private void markUserLocation() {
        /** To Get Current Location **/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
        }

    }

    private void addMarker(double[] Latitude, double[] Longitude, String[] Title) {

        for (int i = 0; i < Latitude.length; i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(Latitude[i], Longitude[i])).title(Title[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_navigate) {

            Intent i = new Intent(MapsActivity.this, LocationActivity.class);
            startActivity(i);
            return true;
        }
*/
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // MAP sETTINGS
        markUserLocation();
        //for moving focus to BHU campus
        LatLng latLng = new LatLng(25.265834, 82.989499);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(zoomlevel));

        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setScrollGesturesEnabled(true);
        mapSettings.setTiltGesturesEnabled(true);
        mapSettings.setRotateGesturesEnabled(true);
        mapSettings.setMyLocationButtonEnabled(true);
        addMarker(HOSTEL_LATITUDE, HOSTEL_LONGITUDE, HOSTEL_NAME);
        button_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(HOSPITAL_LATITUDE, HOSPITAL_LONGITUDE, HOSPITAL_NAME);
                fam.collapse();
            }
        });


        button_venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(VENUE_LATITUDE, VENUE_LONGITUDE, VENUE_NAME);
                fam.collapse();

            }
        });


        button_atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(ATM_LATITUDE, ATM_LONGITUDE, ATM_NAME);
                fam.collapse();

            }
        });


        button_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(HOSTEL_LATITUDE, HOSTEL_LONGITUDE, HOSTEL_NAME);
                fam.collapse();

            }
        });

        button_miscellaneous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(OTHER_LATITUDE, OTHER_LONGITUDE, OTHER_NAMES);
                fam.collapse();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mMap.setMyLocationEnabled(true);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View windowView = LayoutInflater.from(this).inflate(R.layout.layout_info_window, null);
        TextView tvPlaceName = (TextView) windowView.findViewById(R.id.textView);
        tvPlaceName.setText(marker.getTitle());
        return windowView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        LatLng position = marker.getPosition();
        String sLong = Double.toString(position.longitude), sLat = Double.toString(position.latitude);
        Uri gmmIntentUri = Uri.parse(String.format("google.navigation:q=%s,%s&mode=w", sLat, sLong));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }else{
            Toast.makeText(this,"Oops! You don't have Google Maps app installed!",Toast.LENGTH_LONG).show();
        }

    }
}
