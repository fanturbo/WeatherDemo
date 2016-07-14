package com.test.weatherdemo.utils.network;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by turbo on 2016/7/14.
 */
public class APIClient {
    private static APIService apiService;

    public static APIService getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://api.nytimes.com/")
                .build();
        apiService = retrofit.create(APIService.class);
        return apiService;
    }
}
