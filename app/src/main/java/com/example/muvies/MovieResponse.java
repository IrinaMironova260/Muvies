package com.example.muvies;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("docs")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieResponse{" + "movies =" + movies + '}';
    }
}
