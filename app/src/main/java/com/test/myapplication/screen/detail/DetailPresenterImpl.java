package com.test.myapplication.screen.detail;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.util.Log;

import com.test.myapplication.data.PreferencesManager;
import com.test.myapplication.data.dataManager.DataManager;
import com.test.myapplication.data.dataManager.DataManagerImpl;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.data.model.Forecast;

import java.util.ArrayList;
import java.util.List;


public class DetailPresenterImpl implements DetailPresenter {
    private static String TAG = "DetailPresenterImpl";

    private Forecast mForecast;
    private List<Data> mHourlyForecastList;

    private DetailView mDetailView;
    private DataManager mDataManager;

    public DetailPresenterImpl(DetailView detailView, NetworkInfo networkInfo, SharedPreferences sharedPreferences) {
        this.mDetailView = detailView;
        mDataManager = new DataManagerImpl(networkInfo, sharedPreferences);
        getHourlyForecastList();
    }

    @Override
    public void getHourlyForecastList() {
        getForecastFromSharedPrefs();
        mHourlyForecastList = new ArrayList<>();
        if (mForecast != null && mForecast.hourly != null) {
            mHourlyForecastList = mForecast.hourly.data;
        }

        mDetailView.displayHourlyForecast(mHourlyForecastList);
    }

    private void getForecastFromSharedPrefs() {
        mForecast = PreferencesManager.getSavedForecast();
        Log.d(TAG, "getForecastFromSharedPrefs: ");
    }
}
