package com.test.weatherdemo.main.model;

import com.test.weatherdemo.beans.ResultsEntity;
import com.test.weatherdemo.beans.Weather;

import io.realm.RealmQuery;
import rx.Subscriber;

/**
 * Created by turbo on 2016/7/14.
 */
public interface MainModel {
    void getWeather(String location, Subscriber subscriber);

    void close();

    void saveData(Weather weather);

    void getWeatherFromRealm(String location, Subscriber subscriber);
}
