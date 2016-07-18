package com.test.weatherdemo.main.model;

import android.util.Log;

import com.test.weatherdemo.beans.ResultsEntity;
import com.test.weatherdemo.beans.Weather;
import com.test.weatherdemo.utils.network.APIClient;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

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

    public void getWeatherFromRealm(String location, Subscriber subscriber) {
        //想要查出Weather表里 currentCity=location的数据
        //TODO java.lang.ClassCastException: io.realm.RealmResults cannot be cast to com.test.weatherdemo.beans.Weather
        RealmResults<Weather> weathers = realm.where(Weather.class)
                .findAll();
        for (Weather weather : weathers) {
            List<ResultsEntity> results = weather.getResults();
            for (ResultsEntity entity : results) {
                if (location.equals(entity.getCurrentCity())) {
                    weather.asObservable().subscribe(subscriber);
                }
            }
        }

    }

    @Override
    public void saveData(final Weather weather) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long weatherCount = realm.where(Weather.class).count();
                weather.setId((int) weatherCount + 1);
                realm.copyToRealmOrUpdate(weather);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (realm != null) {
            realm.close();
        }
    }

    public void close() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
