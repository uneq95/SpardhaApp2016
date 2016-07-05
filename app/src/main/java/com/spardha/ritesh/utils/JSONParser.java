package com.spardha.ritesh.utils;


import com.spardha.ritesh.models.YouTubeVideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 10-May-16.
 */
public class JSONParser {

    public static ArrayList<YouTubeVideo> getYoutubeVideoList(JSONObject jsonObject) throws JSONException {

        ArrayList<YouTubeVideo> videoList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject arrayObject = jsonArray.getJSONObject(i);
            String videoTitle = arrayObject.getJSONObject("snippet").getString("title");
            String videoID = arrayObject.getJSONObject("id").getString("videoId");
            String vidThumbnailURL = arrayObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
            String vidDescription = arrayObject.getJSONObject("snippet").getString("description");
            YouTubeVideo youTubeVideo = new YouTubeVideo(videoID, vidThumbnailURL, videoTitle, vidDescription);
            videoList.add(youTubeVideo);

        }

        return videoList;

    }
}
