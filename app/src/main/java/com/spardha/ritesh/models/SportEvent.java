package com.spardha.ritesh.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by ritesh on 7/3/16.
 */
@IgnoreExtraProperties
public class SportEvent implements Serializable {

    public String header_url;
    public String sport_name;

    public SportEvent(){

    }
    public SportEvent(String header_url,String sport_name){
        this.header_url=header_url;
        this.sport_name=sport_name;
    }


}
