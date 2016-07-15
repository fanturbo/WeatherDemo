package com.test.weatherdemo.locationsetting.model;

import android.content.Context;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by turbo on 2016/7/15.
 */
public interface SettingModel<T> {
    void close();

    void getWeather(Context context, Observable observable, Subscriber<T> subscriber);
}
