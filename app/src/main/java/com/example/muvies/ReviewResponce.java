package com.example.muvies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponce {

    @SerializedName("docs")
    private List <Review> reviews;

    public ReviewResponce(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponce{" +
                "reviews=" + reviews +
                '}';
    }
}
