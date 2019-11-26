package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;

public interface MainPresenter {

    void getWeatherForecast(double latitude, double longitude);

    void onErrorViewClick();

    void onWeatherForecastViewClick();

//    void findAddress(double latitude, double longitude);

    void saveLocation(double latitude, double longitude, String name);

    void getMinMaxTemp(Forecast forecast);
}
