package com.test.weatherdemo.main.model;

import rx.Subscriber;

/**
 * Created by turbo on 2016/7/14.
 */
public interface MainModel {
    void getWeather(String location, Subscriber subscriber);

    void close();
}
