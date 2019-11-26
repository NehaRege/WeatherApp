package com.test.myapplication.data.dataManager;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.test.myapplication.api.ApiClient;
import com.test.myapplication.api.ApiService;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.util.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManagerImpl implements DataManager {
    private static String TAG = "DataManagerImpl";

    private NetworkInfo mNetworkInfo;
    private SharedPreferences mSharedPreferences;


    public DataManagerImpl(NetworkInfo mNetworkInfo, SharedPreferences sharedPreferences) {
        this.mNetworkInfo = mNetworkInfo;
        this.mSharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<Forecast> getWeatherForecast(double latitude, double longitude) {
        Log.d(TAG, "getWeatherForecast: ");
        return getObservableForecast(latitude, longitude);
    }

    @Override
    public void saveWeatherForecastToSharedPrefs(Forecast forecast) {
        Log.d(TAG, "saveWeatherForecastToSharedPrefs: ");
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(forecast);
        prefsEditor.putString(Constants.SHARED_PREFS_FORECAST_KEY, json);
        prefsEditor.apply();
    }

    @Override
    public Forecast getWeatherForecastFromSharedPrefs() {
        Log.d(TAG, "getWeatherForecastFromSharedPrefs: ");
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(Constants.SHARED_PREFS_FORECAST_KEY, "");
        return gson.fromJson(json, Forecast.class);
    }


    public Observable<Forecast> getObservableForecast(double latitude, double longitude) {
        Log.d(TAG, "getObservableForecast: ");
        return ApiClient.getRetrofit()
                .create(ApiService.class)
                //TODO: Add exclude params
                .getForecast(Constants.API_KEY, latitude, longitude, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Boolean isNetworkAvailable() {
        return mNetworkInfo != null && mNetworkInfo.isConnectedOrConnecting();
    }

}
