/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.weatherdemo.utils.network;


import com.test.weatherdemo.beans.Weather;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Retrofit interface for the New York Times WebService
 */
public interface APIService {
    //http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=B95329fb7fdda1e32ba3e3a245193146
    @GET("/telematics/v3/weather")
    Observable<Weather> getWeather(
            @Query(value = "location", encoded = true) String location,
            @Query(value = "output", encoded = true) String output,
            @Query(value = "ak", encoded = true) String ak);
}