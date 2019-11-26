
package com.test.myapplication.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Flags {

    @SerializedName("sources")
    public List<String> sources = null;
    @SerializedName("nearest-station")
    public Double nearestStation;
    @SerializedName("units")
    public String units;

}
