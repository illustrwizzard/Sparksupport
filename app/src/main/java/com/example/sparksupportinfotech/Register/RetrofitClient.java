package com.example.sparksupportinfotech.Register;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://148.251.86.36:8001/";

    private static Retrofit retrofit;

    public static ApiCall getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiCall.class);
    }
}

