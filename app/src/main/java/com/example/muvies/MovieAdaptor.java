package com.example.muvies;



import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.MovieViewHolder> {

    private List<Movie> movies= new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private OnMovieClickLisner onMovieClickLisner;


    public void setOnMovieClickLisner(OnMovieClickLisner onMovieClickLisner) {
        this.onMovieClickLisner = onMovieClickLisner;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d("MovieAdaptor", "onBindViewHolder: " + position);
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);

        double rating = movie.getRating().kinoSearch();
        int backgroundID;
        if (rating > 7){
            backgroundID = R.drawable.circle_green;
        }else if(rating > 5){
            backgroundID = R.drawable.circle_orange;
        } else {
            backgroundID = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(),backgroundID);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(String.valueOf(rating));

        if (position >= movies.size() - 6 && onReachEndListener !=null){
            onReachEndListener.onReachEnd();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onMovieClickLisner != null){
                    onMovieClickLisner.onMoveClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {  //возвращает кол-во элементов в коллекции
        return movies.size();
    }

    interface OnReachEndListener{
        void onReachEnd();
    }

    interface OnMovieClickLisner{
        void onMoveClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{
       private final ImageView imageViewPoster;
       private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }
    }

}