
package com.test.myapplication.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flags {

    @SerializedName("sources")
    @Expose
    public List<String> sources = null;
    @SerializedName("nearest-station")
    @Expose
    public Double nearestStation;
    @SerializedName("units")
    @Expose
    public String units;

}
