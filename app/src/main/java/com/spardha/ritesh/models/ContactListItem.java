package com.spardha.ritesh.models;

import android.graphics.Bitmap;

/**
 * Created by ritesh_kumar on 21-Jul-15.
 */
public class ContactListItem {

    //for contacts
    private String name, email, phoneNumber, picLink;
    private int photoResId = -1;
    //for contact header
    private String contactHeader;
    // flag
    private boolean isHeader;

    private Bitmap contactImgBitmap = null;

    // constructor for contacts
    public ContactListItem(String name, String desg, String phn, int photoRes) {
        this.name = name;
        this.email = desg;
        this.phoneNumber = phn;
        this.photoResId = photoRes;
        this.isHeader = false;
    }

    public ContactListItem(String name, String desg, String phn, String picLink) {
        this.name = name;
        this.email = desg;
        this.phoneNumber = phn;
        this.picLink = picLink;
        this.isHeader = false;
    }

    //constructor for contact header
    public ContactListItem(String header) {
        this.isHeader = true;
        this.contactHeader = header;
    }

    public String getContactName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPhotoResId() {
        return photoResId;
    }

    public String getContactHeader() {
        return contactHeader;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setLocalBitmap(Bitmap bitmap) {
        contactImgBitmap = bitmap;
    }

    public Bitmap getLocalBitmap() {
        return contactImgBitmap;
    }

    public boolean isHeaderBitmapAvailable() {
        return contactImgBitmap != null;
    }


}
