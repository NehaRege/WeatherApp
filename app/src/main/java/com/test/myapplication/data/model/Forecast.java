
package com.test.myapplication.data.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("latitude")
    public Double latitude;
    @SerializedName("longitude")
    public Double longitude;
    @SerializedName("timezone")
    public String timezone;
    @SerializedName("currently")
    public Currently currently;
    @SerializedName("hourly")
    public Hourly hourly;
    @SerializedName("daily")
    public Daily daily;
    @SerializedName("alerts")
    public List<Alert> alerts = null;
    @SerializedName("flags")
    public Flags flags;
    @SerializedName("offset")
    public Integer offset;

}
