package com.android7.plotanddatabaseexample;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Temperature {

    public List<DataTemp> dataTemp = new ArrayList();

    public class DataTemp{
        @SerializedName("id")
        public Integer id;
    }
}
