package com.android7.plotanddatabaseexampleb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    @GET("/json?")
    Call<List<Temperature>> getWSIZTemperature(@Query("limit") int limit, @Query("sensor") int sensor);
    @GET("/json?")
    Call<List<Temperature>> getHumidity(@Query("limit") int limit);
}
