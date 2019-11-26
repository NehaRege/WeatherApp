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
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
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

import java.io.IOException;
import java.util.List;

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
    View errorView;
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


        //TODO: get location
//        mMainPresenter.getWeatherForecast(latitude, longitude);
//        mMainPresenter.getWeatherForecast(40.7128, 74.0060);

        //TODO: check error view click
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
            }
        });
    }

    @Override
    public void getWeatherForecast(double latitude, double longitude) {
        mMainPresenter.getWeatherForecast(latitude, longitude);
    }

    @Override
    public void displayForecast(Forecast forecast) {
        Log.d(TAG, "displayForecast: ");

        currentHigh.setText(forecast.currently.summary);
        currentLow.setText("low ---");

        // date time
        time.setText(TimeUtils.timestampToDate(forecast.currently.time));
        date.setText(TimeUtils.timestampToTime(forecast.currently.time));
        weatherForecastView.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayLocation(String name) {
        location.setText(name != null ? name : "");
    }

    @Override
    public void hideForecastView() {
        weatherForecastView.setVisibility(View.GONE);
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
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void goToForecastDetailScreen() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void getCurrentLocation() {

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

    @OnClick(R.id.errorView)
    void onErrorViewCardClick() {
        Log.d(TAG, "onErrorViewCardClick: ");
        mMainPresenter.onErrorViewClick();
    }

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

    private void displayLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(getApplicationContext());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);

                if (fetchedAddress != null && fetchedAddress.getLocality() != null && !fetchedAddress.getLocality().isEmpty()) {
                    mMainPresenter.saveLocation(latitude, longitude, fetchedAddress.getLocality());
                    location.setText(fetchedAddress.getLocality());
                }

            } else {
                location.setText(R.string.searching_address);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
