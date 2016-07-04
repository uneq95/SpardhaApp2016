package com.spardha.ritesh.adapter;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.spardha.ritesh.activity.SportsActivity;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.ImageLoaderHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/30/16.
 */
public class AdapterRVEventGrid extends RecyclerView.Adapter<AdapterRVEventGrid.ViewHolder> {

    Context context;
    RequestQueue requestQueue;

    ArrayList<SportEvent> availableSportsList;

    public AdapterRVEventGrid(Context context,ArrayList<SportEvent> availableSportsList) {
        this.context = context;
        this.availableSportsList=availableSportsList;
        requestQueue = AppSingleton.getInstance(context).getRequestQueue();

    }

    @Override
    public AdapterRVEventGrid.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_sports_grid_item_test, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvSportName.setText(availableSportsList.get(position).sport_name.toUpperCase());
        /*ImageLoader imageLoader =
                AppSingleton.getInstance(context).getImageLoader();*/

        Picasso.with(context).load(availableSportsList.get(position).header_url).centerCrop().into(holder.ivSportIcon);
        /*imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image, android.R.drawable
                        .ic_dialog_alert));*/
        /*ImageRequest request = new ImageRequest(availableSportsList.get(position).header_url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.ivSportIcon.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //mImageView.setImageResource(R.drawable.image_load_error);
                    }
                });
        requestQueue.add(request);*/
//        holder.ivSportIcon.setImageBitmap(
//                ImageLoaderHelper.decodeSampledBitmapFromResource(context.getResources(), resId[position], 150, 150));
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
            Intent intent = new Intent(context, SportsActivity.class);
            intent.putExtra("SPORT_NAME", sportName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
