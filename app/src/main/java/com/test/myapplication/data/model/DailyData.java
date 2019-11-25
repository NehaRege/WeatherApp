
package com.test.myapplication.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyData {

    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("sunriseTime")
    @Expose
    public Integer sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    public Integer sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    public Double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    public Double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    public Double precipIntensityMax;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    public Integer precipIntensityMaxTime;
    @SerializedName("precipProbability")
    @Expose
    public Double precipProbability;
    @SerializedName("precipType")
    @Expose
    public String precipType;
    @SerializedName("temperatureHigh")
    @Expose
    public Double temperatureHigh;
    @SerializedName("temperatureHighTime")
    @Expose
    public Integer temperatureHighTime;
    @SerializedName("temperatureLow")
    @Expose
    public Double temperatureLow;
    @SerializedName("temperatureLowTime")
    @Expose
    public Integer temperatureLowTime;
    @SerializedName("apparentTemperatureHigh")
    @Expose
    public Double apparentTemperatureHigh;
    @SerializedName("apparentTemperatureHighTime")
    @Expose
    public Integer apparentTemperatureHighTime;
    @SerializedName("apparentTemperatureLow")
    @Expose
    public Double apparentTemperatureLow;
    @SerializedName("apparentTemperatureLowTime")
    @Expose
    public Integer apparentTemperatureLowTime;
    @SerializedName("dewPoint")
    @Expose
    public Double dewPoint;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("windSpeed")
    @Expose
    public Double windSpeed;
    @SerializedName("windGust")
    @Expose
    public Double windGust;
    @SerializedName("windGustTime")
    @Expose
    public Integer windGustTime;
    @SerializedName("windBearing")
    @Expose
    public Integer windBearing;
    @SerializedName("cloudCover")
    @Expose
    public Double cloudCover;
    @SerializedName("uvIndex")
    @Expose
    public Integer uvIndex;
    @SerializedName("uvIndexTime")
    @Expose
    public Integer uvIndexTime;
    @SerializedName("visibility")
    @Expose
    public Double visibility;
    @SerializedName("ozone")
    @Expose
    public Double ozone;
    @SerializedName("temperatureMin")
    @Expose
    public Double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    public Integer temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    public Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    public Integer temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    public Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    public Integer apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    public Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    public Integer apparentTemperatureMaxTime;

}
