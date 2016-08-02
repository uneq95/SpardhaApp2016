package com.spardha.ritesh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritesh on 6/3/16.
 */
@IgnoreExtraProperties
public class Contact {

    public String name;
    public String phone;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue(Contact.class)
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("phone", phone);
        return result;
    }
}
