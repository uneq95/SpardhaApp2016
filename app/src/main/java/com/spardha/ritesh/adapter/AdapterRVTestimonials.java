package com.spardha.ritesh.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.models.SportEvent;
import com.spardha.ritesh.models.Testimonial;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.ImageSaver;
import com.spardha.ritesh.views.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by ritesh on 7/11/16.
 */
public class AdapterRVTestimonials extends RecyclerView.Adapter<AdapterRVTestimonials.ViewHolder> {

    Context context;
    ArrayList<Testimonial> testimonials;
    RequestQueue requestQueue;
    private final String TAG="AdapterRVTestimonials";

    public AdapterRVTestimonials(Context context, ArrayList<Testimonial> testimonials) {
        this.context = context;
        this.testimonials = testimonials;
        requestQueue = AppSingleton.getInstance(context).getRequestQueue();
    }

    @Override
    public AdapterRVTestimonials.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_testimonial_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterRVTestimonials.ViewHolder holder, final int position) {

        holder.tvGuestJob.setText(testimonials.get(position).guest_job);
        holder.tvGuestName.setText(testimonials.get(position).guest_name);
        holder.tvWords.setText(testimonials.get(position).words);

        String fileName = testimonials.get(position).guest_name.replace(" ", "_").concat(".jpg");
        final ImageSaver imageSaver = new ImageSaver(context).setFileName(fileName);
        boolean isLocalImageAvailable = imageSaver.doesFileExist();

        if (isLocalImageAvailable) {

            Testimonial testimonialObject = testimonials.get(position);
            Bitmap tempBitmap;
            if (testimonialObject.isHeaderBitmapAvailable()) {
                tempBitmap = testimonialObject.getLocalBitmap();
                Log.d(TAG, "image loaded from local bitmap: " + position);
            } else {
                tempBitmap = imageSaver.load();
                Log.d(TAG, "image loaded from internal storage: " + position);
            }
            testimonials.get(position).setLocalBitmap(tempBitmap);
            holder.roundedImageView.setImageBitmap(tempBitmap);


        } else {
            Log.d(TAG, "image loaded from Internet: " + position);
            ImageRequest request = new ImageRequest(testimonials.get(position).image_url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.roundedImageView.setImageBitmap(bitmap);
                            imageSaver.save(bitmap);
                            testimonials.get(position).setLocalBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            //mImageView.setImageResource(R.drawable.image_load_error);
                        }
                    });
            requestQueue.add(request);
        }


    }

    @Override
    public int getItemCount() {
        return testimonials.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView tvGuestName,tvGuestJob,tvWords;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGuestName= (TextView) itemView.findViewById(R.id.tvTestiCeleb);
            tvGuestJob= (TextView) itemView.findViewById(R.id.tvTestiCelebDesg);
            tvWords= (TextView) itemView.findViewById(R.id.tvTestDesc);
            roundedImageView=(RoundedImageView)itemView.findViewById(R.id.testi_img);
        }
    }
}
