
package com.test.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class DailyData {

    @SerializedName("time")
    public long time;
    @SerializedName("summary")
    public String summary;
    @SerializedName("icon")
    public String icon;
    @SerializedName("sunriseTime")
    public long sunriseTime;
    @SerializedName("sunsetTime")
    public long sunsetTime;
    @SerializedName("moonPhase")
    public Double moonPhase;
    @SerializedName("precipIntensity")
    public Double precipIntensity;
    @SerializedName("precipIntensityMax")
    public Double precipIntensityMax;
    @SerializedName("precipIntensityMaxTime")
    public long precipIntensityMaxTime;
    @SerializedName("precipProbability")
    public Double precipProbability;
    @SerializedName("precipType")
    public String precipType;
    @SerializedName("temperatureHigh")
    public Double temperatureHigh;
    @SerializedName("temperatureHighTime")
    public long temperatureHighTime;
    @SerializedName("temperatureLow")
    public Double temperatureLow;
    @SerializedName("temperatureLowTime")
    public long temperatureLowTime;
    @SerializedName("apparentTemperatureHigh")
    public Double apparentTemperatureHigh;
    @SerializedName("apparentTemperatureHighTime")
    public long apparentTemperatureHighTime;
    @SerializedName("apparentTemperatureLow")
    public Double apparentTemperatureLow;
    @SerializedName("apparentTemperatureLowTime")
    public long apparentTemperatureLowTime;
    @SerializedName("dewPoint")
    public Double dewPoint;
    @SerializedName("humidity")
    public Double humidity;
    @SerializedName("pressure")
    public Double pressure;
    @SerializedName("windSpeed")
    public Double windSpeed;
    @SerializedName("windGust")
    public Double windGust;
    @SerializedName("windGustTime")
    public long windGustTime;
    @SerializedName("windBearing")
    public Integer windBearing;
    @SerializedName("cloudCover")
    public Double cloudCover;
    @SerializedName("uvIndex")
    public Integer uvIndex;
    @SerializedName("uvIndexTime")
    public long uvIndexTime;
    @SerializedName("visibility")
    public Double visibility;
    @SerializedName("ozone")
    public Double ozone;
    @SerializedName("temperatureMin")
    public Double temperatureMin;
    @SerializedName("temperatureMinTime")
    public long temperatureMinTime;
    @SerializedName("temperatureMax")
    public Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    public long temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    public Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    public long apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    public Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    public long apparentTemperatureMaxTime;

}
