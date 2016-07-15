package com.test.weatherdemo.locationsetting.view;

import com.test.weatherdemo.beans.District;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by turbo on 2016/7/15.
 */
public interface SettingView {
    void showCity(RealmResults<District> districts);

    void showError();

    void showEmpty();

    Observable getObservable();
}
