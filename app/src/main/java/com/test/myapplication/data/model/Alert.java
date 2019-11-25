
package com.test.myapplication.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("regions")
    @Expose
    public List<String> regions = null;
    @SerializedName("severity")
    @Expose
    public String severity;
    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("expires")
    @Expose
    public Integer expires;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("uri")
    @Expose
    public String uri;

}
