package com.spardha.ritesh.models;

/**
 * Created by ritesh_kumar on 10-May-16.
 */
public class YouTubeVideo {

    private String videoTitle;
    private String vidThumbnailURL;
    private String videoID;
    private String vidDescription;

    public YouTubeVideo(String videoID, String vidThumbnailURL, String videoTitle, String vidDescription) {
        this.setVideoID(videoID);
        this.setVideoTitle(videoTitle);
        this.setVidThumbnailURL(vidThumbnailURL);
        this.setVidDescription(vidDescription);

    }

    public String getVideoTitle() {
        return videoTitle;
    }

    private void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVidThumbnailURL() {
        return vidThumbnailURL;
    }

    private void setVidThumbnailURL(String vidThumbnailURL) {
        this.vidThumbnailURL = vidThumbnailURL;
    }

    public String getVideoID() {
        return videoID;
    }

    private void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVidDescription() {
        return vidDescription;
    }

    private void setVidDescription(String vidDescription) {
        this.vidDescription = vidDescription;
    }
}
