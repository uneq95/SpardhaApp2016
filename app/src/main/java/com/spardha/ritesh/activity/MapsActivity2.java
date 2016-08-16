package com.spardha.ritesh.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spardha.ritesh.R;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float zoomlevel = (float) 14.55;

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
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
