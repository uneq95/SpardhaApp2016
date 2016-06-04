package com.spardha.ritesh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spardha.ritesh.models.Contact;

import java.util.ArrayList;

/**
 * Created by ritesh on 6/4/16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<Contact> contactsData;
    Context context;

    public ContactAdapter(ArrayList<Contact> contactsData ,Context context){
        this.contactsData=contactsData;
        this.context=context;

    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPhone;

        public ViewHolder(View itemView){
            super(itemView);

        }
    }
}
