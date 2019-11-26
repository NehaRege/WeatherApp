package com.test.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.test.myapplication.data.model.Forecast;
import com.test.myapplication.util.Constants;

public class PreferencesManager {

    private static SharedPreferences sharedPrefsInstance = null;

    public static void init(Context context) {
        if (sharedPrefsInstance == null) {
            sharedPrefsInstance = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void saveForecast(Forecast forecast) {
        SharedPreferences.Editor prefsEditor = sharedPrefsInstance.edit();
        Gson gson = new Gson();
        String json = gson.toJson(forecast);
        prefsEditor.putString(Constants.SHARED_PREFS_FORECAST_KEY, json);
        prefsEditor.apply();
    }

    public static Forecast getSavedForecast() {
        Gson gson = new Gson();
        String json = sharedPrefsInstance.getString(Constants.SHARED_PREFS_FORECAST_KEY, "");
        return gson.fromJson(json, Forecast.class);
    }
}
