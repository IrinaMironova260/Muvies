package com.example.muvies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdaptor extends RecyclerView.Adapter<ReviewsAdaptor.ReviewViewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEUTRAL = "Нейтральный";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAutor.setText(review.getAutor());
        holder.textViewReview.setText(review.getReview());
        String type = review.getType();
        int colorResId = android.R.color.holo_red_light;
        switch (type){
            case TYPE_POSITIVE: colorResId = android.R.color.holo_green_light;
            break;
            case TYPE_NEUTRAL: colorResId = android.R.color.holo_orange_light;
            break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(),colorResId);
        holder.linearLayoutConteiner.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout linearLayoutConteiner;
        private final TextView textViewAutor;
        private final TextView textViewReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutConteiner = itemView.findViewById(R.id.linearLayoutConteiner);
            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewReview = itemView.findViewById(R.id.textViewReview);
        }
    }
}
