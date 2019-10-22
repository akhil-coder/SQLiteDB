package com.appface.akhil.sqlitedb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BaseUrl = "http://182.74.184.22:8081/Storage1/CandidateCtrl/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
