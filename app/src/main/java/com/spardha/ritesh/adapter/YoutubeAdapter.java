package com.spardha.ritesh.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.models.YouTubeVideo;
import com.spardha.ritesh.utils.AppSingleton;

import java.util.ArrayList;


/**
 * Created by ritesh_kumar on 10-May-16.
 */
public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

    ArrayList<YouTubeVideo> videoList;
    Context context;

    public YoutubeAdapter(ArrayList<YouTubeVideo> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_youtube_video_object, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvVidTitle.setText(videoList.get(position).getVideoTitle());
        holder.tvVidDescription.setText(videoList.get(position).getVidDescription());
        String thumbnailURL = videoList.get(position).getVidThumbnailURL();
        //TODO load image through volley
        ImageRequest request = new ImageRequest(thumbnailURL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.ivVidThumbnail.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //holder.ivVidThumbnail.setImageResource(R.drawable.image_load_error);
                    }
                });
// Access the RequestQueue through your singleton class.
        AppSingleton.getInstance(context).addToRequestQueue(request);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVidTitle, tvVidDescription;
        ImageView ivVidThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            //TODO references to the text views
            tvVidDescription = (TextView) itemView.findViewById(R.id.tvVidDesc);
            tvVidTitle = (TextView) itemView.findViewById(R.id.tvVidTitle);
            ivVidThumbnail = (ImageView) itemView.findViewById(R.id.ivVidThumbnail);

        }
    }
}
