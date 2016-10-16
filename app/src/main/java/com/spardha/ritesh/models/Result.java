package com.spardha.ritesh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 10/15/16.
 */
@IgnoreExtraProperties
public class Result {

    public String team1;
    public String team2;
    public String sport;
    public String sport_desc;
    public String date;
    public String score;
    public String verdict;

    public Result() {

    }


    public Result(String team1, String team2, String sport, String sportDescription, String date, String score, String verdict) {
        this.team1 = team1;
        this.team2 = team2;
        this.sport = sport;
        this.sport_desc = sportDescription;
        this.score = score;
        this.date = date;
        this.verdict = verdict;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("team1", team1);
        result.put("team2", team2);
        result.put("sport", sport);
        result.put("sport_desc", sport_desc);
        result.put("date", date);
        result.put("score", score);
        result.put("verdict", verdict);

        return result;
    }
}