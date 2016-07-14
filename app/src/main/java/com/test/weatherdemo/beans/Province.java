package com.test.weatherdemo.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Province extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<City> citys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(RealmList<City> citys) {
        this.citys = citys;
    }

    @Override
    public String toString() {
        return name;
    }

}
