package com.spardha.ritesh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 6/3/16.
 */

@IgnoreExtraProperties
public class Sport {

    public String rules;
    public String header_url;
    public HallOfFame hall_of_fame;

    public Sport(){

    }

    public Sport(String rules, String header_url, HallOfFame hall_of_fame){

        this.rules = rules;
        this.header_url=header_url;
        this.hall_of_fame = hall_of_fame;

    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("hall_of_fame", hall_of_fame);
        result.put("header_url",header_url);
        result.put("rules", rules);
        return result;
    }
}
