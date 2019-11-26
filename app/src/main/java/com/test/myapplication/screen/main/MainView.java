package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;

public interface MainView {

    void getWeatherForecast(double latitude, double longitude);

    void displayForecast(Forecast forecast);

    void displayLocation(String location);

    void hideForecastView();

    void isLoading(boolean isLoading);

    void showErrorView();

    void hideErrorView();

    void goToForecastDetailScreen();

    void getCurrentLocation();

    boolean checkLocationPermissions();

    void requestLocationPermission();

    void openSettingsScreen();

    String getSearchingLocationString();


}
