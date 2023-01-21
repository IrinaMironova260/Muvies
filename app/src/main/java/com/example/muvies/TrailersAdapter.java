package com.example.muvies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private OnTrailerClickLisner onTrailerClickLisner;

    public void setOnTrailerClickLisner(OnTrailerClickLisner onTrailerClickLisner) {
        this.onTrailerClickLisner = onTrailerClickLisner;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treiler_item, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.textViewTrailer.setText(trailer.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onTrailerClickLisner != null){
                    onTrailerClickLisner.onTrailerClick(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    interface OnTrailerClickLisner{
        void onTrailerClick(Trailer trailer);
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTrailer;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailer = itemView.findViewById(R.id.textViewTrailer);
        }
    }

}
