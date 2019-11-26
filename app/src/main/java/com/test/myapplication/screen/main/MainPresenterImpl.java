package com.test.myapplication.screen.main;

import android.content.SharedPreferences;
import android.net.NetworkInfo;

import com.test.myapplication.data.dataManager.DataManager;
import com.test.myapplication.data.dataManager.DataManagerImpl;
import com.test.myapplication.data.model.Forecast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";

    private MainView mMainView;
    private DataManager mDataManager;

    private Observable<Forecast> mObservable;

    public MainPresenterImpl(MainView mMainView, NetworkInfo networkInfo, SharedPreferences sharedPreferences) {
        this.mMainView = mMainView;
        mDataManager = new DataManagerImpl(networkInfo, sharedPreferences);
    }

    @Override
    public void getWeatherForecast(double latitude, double longitude) {
        mObservable = mDataManager.getWeatherForecast(latitude, longitude);

        mObservable.subscribe(new Observer<Forecast>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Forecast forecast) {
                //TODO: create a separate shared prefs manager
                mDataManager.saveWeatherForecastToSharedPrefs(forecast);

                mMainView.hideErrorView();
                mMainView.isLoading(false);
                mMainView.displayForecast(forecast);
            }

            @Override
            public void onError(Throwable e) {
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
        getWeatherForecast(MainActivity.latitude, MainActivity.longitude);
    }

    @Override
    public void onWeatherForecastViewClick() {
        mMainView.showForecastDetailScreen();
    }
}
