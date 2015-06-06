package com.windowgarden.app.model;

import org.litepal.crud.DataSupport;

public class PlantInfo extends DataSupport{

	private long id;
	
	private float waterLevel;
	
	private float lightLevel;
	
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
}
