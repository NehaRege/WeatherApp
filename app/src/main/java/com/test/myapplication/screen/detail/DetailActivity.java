package com.test.myapplication.screen.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.test.myapplication.R;
import com.test.myapplication.data.model.Data;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailView {
    private static String TAG = "DetailActivity";

    private DetailPresenter mDetailPresenter;
    private List<Data> mHourlyForecastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpMVP();
    }

    @Override
    public void displayHourlyForecast(List<Data> dataList) {
        mHourlyForecastList = dataList;
        Log.d(TAG, "displayHourlyForecast: list size -----------> " + dataList.size());

    }

    private void setUpMVP() {
        mDetailPresenter = new DetailPresenterImpl(this, getNetworkInfo(), getSharedPreferences());
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
    }

    private SharedPreferences getSharedPreferences() {
        return getPreferences(MODE_PRIVATE);
    }

}
