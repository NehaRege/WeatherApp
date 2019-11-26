package com.test.myapplication.data.dataManager;

import com.test.myapplication.api.ApiClient;
import com.test.myapplication.api.ApiService;
import com.test.myapplication.data.PreferencesManager;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;
import com.test.myapplication.util.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManagerImpl implements DataManager {
    private static String TAG = "DataManagerImpl";

    public DataManagerImpl() {
    }

    @Override
    public Observable<Forecast> getWeatherForecast(double latitude, double longitude) {
        return getObservableForecast(latitude, longitude);
    }

    @Override
    public void saveForecastToSharedPrefs(Forecast forecast) {
        PreferencesManager.saveForecast(forecast);
    }

    @Override
    public void saveLocation(double latitude, double longitude, String name) {
        PreferencesManager.saveLocation(latitude, longitude, name);
    }

    @Override
    public Forecast getSavedForecast() {
        return PreferencesManager.getSavedForecast();
    }

    @Override
    public Location getSavedLocation() {
        return PreferencesManager.getSavedLocation();
    }

    private Observable<Forecast> getObservableForecast(double latitude, double longitude) {
        return ApiClient.getRetrofit()
                .create(ApiService.class)
                .getForecast(Constants.API_KEY, latitude, longitude, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
