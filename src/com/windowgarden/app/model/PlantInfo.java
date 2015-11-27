package com.windowgarden.app.model;

import org.litepal.crud.DataSupport;

public class PlantInfo extends DataSupport{

	private long id;
	
	private float waterLevel;
	
	private float lightLevel;
	
	private float expectedWaterLevel;
	
	private float expectedLightLevel;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setWaterLevel(float waterLevel) {
		this.waterLevel = waterLevel;
	}
	
	public float getWaterLevel() {
		return waterLevel;
	}
	
	public void setLightLevel(float lightLevel) {
		this.lightLevel = lightLevel;
	}
	
	public float getLightLevel() {
		return lightLevel;
	}

	public float getExpectedWaterLevel() {
		return expectedWaterLevel;
	}

	public void setExpectedWaterLevel(float expectedWaterLevel) {
		this.expectedWaterLevel = expectedWaterLevel;
	}

	public float getExpectedLightLevel() {
		return expectedLightLevel;
	}

	public void setExpectedLightLevel(float expectedLightLevel) {
		this.expectedLightLevel = expectedLightLevel;
	}
}
