
package com.test.myapplication.data.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Hourly {

    @SerializedName("summary")
    public String summary;
    @SerializedName("icon")
    public String icon;
    @SerializedName("data")
    public List<Data> data = null;

}
