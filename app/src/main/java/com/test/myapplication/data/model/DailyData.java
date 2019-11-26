
package com.test.myapplication.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyData {

    @SerializedName("time")
    @Expose
    public long time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("sunriseTime")
    @Expose
    public long sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    public long sunsetTime;
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
    public long precipIntensityMaxTime;
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
    public long temperatureHighTime;
    @SerializedName("temperatureLow")
    @Expose
    public Double temperatureLow;
    @SerializedName("temperatureLowTime")
    @Expose
    public long temperatureLowTime;
    @SerializedName("apparentTemperatureHigh")
    @Expose
    public Double apparentTemperatureHigh;
    @SerializedName("apparentTemperatureHighTime")
    @Expose
    public long apparentTemperatureHighTime;
    @SerializedName("apparentTemperatureLow")
    @Expose
    public Double apparentTemperatureLow;
    @SerializedName("apparentTemperatureLowTime")
    @Expose
    public long apparentTemperatureLowTime;
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
    public long windGustTime;
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
    public long uvIndexTime;
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
    public long temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    public Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    public long temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    public Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    public long apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    public Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    public long apparentTemperatureMaxTime;

}
