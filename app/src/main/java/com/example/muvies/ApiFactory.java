package com.example.muvies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/*
/Реализация айпи сервиса
 */
public class ApiFactory {
    private static final String BASE_URL = "https://api.kinopoisk.dev/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public  static final ApiService apiServise = retrofit.create(ApiService.class);


}
