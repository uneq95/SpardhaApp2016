package com.spardha.ritesh.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.models.ContactListItem;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.ImageSaver;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 22-Jul-15.
 */
public class ContactAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<ContactListItem> contacts;
    private String TAG = "ContactAdapter";
    private RequestQueue requestQueue;

    public ContactAdapter(Context context, ArrayList<ContactListItem> objects) {
        this.context = context;
        contacts = objects;
        requestQueue = AppSingleton.getInstance(context).getRequestQueue();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.indexOf(contacts.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        final ContactListItem listItem = contacts.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        if (contacts.get(position).isHeader()) {
            //header =true
            convertView = inflater.inflate(R.layout.contact_headers, parent, false);
            holder.tvHeaderText = (TextView) convertView.findViewById(R.id.contact_header);
            holder.tvHeaderText.setText(listItem.getContactHeader());
            convertView.setClickable(false);
        } else {
            //for contacts
            convertView = inflater.inflate(R.layout.single_contact_layout2, parent, false);
            holder.tvContactName = (TextView) convertView.findViewById(R.id.tvTeamMemberName);
            holder.tvDesignation = (TextView) convertView.findViewById(R.id.tvTeamMemberEmail);
            holder.ivContactPic = (ImageView) convertView.findViewById(R.id.ivTeamMemberPic);
            holder.ivCallImage = (ImageView) convertView.findViewById(R.id.iv_call_image);
            holder.tvContactName.setText(listItem.getContactName());
            holder.tvDesignation.setText(listItem.getEmail());
            if (listItem.getPhotoResId() > 0) {
                holder.ivContactPic.setImageResource(listItem.getPhotoResId());
            } else {
                holder.ivContactPic.setImageResource(R.drawable.ic_no_pic);

            }
            if (listItem.getPicLink() != null) {
                String fileName = listItem.getContactName().toLowerCase().replace(" ", "_").concat(".jpg");
                final ImageSaver imageSaver = new ImageSaver(context).setFileName(fileName);
                boolean isLocalImageAvailable = imageSaver.doesFileExist();

                if (isLocalImageAvailable) {


                    Bitmap tempBitmap;
                    if (listItem.isHeaderBitmapAvailable()) {
                        tempBitmap = listItem.getLocalBitmap();
                        Log.d(TAG, "image loaded from local bitmap: " + position);
                    } else {
                        tempBitmap = imageSaver.load();
                        Log.d(TAG, "image loaded from internal storage: " + position);
                    }
                    listItem.setLocalBitmap(tempBitmap);
                    holder.ivContactPic.setImageBitmap(tempBitmap);


                } else {
                    Log.d(TAG, "image loaded from Internet: " + position);
                    ImageRequest request = new ImageRequest(listItem.getPicLink(),
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    holder.ivContactPic.setImageBitmap(bitmap);
                                    imageSaver.save(bitmap);
                                    listItem.setLocalBitmap(bitmap);
                                }
                            }, 0, 0, null,
                            new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    Bitmap noPicBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_no_pic);
                                    holder.ivContactPic.setImageBitmap(noPicBmp);
                                }
                            });
                    requestQueue.add(request);
                }

            }


            if (listItem.getPhoneNumber() == null) {
                holder.ivCallImage.setImageResource(android.R.color.transparent);
            }
        }
        return convertView;
    }


    private class ViewHolder {
        //for contacts
        TextView tvContactName, tvDesignation;
        ImageView ivContactPic, ivCallImage;

        //for header
        TextView tvHeaderText;

    }
}
