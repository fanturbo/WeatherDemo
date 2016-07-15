package com.test.weatherdemo.locationsetting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.test.weatherdemo.BaseActivity;
import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.City;
import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.beans.Province;
import com.test.weatherdemo.locationsetting.LocationAdapter;
import com.test.weatherdemo.locationsetting.presenter.SettingPresenterImpl;
import com.test.weatherdemo.locationsetting.view.SettingView;
import com.test.weatherdemo.utils.CommonUtils;
import com.test.weatherdemo.utils.SharedPreferencesUtils;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class LocationSettingActivity extends BaseActivity implements SettingView {

    @Bind(R.id.et_search_key)
    EditText editText;
    @Bind(R.id.rv_city)
    RecyclerView recyclerView;
    SettingPresenterImpl settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        settingPresenter = new SettingPresenterImpl(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        settingPresenter.onResume();
    }

    public Observable getObservable() {
        return RxTextView.textChangeEvents(editText)
                .debounce(200, TimeUnit.MILLISECONDS) // default Scheduler is Schedulers.computation()
                .observeOn(AndroidSchedulers.mainThread());// Needed to access Realm data
    }

    @Override
    public void showCity(final RealmResults<District> districts) {
        LocationAdapter adapter = new LocationAdapter(LocationSettingActivity.this, districts, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferencesUtils.saveString(LocationSettingActivity.this, "location", districts.get(position).getName());
                LocationSettingActivity.this.finish();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(LocationSettingActivity.this, "网络请求错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        Toast.makeText(LocationSettingActivity.this, "没有搜索到你想要查找的城市", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        settingPresenter.onDestroy();
    }
}
