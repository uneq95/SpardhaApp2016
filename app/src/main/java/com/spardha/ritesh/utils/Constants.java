package com.spardha.ritesh.utils;

/**
 * Created by ritesh_kumar on 09-May-16.
 */
public class Constants {

    /*Constants for JSONParser*/
    private static final String GOOGLE_DEV_KEY = "AIzaSyDiNPwcPs6q8ctybTymfAQl7bG8dZUaS04";
    private static final String SPARDHA_YOUTUBE_CHANNEL_ID = "UCA8-Lv8lTufkY507CUonbDQ";

    public static String getSpardhaChannelURL() {
        return String.format("https://www.googleapis.com/youtube/v3/search?key=%s&channelId=%s&part=snippet,id&order=date&maxResults=50&type=video", GOOGLE_DEV_KEY, SPARDHA_YOUTUBE_CHANNEL_ID);
    }

    public static String getGoogleDevKey() {
        return GOOGLE_DEV_KEY;
    }

    /*Constants for Youtube Adapter and ActivityYoutubePlayer*/
    public static final String EXTRA_VIDEO_ID = "YOUTUBE_VIDEO_ID";

    /*Constants for AdapterEventsGrid*/
    public static final String EXTRA_SPORT_NAME = "SPORT_NAME";

    /*Constants for AdapterViewPagerHome*/
    public static final String[] TAB_HOME_TITLES = {"UPDATES", "EVENTS", "INFORMALS", "PHOTO WALL", "VIDEO WALL", "TESTIMONIALS"};

    /*Constants for AdapterViewPagerSports*/
    public static final String[] TAB_SPORTS_TITLES = {"UPDATES", "FIXTURES", "RESULTS", "RULES", "CONTACTS", "HALL OF FAME"};


}
