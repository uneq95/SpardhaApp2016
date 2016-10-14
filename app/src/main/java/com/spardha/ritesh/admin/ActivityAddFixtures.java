package com.spardha.ritesh.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spardha.ritesh.R;
import com.spardha.ritesh.models.Fixture;
import com.spardha.ritesh.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 10/14/16.
 */

public class ActivityAddFixtures extends AppCompatActivity {

    private DatabaseReference dbRef;
    private EditText etSportDesc, etTime, etVenue, etTeam1, etTeam2;
    private String sportDesc, time, venue, team1, team2;
    private String SPORT_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sport_add_fixtures);
        dbRef = FirebaseDatabase.getInstance().getReference("fixtures");
        SPORT_NAME = getIntent().getStringExtra(Constants.INTENT_STRING_SPORT_NAME);

        etSportDesc = (EditText) findViewById(R.id.etSportsDesc);
        etTime = (EditText) findViewById(R.id.etTime);
        etVenue = (EditText) findViewById(R.id.etVenue);
        etTeam1 = (EditText) findViewById(R.id.etTeam1);
        etTeam2 = (EditText) findViewById(R.id.etTeam2);

        Button btAddFixture = (Button) findViewById(R.id.btSubmitFixture);

        btAddFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sportDesc = etSportDesc.getText().toString();
                time = etTime.getText().toString();
                venue = etVenue.getText().toString();
                team1 = etTeam1.getText().toString();
                team2 = etTeam2.getText().toString();

                Fixture fixture = new Fixture(team1, team2, SPORT_NAME, sportDesc, venue, time);
                String key = dbRef.push().getKey();
                Map<String, Object> fixtureUpdateMap = fixture.toMap();
                Map<String, Object> updates = new HashMap<>();
                updates.put(key, fixtureUpdateMap);
                dbRef.updateChildren(updates);
                Toast.makeText(ActivityAddFixtures.this, "Fixture Update has been queued for publishing!", Toast.LENGTH_LONG).show();
                etSportDesc.setText("");
                etTime.setText("");
                etVenue.setText("");
                etTeam1.setText("");
                etTeam2.setText("");

            }
        });

    }
}
