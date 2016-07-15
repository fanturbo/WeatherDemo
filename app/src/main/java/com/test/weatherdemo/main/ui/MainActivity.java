package com.test.weatherdemo.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.test.weatherdemo.BaseActivity;
import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.Weather;
import com.test.weatherdemo.locationsetting.ui.LocationSettingActivity;
import com.test.weatherdemo.main.WeatherAdapter;
import com.test.weatherdemo.main.presenter.MainPresenterImpl;
import com.test.weatherdemo.main.view.MainView;
import com.test.weatherdemo.utils.SharedPreferencesUtils;
import com.test.weatherdemo.utils.network.APIClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, MainView {

    @Bind(R.id.refresh_view)
    SwipeRefreshLayout refreshView;
    @Bind(R.id.list_view)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        refreshView.setColorSchemeResources(
                R.color.swiperefresh_color1,
                R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,
                R.color.swiperefresh_color4
        );
        refreshView.setOnRefreshListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, LocationSettingActivity.class), RESULT_OK);
            }
        });
        mainPresenter = new MainPresenterImpl(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == RESULT_OK) {
            mainPresenter.onResume();
        }
    }

    @Override
    public void onRefresh() {
        String location = SharedPreferencesUtils.getString(this, "location", "");
        if (!"".equals(location)) {
            mainPresenter.onResume();
        }
    }

    @Override
    public void showWeather(Weather weather) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(MainActivity.this, weather.getResults().get(0).getWeather_data(), new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        recyclerView.setAdapter(weatherAdapter);
        refreshView.setRefreshing(false);
    }

    @Override
    public void showError() {
        Toast.makeText(MainActivity.this, "网络请求错误", Toast.LENGTH_SHORT).show();
    }
}
