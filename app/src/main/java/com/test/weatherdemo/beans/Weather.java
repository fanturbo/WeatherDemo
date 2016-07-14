package com.test.weatherdemo.beans;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by turbo on 2016/7/14.
 */
public class Weather extends RealmObject {

    /**
     * error : 0
     * status : success
     * date : 2016-07-14
     * results : [{"currentCity":"北京","pm25":"63","index":[{"title":"穿衣","zs":"热","tipt":"穿衣指数","des":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},{"title":"洗车","zs":"不宜","tipt":"洗车指数","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"title":"感冒","zs":"少发","tipt":"感冒指数","des":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"title":"运动","zs":"较不宜","tipt":"运动指数","des":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},{"title":"紫外线强度","zs":"中等","tipt":"紫外线强度指数","des":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}],"weather_data":[{"date":"周四 07月14日 (实时：31℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/zhenyu.png","weather":"阵雨","wind":"微风","temperature":"31 ~ 22℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"微风","temperature":"27 ~ 21℃"},{"date":"周六","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"微风","temperature":"29 ~ 22℃"},{"date":"周日","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"微风","temperature":"33 ~ 23℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    /**
     * currentCity : 北京
     * pm25 : 63
     * index : [{"title":"穿衣","zs":"热","tipt":"穿衣指数","des":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},{"title":"洗车","zs":"不宜","tipt":"洗车指数","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"title":"感冒","zs":"少发","tipt":"感冒指数","des":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"title":"运动","zs":"较不宜","tipt":"运动指数","des":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},{"title":"紫外线强度","zs":"中等","tipt":"紫外线强度指数","des":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}]
     * weather_data : [{"date":"周四 07月14日 (实时：31℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/zhenyu.png","weather":"阵雨","wind":"微风","temperature":"31 ~ 22℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"微风","temperature":"27 ~ 21℃"},{"date":"周六","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"微风","temperature":"29 ~ 22℃"},{"date":"周日","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"微风","temperature":"33 ~ 23℃"}]
     */

    private List<ResultsEntity> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        private String currentCity;
        private String pm25;
        /**
         * title : 穿衣
         * zs : 热
         * tipt : 穿衣指数
         * des : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
         */

        private List<IndexEntity> index;
        /**
         * date : 周四 07月14日 (实时：31℃)
         * dayPictureUrl : http://api.map.baidu.com/images/weather/day/zhenyu.png
         * nightPictureUrl : http://api.map.baidu.com/images/weather/night/zhenyu.png
         * weather : 阵雨
         * wind : 微风
         * temperature : 31 ~ 22℃
         */

        private List<WeatherDataEntity> weather_data;

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

        public void setIndex(List<IndexEntity> index) {
            this.index = index;
        }

        public List<WeatherDataEntity> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherDataEntity> weather_data) {
            this.weather_data = weather_data;
        }

        public static class IndexEntity {
            private String title;
            private String zs;
            private String tipt;
            private String des;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getZs() {
                return zs;
            }

            public void setZs(String zs) {
                this.zs = zs;
            }

            public String getTipt() {
                return tipt;
            }

            public void setTipt(String tipt) {
                this.tipt = tipt;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }
        }

        public static class WeatherDataEntity {
            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String weather;
            private String wind;
            private String temperature;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayPictureUrl() {
                return dayPictureUrl;
            }

            public void setDayPictureUrl(String dayPictureUrl) {
                this.dayPictureUrl = dayPictureUrl;
            }

            public String getNightPictureUrl() {
                return nightPictureUrl;
            }

            public void setNightPictureUrl(String nightPictureUrl) {
                this.nightPictureUrl = nightPictureUrl;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
