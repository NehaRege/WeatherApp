package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;

public interface MainView {
    void getWeatherForecast(double latitude, double longitude);

    void displayForecast(Forecast forecast, double minTemp, double MaxTemp);

    void displayLocation(String location);

    void showForecastView();

    void hideForecastView();

    void isLoading(boolean isLoading);

    void showErrorView();

    void hideErrorView();

    void goToForecastDetailScreen();

    String getSearchingLocationString();

    void getCurrentLocation();
}
