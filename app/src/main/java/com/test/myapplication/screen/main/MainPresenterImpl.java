package com.test.myapplication.screen.main;

import android.location.Address;
import android.location.Geocoder;

import com.test.myapplication.data.dataManager.DataManager;
import com.test.myapplication.data.dataManager.DataManagerImpl;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";

    private MainView mMainView;
    private DataManager mDataManager;
    private Geocoder mGeocoder;

    private Observable<Forecast> mObservable;
    private double mMinTemp;
    private double mMaxTemp;

    public MainPresenterImpl(MainView mainView, Geocoder geocoder) {
        mMainView = mainView;
        mGeocoder = geocoder;
        mDataManager = new DataManagerImpl();
    }

    @Override
    public void getWeatherForecast(double latitude, double longitude) {
        mMainView.isLoading(true);

        mObservable = mDataManager.getWeatherForecast(latitude, longitude);

        mObservable.subscribe(new Observer<Forecast>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Forecast forecast) {
                mDataManager.saveForecastToSharedPrefs(forecast);

                getMinMaxTemp(forecast);

                mMainView.hideErrorView();
                mMainView.isLoading(false);
                mMainView.showForecastView();

                getLocationAddress(latitude, longitude);
                mMainView.displayForecast(forecast, mMinTemp, mMaxTemp);
            }

            @Override
            public void onError(Throwable e) {
                mMainView.isLoading(false);
                mMainView.hideForecastView();
                mMainView.showErrorView();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void onErrorViewClick() {
        mMainView.hideErrorView();
        mMainView.isLoading(true);
        Location location = mDataManager.getSavedLocation();

        if (location == null) {
            mMainView.getCurrentLocation();
        } else {
            getWeatherForecast(location.latitude, location.longitude);
        }
    }

    @Override
    public void onWeatherForecastViewClick() {
        mMainView.goToForecastDetailScreen();
    }

    private void getLocationAddress(double latitude, double longitude) {
        try {
            List<Address> addresses = mGeocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);

                if (fetchedAddress != null && fetchedAddress.getLocality() != null && !fetchedAddress.getLocality().isEmpty()) {
                    mDataManager.saveLocation(latitude, longitude, fetchedAddress.getLocality());
                    mMainView.displayLocation(fetchedAddress.getLocality());
                }
            } else {
                mMainView.displayLocation(mMainView.getSearchingLocationString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            mMainView.displayLocation(mMainView.getSearchingLocationString());
        }
    }

    @Override
    public void getMinMaxTemp(Forecast forecast) {
        List<Double> intList = new ArrayList<>();
        List<Data> dataList = (forecast.hourly != null && forecast.hourly.data != null) ? forecast.hourly.data : new ArrayList<>();

        for (Data data : dataList) {
            intList.add(data.temperature);
        }

        mMaxTemp = Collections.max(intList);
        mMinTemp = Collections.min(intList);
    }

}
