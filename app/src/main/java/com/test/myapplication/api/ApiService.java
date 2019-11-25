package com.test.myapplication.api;

import com.test.myapplication.data.model.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
//    https://api.darksky.net/forecast/7d213bc69686b2c1ca60cc26f783fafb/37.8267,-122.4233

    @GET("forecast/{key}/{latitude},{longitude}")
    Observable<Forecast> getForecast(@Path("key") String key,
                                     @Path("latitude") double latitude,
                                     @Path("longitude") double longitude,
                                     @Query("exclude") String exclude);
}
