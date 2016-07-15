package com.test.weatherdemo.locationsetting.presenter;

import android.content.Context;

import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.locationsetting.model.SettingModel;
import com.test.weatherdemo.locationsetting.model.SettingModelImpl;
import com.test.weatherdemo.locationsetting.view.SettingView;

import io.realm.RealmResults;
import rx.Subscriber;

/**
 * Created by turbo on 2016/7/15.
 */
public class SettingPresenterImpl implements SettingPresenter {

    private SettingView settingView;
    private SettingModel<RealmResults<District>> settingModel;
    private Context context;

    public SettingPresenterImpl(Context context, SettingView settingView) {
        this.settingView = settingView;
        this.settingModel = new SettingModelImpl();
        this.context = context;
    }

    @Override
    public void onPause() {
        settingModel.close();
    }

    @Override
    public void onResume() {
        settingModel.getWeather(context, settingView.getObservable(), new Subscriber<RealmResults<District>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                settingView.showError();
            }

            @Override
            public void onNext(RealmResults<District> districts) {
                if (districts == null || districts.size() == 0) {
                    settingView.showEmpty();
                } else {
                    settingView.showCity(districts);
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
