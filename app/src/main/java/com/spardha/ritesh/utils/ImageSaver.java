package com.spardha.ritesh.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ritesh on 7/7/16.
 */
public class ImageSaver {

    private String TAG = "ImageSaver";
    private String directoryName = "images";
    private String fileName = "image.png";
    private Context context;

    public ImageSaver(Context context) {
        this.context = context;
    }

    public ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public void save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //@NonNull
    private File createFile() {
        File directory = context.getDir(directoryName, Context.MODE_PRIVATE);
        Log.d(TAG, new File(directory, fileName).getPath());
        return new File(directory, fileName);
    }

    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            Log.i(TAG, "returning bitmap");
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    Log.i(TAG, "closing stream");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean doesFileExist() {
        return createFile().exists();
    }
}