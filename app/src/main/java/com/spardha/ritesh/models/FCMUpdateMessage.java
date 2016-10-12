package com.spardha.ritesh.models;

import com.google.firebase.database.IgnoreExtraProperties;

import io.realm.RealmObject;

/**
 * Created by ritesh on 10/10/16.
 */
@IgnoreExtraProperties
public class FCMUpdateMessage extends RealmObject {

    public long msg_id;

    /*message type 1*/
    public String msg_title;
    public String msg_text;

    /*message type 2*/
    public String img_link;
    //@param msg_text -- second data element image title link
    public String redirect_link;


    public FCMUpdateMessage() {

    }

}
