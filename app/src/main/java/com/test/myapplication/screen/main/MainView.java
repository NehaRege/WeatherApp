package com.test.myapplication.screen.main;

import com.test.myapplication.data.model.Forecast;

public interface MainView {

    void displayForecast(Forecast forecast);

    void isLoading(boolean isLoading);

    void showErrorView();

    void hideErrorView();

    void goToForecastDetails();


}
