package com.example.dulanjana.movieapplication.View;

import com.example.dulanjana.movieapplication.Model.Movie;

import java.util.List;

public interface UpcomingFragmentView {

    void OnSuccess(List<Movie> upComingMovies);

    void onError(Error error);
}
