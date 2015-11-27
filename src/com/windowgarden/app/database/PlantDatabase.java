package com.windowgarden.app.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import com.windowgarden.app.model.PlantInfo;
import com.windowgarden.app.util.MyApplication;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * 封装一些常用的数据库操作
 * 
 * @author pigyu
 *
 */

public class PlantDatabase {

	private static PlantDatabase plantDatabase;
	
	@SuppressWarnings("unused")
	private SQLiteDatabase db;
	
	public PlantInfo myPlantInfo;
	
	/**
	 * 构造方法私有化
	 */
	private PlantDatabase() {
		db = Connector.getDatabase();
	}
	
	/**
	 * 获取PlantDatabase的实例（单例）
	 */
	public synchronized static PlantDatabase getInstance() {
		if(plantDatabase == null) {
			plantDatabase = new PlantDatabase();
		}
		return plantDatabase;
	}

	public void savePlantInfo(PlantInfo plantInfo) {			
		//第一次 ， save.
		//plantInfo.save();
		//第二次，update.
		plantInfo.update(plantInfo.getId());
	}
	
	/*
	private int loadNum() {
		FileInputStream in = null;
		BufferedReader reader = null;
		String line = "0";
		try {
			in = MyApplication.getContext().openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			line = reader.readLine();
		} catch (FileNotFoundException e) {
			//create the "data" file, 
			//and return.
			addNum(null);
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			addNum(line);
		}
		return Integer.parseInt(line);
	}
	*/

	/*
	private void addNum(String num) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = MyApplication.getContext().openFileOutput("data", 
					MyApplication.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
	
			if(num != null) {
				writer.write(String.valueOf(Integer.parseInt(num) + 1));
			} else {
				writer.write("1");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	*/
	
	public void saveToSharedPrefs(String id) {
		SharedPreferences.Editor editor = MyApplication.getContext().
				getSharedPreferences("myPlantInfo" + id, 0).edit();
		if(!TextUtils.isEmpty(id)) {
			 long longId = Long.parseLong(id);
			 myPlantInfo = DataSupport.find(PlantInfo.class, longId);
		}
		editor.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
		editor.putLong("myId", myPlantInfo.getId());
		System.out.println("" + myPlantInfo.getWaterLevel());
		editor.putFloat("myWaterLevel", myPlantInfo.getWaterLevel());
		editor.putFloat("myLightLevel", myPlantInfo.getLightLevel());
		editor.putFloat("myExpectedWaterLevel", myPlantInfo.getExpectedWaterLevel());
		editor.putFloat("myExpectedLightLevel", myPlantInfo.getExpectedLightLevel());
		editor.putString("update_date", sdf.format(new Date()));
		editor.putBoolean("is_saved", true);
		editor.commit();
	}
	
}
