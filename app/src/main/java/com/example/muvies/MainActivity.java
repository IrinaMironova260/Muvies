package com.example.muvies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerViewMovies;
    private MovieAdaptor movieAdaptor;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarLoading = findViewById(R.id. progressBarLoading);
        recyclerViewMovies = findViewById(R.id.recyclerviewMovies);
        movieAdaptor = new MovieAdaptor();
        recyclerViewMovies.setAdapter(movieAdaptor);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdaptor.setMovies(movies);
            }
        });

        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading){
                    progressBarLoading.setVisibility(View.VISIBLE);
                }else {
                    progressBarLoading.setVisibility(View.INVISIBLE);
                }
            }
        });
        movieAdaptor.setOnReachEndListener(new MovieAdaptor.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                mainViewModel.loadMovies();
            }
        });
        movieAdaptor.setOnMovieClickLisner(new MovieAdaptor.OnMovieClickLisner() {
            @Override
            public void onMoveClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemFavorite){
            Intent intent = FavoriteMoviesActivity.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}