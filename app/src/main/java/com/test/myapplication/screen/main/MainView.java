package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;

public interface MainView {

    void displayForecast(Forecast forecast);

    void displayDateAndTime();

    void displayLocation();

    void isLoading(boolean isLoading);

    void showErrorView();

    void hideErrorView();

    void showForecastDetailScreen();


}
