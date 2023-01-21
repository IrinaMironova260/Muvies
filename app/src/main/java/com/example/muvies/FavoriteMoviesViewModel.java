package com.example.muvies;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class FavoriteMoviesViewModel extends AndroidViewModel {
    private final MovieDao movieDao;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieDao.getAllFavoriteMovies();
    }

}
