package com.example.sandy.newgeckotransporter;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sandy on 03-Oct-18.
 */

public class Apiclient {
    private static retrofit2.Retrofit retrofit;
    private static final String BASE_URL = "http://navjacinth9.000webhostapp.com/json/";

    public static retrofit2.Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
