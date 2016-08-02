package com.spardha.ritesh.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by ritesh on 8/2/16.
 */
public class Utilities {

    public static void makeCall(Context context,String mobileNum){

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(String.format("tel:%s", mobileNum)));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            if ( ContextCompat.checkSelfPermission( context, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions((Activity) context, new String[] {  Manifest.permission.CALL_PHONE  },0);
            }else{
                context.startActivity(intent);
            }
        }else{
            context.startActivity(intent);
        }



    }
    
}
