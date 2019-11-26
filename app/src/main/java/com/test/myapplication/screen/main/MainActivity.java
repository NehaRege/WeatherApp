package com.test.myapplication.screen.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.myapplication.R;
import com.test.myapplication.data.model.Forecast;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void displayForecast(Forecast forecast) {

    }

    @Override
    public void isLoading(boolean isLoading) {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void goToForecastDetails() {

    }
}
