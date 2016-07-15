package com.test.weatherdemo.main.view;


import com.test.weatherdemo.beans.Weather;

import io.realm.RealmResults;

/**
 * Created by turbo on 2016/7/15.
 */
public interface MainView {
    void showWeather(Weather weather);

    void showError();

}
