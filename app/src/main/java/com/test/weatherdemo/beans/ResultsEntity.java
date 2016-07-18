package com.test.weatherdemo.beans;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by snail on 16/7/14.
 */
public class ResultsEntity extends RealmObject {

    @PrimaryKey
    private String currentCity;
    private String pm25;
    /**
     * title : 穿衣
     * zs : 热
     * tipt : 穿衣指数
     * des : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
     */

    private RealmList<IndexEntity> index;
    /**
     * date : 周四 07月14日 (实时：31℃)
     * dayPictureUrl : http://api.map.baidu.com/images/weather/day/zhenyu.png
     * nightPictureUrl : http://api.map.baidu.com/images/weather/night/zhenyu.png
     * weather : 阵雨
     * wind : 微风
     * temperature : 31 ~ 22℃
     */

    private RealmList<WeatherDataEntity> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<IndexEntity> getIndex() {
        return index;
    }

    public void setIndex(RealmList<IndexEntity> index) {
        this.index = index;
    }

    public List<WeatherDataEntity> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(RealmList<WeatherDataEntity> weather_data) {
        this.weather_data = weather_data;
    }
}
