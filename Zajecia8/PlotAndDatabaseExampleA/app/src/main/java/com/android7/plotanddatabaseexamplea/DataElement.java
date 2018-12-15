package com.android7.plotanddatabaseexamplea;

import com.google.gson.annotations.SerializedName;

public class DataElement {
    @SerializedName("id")
    public Integer id;

    @SerializedName("temp")
    public Double temperature;
}
