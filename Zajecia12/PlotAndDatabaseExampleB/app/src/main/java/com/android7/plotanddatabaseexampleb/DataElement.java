package com.android7.plotanddatabaseexampleb;

import com.google.gson.annotations.SerializedName;

public class DataElement {
    @SerializedName("id")
    public Integer id;

    @SerializedName("temp")
    public Double temperature;
}
