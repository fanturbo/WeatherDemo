package com.test.weatherdemo.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City extends RealmObject {
	@PrimaryKey
	private String id;
	private String name;
	private RealmList<District> disList;

	public City() {
	}

	public City(String id, String name, RealmList<District> disList) {
		super();
		this.id = id;
		this.name = name;
		this.disList = disList;
	}

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

	public List<District> getDisList() {
		return disList;
	}

	public void setDisList(RealmList<District> disList) {
		this.disList = disList;
	}

	@Override
	public String toString() {
		return name;
	}

}
