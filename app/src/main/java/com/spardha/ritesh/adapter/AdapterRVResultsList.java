package com.spardha.ritesh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spardha.ritesh.R;
import com.spardha.ritesh.models.Result;

import java.util.ArrayList;

/**
 * Created by ritesh on 10/15/16.
 */

public class AdapterRVResultsList extends RecyclerView.Adapter<AdapterRVResultsList.ViewHolder> {


    private static final String TAG = "AdapterRVResultsList";
    private Context context;
    private ArrayList<Result> results;

    public AdapterRVResultsList(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public AdapterRVResultsList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_result_x_vs_y, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterRVResultsList.ViewHolder holder, int position) {

        Result result = results.get(position);
        holder.tvScore.setText(result.score);
        holder.tvVerdict.setText(result.verdict);
        holder.tvMatchGenre.setText(result.sport_desc);
        holder.tvTeam1.setText(result.team1);
        holder.tvTeam2.setText(result.team2);
        holder.tvTime.setText(result.date);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMatchGenre, tvTime, tvTeam1, tvTeam2, tvScore, tvVerdict;

        ViewHolder(View itemView) {
            super(itemView);
            tvMatchGenre = (TextView) itemView.findViewById(R.id.tvMatchGenre);
            tvVerdict = (TextView) itemView.findViewById(R.id.tvMatchVerdict);
            tvTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            tvTeam1 = (TextView) itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = (TextView) itemView.findViewById(R.id.tvTeam2);
            tvScore = (TextView) itemView.findViewById(R.id.tvMatchScore);
        }
    }

}
