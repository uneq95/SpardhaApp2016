package com.spardha.ritesh.utils;

/**
 * Created by ritesh_kumar on 09-May-16.
 */
public class Constants {

    private static final String GOOGLE_DEV_KEY = "AIzaSyDiNPwcPs6q8ctybTymfAQl7bG8dZUaS04";
    private static final String SPARDHA_YOUTUBE_CHANNEL_ID = "UCA8-Lv8lTufkY507CUonbDQ";

    public static String getSpardhaChannelURL() {
        return String.format("https://www.googleapis.com/youtube/v3/search?key=%s&channelId=%s&part=snippet,id&order=date&maxResults=50&type=video", GOOGLE_DEV_KEY, SPARDHA_YOUTUBE_CHANNEL_ID);
    }

}
