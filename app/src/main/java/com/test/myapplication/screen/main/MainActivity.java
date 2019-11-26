package com.test.myapplication.screen.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.myapplication.R;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.screen.detail.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    private static String TAG = "MainActivity";

    @BindView(R.id.mainToolbar)
    Toolbar toolbar;
    @BindView(R.id.mainLayout)
    ConstraintLayout mainLayout;
    @BindView(R.id.weatherForecastView)
    CardView weatherForecastView;
    @BindView(R.id.errorView)
    CardView errorView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.currentHigh)
    TextView currentHigh;
    @BindView(R.id.currentLow)
    TextView currentLow;
    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;

    private MainPresenter mMainPresenter;
    private NetworkInfo mNetworkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setToolbar();
        setUpMVP();
    }

    @Override
    public void displayForecast(Forecast forecast) {

    }

    @Override
    public void isLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.GONE);
            mainLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorView() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorView() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showForecastDetailScreen() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.weatherForecastView)
    void onWeatherForecastViewClick() {
        Log.d(TAG, "onWeatherForecastViewClick: ");
        mMainPresenter.onWeatherForecastViewClick();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        setSupportActionBar(toolbar);
    }

    private void setUpMVP() {
        mMainPresenter = new MainPresenterImpl(this, getNetworkInfo());
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
    }
}
