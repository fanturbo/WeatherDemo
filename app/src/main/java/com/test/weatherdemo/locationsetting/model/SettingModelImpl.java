package com.test.weatherdemo.locationsetting.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.locationsetting.LocationAdapter;
import com.test.weatherdemo.utils.CommonUtils;
import com.test.weatherdemo.utils.SharedPreferencesUtils;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by turbo on 2016/7/15.
 */
public class SettingModelImpl implements SettingModel {
    protected Realm realm;
    protected Subscription subscription;

    public void getWeather(Context context, Observable observable, Subscriber subscriber) {
        CommonUtils.writeCity2Realm(context);
        realm = Realm.getDefaultInstance();
        subscription = observable
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        if (textViewTextChangeEvent.count() == 0 || textViewTextChangeEvent.text().equals("")) {
                            return false;
                        }
                        return true;
                    }
                })
                .flatMap(new Func1<TextViewTextChangeEvent, Observable<RealmResults<District>>>() {
                    @Override
                    public Observable<RealmResults<District>> call(TextViewTextChangeEvent event) {
                        // Use Async API to move Realm queries off the main thread.
                        // Realm currently doesn't support the standard Schedulers.
                        return realm.where(District.class)
                                .beginsWith("name", event.text().toString().toUpperCase())
                                .or().beginsWith("name", event.text().toString().toLowerCase())
                                .findAllSortedAsync("name").asObservable();
                    }
                })
                .filter(new Func1<RealmResults<District>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<District> districts) {
                        // Only continue once data is actually loaded
                        // RealmObservables will emit the unloaded (empty) list as it's first item
                        return districts.isLoaded();
                    }
                })
                .subscribe(subscriber);
    }

    @Override
    public void close() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        realm.close();
    }
}
