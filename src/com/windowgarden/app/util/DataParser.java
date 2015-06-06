package com.windowgarden.app.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.windowgarden.app.database.PlantDatabase;
import com.windowgarden.app.model.PlantInfo;

public class DataParser {

	public static void parseJSONWithGSON(PlantDatabase plantDatabase, 
			String jsonData) {
		Gson gson = new Gson();
		//after parse, save it to database.
		List<PlantInfo> plantInfoList = gson.fromJson(jsonData, 
				new TypeToken<List<PlantInfo>>() {}.getType());
		for(PlantInfo plantInfo : plantInfoList) {
			plantDatabase.savePlantInfo(plantInfo);
		}
	}
}
