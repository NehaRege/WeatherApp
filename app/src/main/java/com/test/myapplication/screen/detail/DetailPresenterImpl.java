package com.test.myapplication.screen.detail;

import com.test.myapplication.data.dataManager.DataManager;
import com.test.myapplication.data.dataManager.DataManagerImpl;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.data.model.Forecast;

import java.util.ArrayList;
import java.util.List;


public class DetailPresenterImpl implements DetailPresenter {
    private static String TAG = "DetailPresenterImpl";

    private List<Data> mHourlyForecastList;

    private DetailView mDetailView;
    private DataManager mDataManager;

    public DetailPresenterImpl(DetailView detailView) {
        this.mDetailView = detailView;
        mDataManager = new DataManagerImpl();
    }

    @Override
    public void getHourlyForecastList() {
        Forecast forecast = mDataManager.getSavedForecast();

        mHourlyForecastList = new ArrayList<>();
        if (forecast != null && forecast.currently != null) {
            mHourlyForecastList = forecast.hourly.data;
        }

        mDetailView.displayHourlyForecast(mHourlyForecastList);
    }
}
