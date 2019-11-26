package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;

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

    boolean checkLocationPermissions();

    void requestLocationPermission();

    void openSettingsScreen();

    String getSearchingLocationString();


}
