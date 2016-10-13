package com.spardha.ritesh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.models.Fixture;

import java.util.ArrayList;

/**
 * Created by ritesh on 10/12/16.
 */

public class AdapterRVFixturesList extends RecyclerView.Adapter<AdapterRVFixturesList.ViewHolder> {
    private static final String TAG = "AdapterRVFixturesList";
    private Context context;
    private ArrayList<Fixture> fixtures;

    public AdapterRVFixturesList(Context context, ArrayList<Fixture> fixtures) {
        this.context = context;
        this.fixtures = fixtures;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_x_vs_y, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fixture fixture = fixtures.get(position);
        holder.tvTeam1.setText(fixture.team1);
        holder.tvTeam2.setText(fixture.team2);
        holder.tvVenue.setText(fixture.venue);
        holder.tvMatchGenre.setText(fixture.sport);
        holder.tvTime.setText(fixture.time);
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMatchGenre, tvVenue, tvTime, tvTeam1, tvTeam2, tvScore, tvVerdict;

        ViewHolder(View itemView) {
            super(itemView);
            tvMatchGenre = (TextView) itemView.findViewById(R.id.tvMatchGenre);
            tvVenue = (TextView) itemView.findViewById(R.id.tvVenue);
            tvTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            tvTeam1 = (TextView) itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = (TextView) itemView.findViewById(R.id.tvTeam2);
        }
    }
}
