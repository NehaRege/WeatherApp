package com.test.myapplication.api;

import com.test.myapplication.data.model.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("forecast/{key}/{latitude},{longitude}")
    Observable<Forecast> getForecast(
            @Path("key") String key,
            @Path("latitude") double latitude,
            @Path("longitude") double longitude,
            @Query("exclude") String exclude);
}
