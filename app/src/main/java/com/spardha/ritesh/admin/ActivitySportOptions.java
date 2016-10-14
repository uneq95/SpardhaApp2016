package com.spardha.ritesh.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.utils.Constants;

/**
 * Created by ritesh on 10/13/16.
 */

public class ActivitySportOptions extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sport_options);
        ListView lv = (ListView)findViewById(R.id.lvSportOptions);
        final String SPORT_NAME = getIntent().getStringExtra(Constants.INTENT_STRING_SPORT_NAME);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Constants.SPORT_ADMIN_OPTIONS);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(ActivitySportOptions.this, ActivityAddFixtures.class);
                    intent.putExtra(Constants.INTENT_STRING_SPORT_NAME,SPORT_NAME);
                    startActivity(intent);
                }

            }
        });

    }
}
