package com.example.muvies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private String autor;
    @SerializedName("review")
    private String review;
    @SerializedName("type")
    private String type;

    public Review(String autor, String review, String type) {
        this.autor = autor;
        this.review = review;
        this.type = type;
    }

    public String getAutor() {
        return autor;
    }

    public String getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "autor='" + autor + '\'' +
                ", review='" + review + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
