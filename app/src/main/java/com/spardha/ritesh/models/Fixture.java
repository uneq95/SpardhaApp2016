package com.spardha.ritesh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 10/12/16.
 */
@IgnoreExtraProperties
public class Fixture {

    public String team1;
    public String team2;
    public String sport;
    public String sport_desc;
    public String venue;
    public String time;

    public Fixture() {

    }

    public Fixture(String team1, String team2, String sport, String sportDescription, String venue, String time) {
        this.team1 = team1;
        this.team2 = team2;
        this.sport = sport;
        this.sport_desc = sportDescription;
        this.venue = venue;
        this.time = time;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("team1", team1);
        result.put("team2", team2);
        result.put("sport", sport);
        result.put("sport_desc", sport_desc);
        result.put("venue", venue);
        result.put("time", time);

        return result;
    }

}
