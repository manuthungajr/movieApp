package com.example.dulanjana.movieapplication.Presenter;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dulanjana.movieapplication.Model.Movie;
import com.example.dulanjana.movieapplication.View.PopularFragmentview;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PopularPresenterImpl implements PopularPresenter {

    PopularFragmentview popularFragmentview;
    Context context;


    public PopularPresenterImpl(PopularFragmentview popularFragmentview, Context context) {
        this.popularFragmentview = popularFragmentview;
        this.context = context;
    }


    @Override
    public void fetchPopularMovies() {

        Log.d(TAG, "ONSUCCESS1");

        String mJSONURLString = "https://api.themoviedb.org/3/movie/popular?api_key=21cf9e58fa9fb18d1769658101c2fa34&page=1";

        StringRequest fetchMovies = new StringRequest(Request.Method.GET, mJSONURLString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response Check :" + response);
                        try {
                            JSONObject responsejson = new JSONObject(response);
                            JSONArray jsonArray = responsejson.getJSONArray("results");
                            final List<Movie> movieList = new ArrayList<>();
                            for (int i = 0; i <= jsonArray.length()-1; i++) {
                                JSONObject movie = jsonArray.getJSONObject(i);
                                movieList.add(new Movie(movie.getString("title"),movie.getString("poster_path")));
                            }



                            popularFragmentview.OnSuccess(movieList);
                            int i =  2 +5;

                            Log.d(TAG, "ONSUCCESS10");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());

            }
        });
        if(context!=null) {
            RequestQueue queue = Volley.newRequestQueue(this.context);
            queue.add(fetchMovies);
        }

    }
}
