package com.test.myapplication.screen.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.test.myapplication.R;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private DetailPresenter mDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpMVP();

    }

    @Override
    public void displayHourlyForecast() {

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
