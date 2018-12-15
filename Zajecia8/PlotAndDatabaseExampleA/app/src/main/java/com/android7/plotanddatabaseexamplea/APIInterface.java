package com.android7.plotanddatabaseexamplea;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    @GET("/json?")
    Call<List<DataElement>> getWSIZTemperature(@Query("limit") int no);
}
