package com.test.myapplication.screen.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.R;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.screen.WeatherAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {
    private static String TAG = "DetailActivity";

    @BindView(R.id.mainToolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private WeatherAdapter mWeatherAdapter;

    private DetailPresenter mDetailPresenter;
    private List<Data> mHourlyForecastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setUpMVP();
        setLayoutManager();
    }

    @Override
    public void displayHourlyForecast(List<Data> dataList) {
        mHourlyForecastList = dataList;
        mWeatherAdapter = new WeatherAdapter(dataList);
        recyclerView.setAdapter(mWeatherAdapter);
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

    private void setLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


}
