package com.test.myapplication.data.dataManager;

import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;

import io.reactivex.Observable;

public interface DataManager {

    Observable<Forecast> getWeatherForecast(double latitude, double longitude);

    void saveForecastToSharedPrefs(Forecast forecast);

    void saveLocation(double latitude, double longitude, String name);

    Forecast getSavedForecast();

    Location getSavedLocation();
}
