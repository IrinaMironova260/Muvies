package com.example.muvies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private double kinoSearch;

    public Rating(double kinoSearch) {
        this.kinoSearch = kinoSearch;
    }

    public double kinoSearch() {
        return kinoSearch;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kinoSearch='" + kinoSearch + '\'' +
                '}';
    }
}
