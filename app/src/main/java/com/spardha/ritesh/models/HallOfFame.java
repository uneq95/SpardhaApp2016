package com.spardha.ritesh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 6/3/16.
 */
@IgnoreExtraProperties
public class HallOfFame {

    public String runner_up;
    public String winner;

    public HallOfFame(){

    }

    public HallOfFame(String winner,String runner_up){
        this.winner=winner;
        this.runner_up = runner_up;
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("runner_up", runner_up);
        result.put("winner",winner);
        return result;
    }
}
