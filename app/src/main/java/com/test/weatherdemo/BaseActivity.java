package com.test.weatherdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Subscription;

/**
 * Created by snail on 16/7/14.
 */
public class BaseActivity extends AppCompatActivity {
    protected Realm realm;
    protected Subscription subscription;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (subscription != null && subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null)
            realm.close();
    }
}
