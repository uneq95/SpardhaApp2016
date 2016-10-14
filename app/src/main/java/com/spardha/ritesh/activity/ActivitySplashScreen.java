package com.spardha.ritesh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.spardha.ritesh.R;
import com.spardha.ritesh.admin.ActivitySportSelect;

/**
 * Created by ritesh on 10/9/16.
 */

public class ActivitySplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //Intent dataSyncService = new Intent(this, DataSyncService.class);
        //startService(dataSyncService);
        /*Intent homeActivity = new Intent(this, ActivityHome.class);
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeActivity);
        finish();*/

        Intent adminIntent = new Intent(this, ActivitySportSelect.class);
        adminIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(adminIntent);
        finish();

    }
}
