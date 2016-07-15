package com.test.weatherdemo.main.model;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.test.weatherdemo.beans.Weather;
import com.test.weatherdemo.main.WeatherAdapter;
import com.test.weatherdemo.utils.CommonUtils;
import com.test.weatherdemo.utils.network.APIClient;

import io.realm.Realm;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by turbo on 2016/7/14.
 */
public class MainModelImpl implements MainModel {
    private Subscription subscription;
    private Realm realm;

    public MainModelImpl() {
        realm = realm.getDefaultInstance();
    }

    @Override
    public void getWeather(String location, Subscriber subscriber) {
        //获取天气数据并保存到数据库，如果无网络那么取数据库里内容，如果没有取到，显示网络异常
        subscription = APIClient.getInstance().getWeather(location, "json", "B95329fb7fdda1e32ba3e3a245193146")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // Retrofit put us on a worker thread. Move back to UI
                .filter(new Func1<Weather, Boolean>() {
                    @Override
                    public Boolean call(Weather weather) {
                        return weather.getError() == 0;
                    }
                })
                .subscribe(subscriber);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (realm != null) {
            realm.close();
        }
    }
    public void close(){
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
