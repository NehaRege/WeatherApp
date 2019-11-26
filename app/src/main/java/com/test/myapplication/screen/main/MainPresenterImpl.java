package com.test.myapplication.screen.main;

import com.test.myapplication.data.dataManager.DataManager;

public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";

    private MainView mMainView;
    private DataManager mDataManager;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void getWeatherForecast() {

    }

    @Override
    public void onErrorViewClick() {

    }

    @Override
    public void onWeatherForecastViewClick() {
        mMainView.showForecastDetailScreen();
    }
}
