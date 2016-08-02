package com.spardha.ritesh.models;

import android.graphics.Bitmap;

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

    /*parameters for local optimisation*/
    private Bitmap imageBitmap = null;

    public Testimonial() {

    }

    public void setLocalBitmap(Bitmap bitmap) {
        imageBitmap = bitmap;
    }

    public Bitmap getLocalBitmap() {
        return imageBitmap;
    }

    public boolean isHeaderBitmapAvailable() {
        return imageBitmap != null;
    }

}
