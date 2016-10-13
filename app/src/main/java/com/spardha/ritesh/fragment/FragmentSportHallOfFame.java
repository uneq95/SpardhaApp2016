package com.spardha.ritesh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spardha.ritesh.R;

/**
 * Created by ritesh on 6/3/16.
 */
public class FragmentSportHallOfFame extends Fragment {

    public static final String TAG = "hall_of_fame";
    public static String winnerName = "winner", runnerUpName = "runnerup";
    public TextView tvWinner, tvRunnerUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superView = inflater.inflate(R.layout.fragment_sport_hall_of_fame, container, false);
        tvWinner = (TextView) superView.findViewById(R.id.tvWinnerName);
        tvRunnerUp = (TextView) superView.findViewById(R.id.tvRunnerUpName);
        tvRunnerUp.setText(runnerUpName);
        tvWinner.setText(winnerName);
        return superView;
    }

    public void updateViews(String winner, String runnerup) {
        winnerName = winner;
        runnerUpName = runnerup;
//        if (tvWinner != null)
//            tvWinner.setText(winner);
//        if (tvRunnerUp != null)
//            tvRunnerUp.setText(runnerup);

    }

}
