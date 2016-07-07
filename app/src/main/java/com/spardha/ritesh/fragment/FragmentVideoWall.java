package com.spardha.ritesh.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.spardha.ritesh.R;
import com.spardha.ritesh.adapter.AdapterRVYoutube;
import com.spardha.ritesh.models.YouTubeVideo;
import com.spardha.ritesh.utils.AppSingleton;
import com.spardha.ritesh.utils.Constants;
import com.spardha.ritesh.utils.ItemOffsetDecoration;
import com.spardha.ritesh.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ritesh on 7/5/16.
 */
public class FragmentVideoWall extends Fragment {

    View superView;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView videoRecyclerView;
    ArrayList<YouTubeVideo> youTubeVideos;
    Context fragmentContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.fragment_video_wall, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) superView.findViewById(R.id.swipe_refresh_layout);
//        int[] colors = {R.color.blue, R.color.green, R.color.orange};
//        swipeRefreshLayout.setColorSchemeColors(colors);
        fragmentContext = getContext();
        videoRecyclerView = (RecyclerView) superView.findViewById(R.id.recycler_view);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //videoRecyclerView.setLayoutManager(linearLayoutManager);
        videoRecyclerView.setLayoutManager(new GridLayoutManager(fragmentContext, 1));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(fragmentContext, R.dimen.recycler_view_item_spacing);
        videoRecyclerView.addItemDecoration(itemDecoration);

        //todo make a universal internet availablity checking function
        if (isInternetAvailable()) {
            //todo load list using volley then display in recycler view
            loadVideoList();
        } else {
            //todo use snackbar to show the message
            Toast.makeText(fragmentContext, "Please connect to the Internet!!", Toast.LENGTH_SHORT).show();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //todo load list using volley then display in recycler view
                loadVideoList();

            }
        });

        return superView;

    }

    boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) fragmentContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null);

    }

    private void loadVideoList() {
        youTubeVideos = new ArrayList<>();
        String url = Constants.getSpardhaChannelURL();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    youTubeVideos = JSONParser.getYoutubeVideoList(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AdapterRVYoutube adapter = new AdapterRVYoutube(youTubeVideos, getContext());
                videoRecyclerView.setAdapter(adapter);
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppSingleton.getInstance(fragmentContext).addToRequestQueue(jsonObjectRequest);
    }
}
