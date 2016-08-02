package com.spardha.ritesh.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.models.Contact;
import com.spardha.ritesh.utils.Utilities;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/4/16.
 */
public class AdapterSportContacts extends RecyclerView.Adapter<AdapterSportContacts.ViewHolder> {

    ArrayList<Contact> contactsData;
    Context context;
    String TAG="AdapterSportContacts";
    public AdapterSportContacts(ArrayList<Contact> contactsData , Context context){
        this.contactsData=contactsData;
        this.context=context;

    }

    @Override
    public AdapterSportContacts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvName.setText(contactsData.get(position).name);
        holder.cardView.setOnClickListener(new ContactOnClickListener(contactsData.get(position),context));

    }

    @Override
    public int getItemCount() {
        return contactsData.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CardView cardView;
        public ViewHolder(View itemView){
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvcontact);
            cardView=(CardView)itemView.findViewById(R.id.card_view_contact1);
        }
    }

    private class ContactOnClickListener implements View.OnClickListener{

        Contact contact;
        Context context;
        public ContactOnClickListener(Contact contact,Context context){
            this.contact=contact;
            this.context=context;
        }
        @Override
        public void onClick(View v) {
            Log.i(TAG,contact.phone.trim());
            Utilities.makeCall(context,contact.phone.trim());
        }
    }
}
