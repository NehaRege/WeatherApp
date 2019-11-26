package com.test.myapplication.screen.main;

public interface MainPresenter {

    void getWeatherForecast(double latitude, double longitude);

    void onErrorViewClick();

    void onWeatherForecastViewClick();
}
