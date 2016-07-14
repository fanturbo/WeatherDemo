package com.test.weatherdemo.beans;

import java.util.List;

public class City {
	private String id;
	private String name;
	private List<District> disList;

	public City() {
	}

	public City(String id, String name, List<District> disList) {
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

	public void setDisList(List<District> disList) {
		this.disList = disList;
	}

	@Override
	public String toString() {
		return name;
	}

}
