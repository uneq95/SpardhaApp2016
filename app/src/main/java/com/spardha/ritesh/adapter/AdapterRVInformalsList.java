package com.spardha.ritesh.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.activity.ActivitySports;
import com.spardha.ritesh.models.Informals;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.ImageSaver;

import java.util.ArrayList;

/**
 * Created by ritesh on 9/30/16.
 */

public class AdapterRVInformalsList extends RecyclerView.Adapter<AdapterRVEventList.ViewHolder> {

    private ArrayList<Informals>  informals;
    private Context context;
    private RequestQueue requestQueue;
    private static final String TAG = "AdapterRVEventList";
    public AdapterRVInformalsList(Context context, ArrayList<Informals> events){
        this.informals=events;
        this.context=context;
        requestQueue = AppSingleton.getInstance(context).getRequestQueue();
    }
    @Override
    public AdapterRVEventList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sports_grid_item_test, parent, false);
        return new AdapterRVEventList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterRVEventList.ViewHolder holder,final int position) {

        holder.tvSportName.setText(informals.get(position).name.toUpperCase());

        String fileName = informals.get(position).name.concat(".jpg");
        final ImageSaver imageSaver = new ImageSaver(context).setFileName(fileName);
        boolean isLocalImageAvailable = imageSaver.doesFileExist();

        if (isLocalImageAvailable) {

            Informals informalEvent = informals.get(position);
            Bitmap tempBitmap;
            if (informalEvent.isHeaderBitmapAvailable()) {
                tempBitmap = informalEvent.getLocalBitmap();
                Log.d(TAG, "image loaded from local bitmap: " + position);
            } else {
                tempBitmap = imageSaver.load();
                Log.d(TAG, "image loaded from internal storage: " + position);
            }
            informals.get(position).setLocalBitmap(tempBitmap);
            holder.ivSportIcon.setImageBitmap(tempBitmap);


        } else {
            Log.d(TAG, "image loaded from Internet: " + position);
            ImageRequest request = new ImageRequest(informals.get(position).img_link,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.ivSportIcon.setImageBitmap(bitmap);
                            imageSaver.save(bitmap);
                            informals.get(position).setLocalBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            //mImageView.setImageResource(R.drawable.image_load_error);
                        }
                    });
            requestQueue.add(request);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleDocLinkIntent = new Intent(Intent.ACTION_VIEW);
                googleDocLinkIntent.setData(Uri.parse(Constants.INFORMAL_DOC_LINK));
                context.startActivity(googleDocLinkIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return informals.size();
    }
    static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSportName;
        ImageView ivSportIcon;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            tvSportName = (TextView) itemView.findViewById(R.id.tvSportName);
            ivSportIcon = (ImageView) itemView.findViewById(R.id.ivSportsIcon);
            cardView = (CardView) itemView.findViewById(R.id.card_sport_item);
        }
    }
}
