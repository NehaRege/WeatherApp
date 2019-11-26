
package com.test.myapplication.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currently {

    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("nearestStormDistance")
    @Expose
    public Integer nearestStormDistance;
    @SerializedName("nearestStormBearing")
    @Expose
    public Integer nearestStormBearing;
    @SerializedName("precipIntensity")
    @Expose
    public Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    public Double precipProbability;
    @SerializedName("temperature")
    @Expose
    public Double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    public Double apparentTemperature;
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
    @SerializedName("windBearing")
    @Expose
    public Integer windBearing;
    @SerializedName("cloudCover")
    @Expose
    public Double cloudCover;
    @SerializedName("uvIndex")
    @Expose
    public Integer uvIndex;
    @SerializedName("visibility")
    @Expose
    public Double visibility;
    @SerializedName("ozone")
    @Expose
    public Double ozone;

}
