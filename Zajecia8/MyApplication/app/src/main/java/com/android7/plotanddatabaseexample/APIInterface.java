package com.android7.plotanddatabaseexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    @GET("/json?")
    Call<Temperature> getWSIZTemperature(@Query("limit") int no);
}
