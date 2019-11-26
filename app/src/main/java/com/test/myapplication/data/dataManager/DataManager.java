package com.test.myapplication.data.dataManager;

import com.test.myapplication.data.model.Forecast;

import io.reactivex.Observable;

public interface DataManager {

    Observable<Forecast> getWeatherForecast(double latitude, double longitude);

    void saveWeatherForecastToSharedPrefs(Forecast forecast);

    Forecast getWeatherForecastFromSharedPrefs();

}
