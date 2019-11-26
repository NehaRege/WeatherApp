package com.test.myapplication.screen.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.test.myapplication.R;
import com.test.myapplication.data.PreferencesManager;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.screen.detail.DetailActivity;
import com.test.myapplication.util.Constants;
import com.test.myapplication.util.TimeUtils;

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
    ConstraintLayout weatherForecastView;
    @BindView(R.id.errorView)
    CardView errorView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.temp)
    TextView temperature;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.highLowTemp)
    TextView highLowTemp;
    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;

    private MainPresenter mMainPresenter;
    private FusedLocationProviderClient mFusedLocationClient;

    // TODO: Save location in SharedPrefs
    public static double latitude = 40.7128;
    public static double longitude = 74.0060;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();

        PreferencesManager.init(getApplicationContext());

        setUpMVP();

        initLocation();

        //TODO: check error view click
        errorView.setOnClickListener(view -> {
            Log.d(TAG, "onClick: ");
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    @Override
    public void getWeatherForecast(double latitude, double longitude) {
        mMainPresenter.getWeatherForecast(latitude, longitude);
    }

    @Override
    public void displayForecast(Forecast forecast, double minTemp, double maxTemp) {
        Log.d(TAG, "displayForecast: ");

        temperature.setText(getString(R.string.temperatureWithFahrenheit, String.valueOf(forecast.currently.temperature)));

        highLowTemp.setText(getString(R.string.highLowTempMessage, String.valueOf(maxTemp), String.valueOf(minTemp)));

        // date time
        time.setText(TimeUtils.timestampToDate(forecast.currently.time));
        date.setText(TimeUtils.timestampToTime(forecast.currently.time));

        // weather icon
        setIcon(forecast.currently != null ? forecast.currently.icon : "");
    }

    @Override
    public void displayLocation(String name) {
        location.setText(name != null ? name : "");
    }

    @Override
    public void showForecastView() {
        weatherForecastView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideForecastView() {
        weatherForecastView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.INVISIBLE);
            weatherForecastView.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showErrorView() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorView() {
        errorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void goToForecastDetailScreen() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    @Override
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.LOCATION_PERMISSION_ID
        );
    }

    @Override
    public void openSettingsScreen() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    public String getSearchingLocationString() {
        return getApplicationContext().getString(R.string.searching_address);
    }

    @OnClick(R.id.weatherForecastView)
    void onWeatherForecastViewClick() {
        mMainPresenter.onWeatherForecastViewClick();
    }

//    @OnClick(R.id.errorView)
//    void onErrorViewCardClick() {
//        Log.d(TAG, "onErrorViewCardClick: ");
//        mMainPresenter.onErrorViewClick();
//    }

    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        setSupportActionBar(toolbar);
    }

    private void setUpMVP() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;

        mMainPresenter = new MainPresenterImpl(this, networkInfo, getPreferences(MODE_PRIVATE), new Geocoder(getApplicationContext()));
    }

    private void initLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.LOCATION_PERMISSION_ID
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Constants.LOCATION_PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
                getLastLocation();
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                Log.d(TAG, "getLastLocation: LAT =============== " + location.getLatitude());
                                Log.d(TAG, "getLastLocation: LONGITUDE =============== " + location.getLongitude());

                                getWeatherForecast(location.getLatitude(), location.getLongitude());
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();

            getWeatherForecast(location.getLatitude(), location.getLongitude());


            Log.d(TAG, "onLocationResult: LAT ===============> " + location.getLatitude());
            Log.d(TAG, "onLocationResult: LONGITUDE ===============> " + location.getLongitude());
        }
    };

    private void setIcon(String icon) {
        switch (icon) {
            case "clear-day":
                weatherIcon.setImageResource(R.drawable.sun);
                break;
            case "clear-night":
                weatherIcon.setImageResource(R.drawable.clear_night);
                break;
            case "partly-cloudy-day":
                weatherIcon.setImageResource(R.drawable.cloudy_day);
                break;
            case "partly-cloudy-night":
                weatherIcon.setImageResource(R.drawable.cloudy_night);
                break;
            case "rain":
                weatherIcon.setImageResource(R.drawable.rain);
                break;
            case "sleet":
            case "thunderstorm":
            case "cloudy":
            case "fog":
            case "wind":
            case "tornado":
                weatherIcon.setImageResource(R.drawable.storm);
                break;
            case "snow":
            case "hail":
                weatherIcon.setImageResource(R.drawable.snow);
                break;
            default:
                weatherIcon.setImageResource(R.drawable.cloud);
        }
    }

}
