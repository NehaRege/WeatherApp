package com.test.myapplication.data.dataManager;

import android.net.NetworkInfo;

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


    public DataManagerImpl(NetworkInfo mNetworkInfo) {
        this.mNetworkInfo = mNetworkInfo;
    }

    @Override
    public Observable<Forecast> getWeatherForecast(double latitude, double longitude) {
        return getObservableForecast(latitude, longitude);
    }


    public Observable<Forecast> getObservableForecast(double latitude, double longitude) {
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
