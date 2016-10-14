package com.spardha.ritesh.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.utils.Constants;

/**
 * Created by ritesh on 10/13/16.
 */

public class ActivitySportSelect extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sport_select);
        GridView gv = (GridView) findViewById(R.id.gvSprtSelectGrid);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Constants.SPORT_SELECT_GRID_SELECTIONS);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivitySportSelect.this,ActivitySportOptions.class);
                intent.putExtra(Constants.INTENT_STRING_SPORT_NAME,Constants.SPORT_SELECT_GRID_SELECTIONS[position]);
                startActivity(intent);
            }
        });
    }
}
