package com.example.muvies;

/*
/Интерфейс айпи сервиса
 */
import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?token=SSCE0DQ-QWG41J5-HJ6FP2F-S08S1T2&field=rating.kp&search=4-10&sortField=votes.kp&sortType=-1&limit=20")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=SSCE0DQ-QWG41J5-HJ6FP2F-S08S1T2&field=id")
    Single<TrailerResponse> loadTrailers(@Query("search") int id);

    @GET("review?token=SSCE0DQ-QWG41J5-HJ6FP2F-S08S1T2&field=movieId")
    Single<ReviewResponce> loadReviews(@Query("search") int id);

}
