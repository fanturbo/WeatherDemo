/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.weatherdemo.main.presenter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.test.weatherdemo.beans.Weather;
import com.test.weatherdemo.main.WeatherAdapter;
import com.test.weatherdemo.main.model.MainModel;
import com.test.weatherdemo.main.model.MainModelImpl;
import com.test.weatherdemo.main.view.MainView;
import com.test.weatherdemo.utils.SharedPreferencesUtils;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Presenter class for controlling the Main Activity
 */
public class MainPresenterImpl implements MainPresenter {

    private MainModel mainModel;
    private MainView mainView;
    private Context context;

    public MainPresenterImpl(Context context, MainView mainView) {
        this.mainModel = new MainModelImpl();
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        String location = SharedPreferencesUtils.getString(context, "location", "");
        if (!"".equals(location)) {
            Subscriber subscriber = new Subscriber<Weather>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mainView.showError();
                }

                @Override
                public void onNext(Weather weather) {
                    mainView.showWeather(weather);
                }
            };
            mainModel.getWeather(location, subscriber);
        }
    }

    @Override
    public void onDestroy() {
        mainModel.close();
    }
}
