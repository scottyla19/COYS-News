package com.example.android.coys;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


import static android.R.attr.key;

/**
 * Created by swlaforest on 4/24/2017.
 */

public interface GuardianService {
    @GET("search?")
    Call<Article> listArticles(@Query(value = "q", encoded = true) String query,
                                     @Query("to-date") String date,
                                     @Query("order-by") String order,
                                     @Query("api-key") String key);




}