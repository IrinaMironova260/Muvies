package com.example.muvies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";
    private static final String TAG = "MovieDetailActivity";

    private ImageView imageViewPoster;
    private ImageView imageViewStar;

    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDesc;

    private MovieDetailViewModel movieDetailViewModel;

    private RecyclerView recyclerviewTrailers;
    private RecyclerView recyclerviewReviews;

    private TrailersAdapter trailersAdapter;
    private ReviewsAdaptor reviewsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
        trailersAdapter = new TrailersAdapter();
        reviewsAdaptor = new ReviewsAdaptor();
        recyclerviewTrailers.setAdapter(trailersAdapter);
        recyclerviewReviews.setAdapter(reviewsAdaptor);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDesc.setText(movie.getDescription());

        movieDetailViewModel.loadTrailers(movie.getId());
        movieDetailViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        trailersAdapter.setOnTrailerClickLisner(new TrailersAdapter.OnTrailerClickLisner() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });
        movieDetailViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewsAdaptor.setReviews(reviewList);
            }
        });
        movieDetailViewModel.loadReviews(movie.getId());

        Drawable startOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,android.R.drawable.star_big_off);
        Drawable startOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,android.R.drawable.star_big_on);
        movieDetailViewModel.getFavoriteMovie(movie.getId()).observe(this, new Observer<Movie>()
        {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb == null){
                    imageViewStar.setImageDrawable(startOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.insertMovie(movie);
                        }
                    });
                }else {
                    imageViewStar.setImageDrawable(startOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.removeMovie(movie.getId());
                        }
                    });
                }
            }
        });

      }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDesc = findViewById(R.id.textViewDesc);
        recyclerviewTrailers = findViewById(R.id.recyclerviewTrailers);
        recyclerviewReviews = findViewById(R.id.recyclerviewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}