package com.test.weatherdemo;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import timber.log.Timber;

/**
 * Created by turbo on 2016/7/13.
 */
public class WeatherApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initializeTimber();
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                super.handleError(e);
                Timber.e(e.toString());
            }
        });

        // Configure default configuration for Realm
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    /**
     * Initialize Timber logging
     */
    protected void initializeTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    public static Context getContext() {
        return context;
    }
}
