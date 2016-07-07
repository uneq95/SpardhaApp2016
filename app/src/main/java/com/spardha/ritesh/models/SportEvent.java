package com.spardha.ritesh.models;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;
import com.spardha.ritesh.utils.ImageSaver;

import java.io.File;
import java.io.Serializable;

/**
 * Created by ritesh on 7/3/16.
 */
@IgnoreExtraProperties
public class SportEvent implements Serializable {

    /*parameters loaded fro Firebase*/
    public String header_url;
    public String sport_name;

    /*parameters for local optimisation*/
    private Bitmap headerBitmap=null;

    public SportEvent(){

    }
    public SportEvent(String header_url,String sport_name){
        this.header_url=header_url;
        this.sport_name=sport_name;
    }

    public void setLocalBitmap(Bitmap bitmap){
        headerBitmap=bitmap;
    }
    public Bitmap getLocalBitmap(){
        return headerBitmap;
    }
    public boolean isHeaderBitmapAvailable(){
        return headerBitmap != null;
    }


}
