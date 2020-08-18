package com.example.dulanjana.movieapplication.View;

import com.example.dulanjana.movieapplication.Model.Movie;

import java.util.List;

public interface PopularFragmentview {

    void OnSuccess(List<Movie> PopularMovies);

    void onError(Error error);
}
