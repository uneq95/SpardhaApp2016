package com.spardha.ritesh.models;

import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ritesh on 9/30/16.
 */
@IgnoreExtraProperties
public class Informals {

    public String name;
    public String img_link;

    /*parameters for local optimisation*/
    private Bitmap headerBitmap = null;

    public Informals(){

    }
    public void setLocalBitmap(Bitmap bitmap) {
        headerBitmap = bitmap;
    }

    public Bitmap getLocalBitmap() {
        return headerBitmap;
    }

    public boolean isHeaderBitmapAvailable() {
        return headerBitmap != null;
    }
}
