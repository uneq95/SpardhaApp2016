package com.spardha.ritesh.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.spardha.ritesh.R;
import com.spardha.ritesh.utils.Constants;

/**
 * Created by ritesh on 7/7/16
 */
public class ActivityYouTubePlayer extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {

    YouTubePlayer youtubeplayer;

    private String VIDEO_ID;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube_player);
        Intent videoIntent = getIntent();
        VIDEO_ID = videoIntent.getStringExtra(Constants.EXTRA_VIDEO_ID);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(Constants.getGoogleDevKey(), this);
    }

    @Override
    public void onFullscreen(boolean b) {
        if (youtubeplayer != null) {
            youtubeplayer.play();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        this.youtubeplayer = youTubePlayer;
        youtubeplayer.setOnFullscreenListener(this);
        if (!wasRestored) {
            youtubeplayer.loadVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
