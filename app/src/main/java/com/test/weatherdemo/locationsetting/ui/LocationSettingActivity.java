package com.test.weatherdemo.locationsetting.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.Province;
import com.test.weatherdemo.utils.CommonUtils;

import io.realm.Realm;

public class LocationSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CommonUtils.writeCity2Realm(this);
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(Province.class).count();

    }

}
