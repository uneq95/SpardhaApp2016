package com.spardha.ritesh.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ritesh on 7/11/16.
 */
@IgnoreExtraProperties
public class Testimonial {

    public String guest_job;
    public String guest_name;
    public String image_url;
    public String words;

    public Testimonial(){

    }
}
