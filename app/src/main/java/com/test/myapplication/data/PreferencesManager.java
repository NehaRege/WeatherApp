package com.test.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.data.model.Location.Location;
import com.test.myapplication.util.Constants;

public class PreferencesManager {
    private static String TAG = "PreferencesManager";


    private static SharedPreferences sharedPrefsInstance = null;

    public static void init(Context context) {
        if (sharedPrefsInstance == null) {
            sharedPrefsInstance = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void saveForecast(Forecast forecast) {
        Log.d(TAG, "saveForecast: ");
        SharedPreferences.Editor prefsEditor = sharedPrefsInstance.edit();
        Gson gson = new Gson();
        String json = gson.toJson(forecast);
        prefsEditor.putString(Constants.SHARED_PREFS_FORECAST_KEY, json);
        prefsEditor.apply();
    }

    public static Forecast getSavedForecast() {
        Log.d(TAG, "getSavedForecast: ");
        Gson gson = new Gson();
        String json = sharedPrefsInstance.getString(Constants.SHARED_PREFS_FORECAST_KEY, "");
        return gson.fromJson(json, Forecast.class);
    }

    public static void saveLocation(double latitude, double longitude, String name) {
        Location location = new Location(latitude, longitude, name);
        SharedPreferences.Editor prefsEditor = sharedPrefsInstance.edit();
        Gson gson = new Gson();
        String json = gson.toJson(location);
        prefsEditor.putString(Constants.SHARED_PREFS_LOCATION_KEY, json);
        prefsEditor.apply();
    }

    public static Location getSavedLocation() {
        Gson gson = new Gson();
        String json = sharedPrefsInstance.getString(Constants.SHARED_PREFS_LOCATION_KEY, "");
        return gson.fromJson(json, Location.class);
    }

//    public static double getSavedLatitude() {
//        Gson gson = new Gson();
//        String json = sharedPrefsInstance.getString(Constants.SHARED_PREFS_FORECAST_KEY, "");
//        return gson.fromJson(json, Forecast.class);
//    }
//
//    public static double getSavedLongitude() {
//        Gson gson = new Gson();
//        String json = sharedPrefsInstance.getString(Constants.SHARED_PREFS_FORECAST_KEY, "");
//        return gson.fromJson(json, Forecast.class);
//    }


}
