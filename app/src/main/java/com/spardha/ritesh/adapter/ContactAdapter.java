package com.spardha.ritesh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.models.ContactListItem;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 22-Jul-15.
 */
public class ContactAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<ContactListItem> contacts;

    public ContactAdapter(Context context, ArrayList<ContactListItem> objects) {
        this.context = context;
        contacts = objects;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
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

                Ion.with(context)
                        .load(listItem.getPicLink())
                        .withBitmap()
                        .error(R.drawable.ic_no_pic)
                        .intoImageView(holder.ivContactPic);
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
