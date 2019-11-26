
package com.test.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("time")
    public long time;
    @SerializedName("summary")
    public String summary;
    @SerializedName("icon")
    public String icon;
    @SerializedName("precipIntensity")
    public Double precipIntensity;
    @SerializedName("precipProbability")
    public Double precipProbability;
    @SerializedName("precipType")
    public String precipType;
    @SerializedName("temperature")
    public Double temperature;
    @SerializedName("apparentTemperature")
    public Double apparentTemperature;
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
    @SerializedName("windBearing")
    public Integer windBearing;
    @SerializedName("cloudCover")
    public Double cloudCover;
    @SerializedName("uvIndex")
    public Integer uvIndex;
    @SerializedName("visibility")
    public Double visibility;
    @SerializedName("ozone")
    public Double ozone;

}
