package com.spardha.ritesh.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.activity.ActivitySports;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.ImageSaver;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ritesh on 6/30/16.
 */
public class AdapterRVEventList extends RecyclerView.Adapter<AdapterRVEventList.ViewHolder> {

    Context context;
    RequestQueue requestQueue;
    private static final String TAG ="AdapterRVEventList";
    ArrayList<SportEvent> availableSportsList;

    public AdapterRVEventList(Context context, ArrayList<SportEvent> availableSportsList) {
        this.context = context;
        this.availableSportsList = availableSportsList;
        requestQueue = AppSingleton.getInstance(context).getRequestQueue();

    }

    @Override
    public AdapterRVEventList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_sports_grid_item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvSportName.setText(availableSportsList.get(position).sport_name.toUpperCase());
        /*final ImageLoader imageLoader =
                AppSingleton.getInstance(context).getImageLoader();

        imageLoader.get(availableSportsList.get(position).header_url, ImageLoader.getImageListener(holder.ivSportIcon,
                R.drawable.header_athletics, android.R.drawable
                        .ic_dialog_alert));*/
        String fileName = availableSportsList.get(position).sport_name.replace(" ","_").concat(".jpg");
        final ImageSaver imageSaver = new ImageSaver(context).setFileName(fileName);
        boolean isLocalImageAvailable=imageSaver.doesFileExist();
        if(isLocalImageAvailable){
            holder.ivSportIcon.setImageBitmap(imageSaver.load());
            Log.d(TAG,"image loaded from memory: "+position);
            //Log.d(TAG,);
        }else{
            Log.d(TAG,"image loaded from Internet: "+position);
            ImageRequest request = new ImageRequest(availableSportsList.get(position).header_url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.ivSportIcon.setImageBitmap(bitmap);
                            imageSaver.save(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            //mImageView.setImageResource(R.drawable.image_load_error);
                        }
                    });
            requestQueue.add(request);
        }

        holder.cardView.setOnClickListener(new EventOnClickListener(availableSportsList.get(position).sport_name, context));

    }

    @Override
    public int getItemCount() {
        return availableSportsList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSportName;
        ImageView ivSportIcon;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSportName = (TextView) itemView.findViewById(R.id.tvSportName);
            ivSportIcon = (ImageView) itemView.findViewById(R.id.ivSportsIcon);
            cardView = (CardView) itemView.findViewById(R.id.card_sport_item);
        }
    }

    public class EventOnClickListener implements View.OnClickListener {

        String sportName;
        Context context;

        public EventOnClickListener(String sportName, Context context) {
            this.context = context;
            this.sportName = sportName.toLowerCase();
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ActivitySports.class);
            intent.putExtra(Constants.EXTRA_SPORT_NAME, sportName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
