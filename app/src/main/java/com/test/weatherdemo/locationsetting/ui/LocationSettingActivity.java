package com.test.weatherdemo.locationsetting.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.City;
import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.beans.Province;
import com.test.weatherdemo.utils.CommonUtils;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmQuery;

public class LocationSettingActivity extends AppCompatActivity {

    @Bind(R.id.et_search_key)
    private EditText editText;
    @Bind(R.id.rv_city)
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CommonUtils.writeCity2Realm(this);
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(Province.class).count();
        String searchKey = null;

        RealmQuery<Province> provinceRealmQuery = realm.where(Province.class).beginsWith("name", searchKey);
        RealmQuery<City> cityRealmQuery = realm.where(City.class).beginsWith("name", searchKey);
        RealmQuery<District> districtRealmQuery = realm.where(District.class).beginsWith("name", searchKey);


    }

}
