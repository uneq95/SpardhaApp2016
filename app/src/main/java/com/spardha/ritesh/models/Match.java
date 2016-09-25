package com.spardha.ritesh.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ritesh on 9/12/16.
 */

@IgnoreExtraProperties
public class Match {

    String event_name;
    String event_time;
    String gender;
    String venue;

    String teamA;
    String teamB;
    String scoreA;
    String scoreB;


}
