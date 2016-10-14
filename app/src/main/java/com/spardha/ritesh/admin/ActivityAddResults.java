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
import com.spardha.ritesh.models.Result;
import com.spardha.ritesh.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 10/15/16.
 */
public class ActivityAddResults extends AppCompatActivity {

    private DatabaseReference dbRef;
    private EditText etSportDesc, etTime, etTeam1, etTeam2, etVerdict, etScore;
    private String sportDesc, time, team1, team2, score, verdict;
    private String SPORT_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sport_add_results);
        dbRef = FirebaseDatabase.getInstance().getReference("results");
        SPORT_NAME = getIntent().getStringExtra(Constants.INTENT_STRING_SPORT_NAME);

        etSportDesc = (EditText) findViewById(R.id.etSportsDesc);
        etTime = (EditText) findViewById(R.id.etTime);
        etScore = (EditText) findViewById(R.id.etScore);
        etVerdict = (EditText) findViewById(R.id.etVerdict);
        etTeam1 = (EditText) findViewById(R.id.etTeam1);
        etTeam2 = (EditText) findViewById(R.id.etTeam2);

        Button btAddResult = (Button) findViewById(R.id.btSubmitFixture);

        btAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sportDesc = etSportDesc.getText().toString();
                time = etTime.getText().toString();
                team1 = etTeam1.getText().toString();
                team2 = etTeam2.getText().toString();
                verdict = etVerdict.getText().toString();
                score = etScore.getText().toString();
                verdict = etVerdict.getText().toString();

                Result result = new Result(team1, team2, SPORT_NAME, sportDesc, time, score, verdict);
                String key = dbRef.push().getKey();
                Map<String, Object> fixtureUpdateMap = result.toMap();
                Map<String, Object> updates = new HashMap<>();
                updates.put(key, fixtureUpdateMap);
                dbRef.updateChildren(updates);
                Toast.makeText(ActivityAddResults.this, "Result Update has been queued for publishing!", Toast.LENGTH_LONG).show();
                etSportDesc.setText("");
                etTime.setText("");
                etVerdict.setText("");
                etScore.setText("");
                etTeam1.setText("");
                etTeam2.setText("");

            }
        });


    }
}
