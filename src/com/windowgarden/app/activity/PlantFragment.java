package com.windowgarden.app.activity;

import com.windowgarden.app.R;
import com.windowgarden.app.database.PlantDatabase;
import com.windowgarden.app.util.DataParser;
import com.windowgarden.app.util.HttpCallbackListener;
import com.windowgarden.app.util.HttpUtil;
import com.windowgarden.app.util.LogUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlantFragment extends Fragment implements OnClickListener{
	
	private TextView waterLevelText;
	private TextView lightLevelText;
	private Button refreshButton;
	private Button operatorButton;
	private TextView updateDateText;
	
	private float myWaterLevel;
	private float myLightLevel;
	private String myUpdateDate;
	
	private PlantDatabase plantDatabase;
	
	/*
	private Handler handler = new Handler() {
		
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case SHOW_RESPONSE:
				responseText.setText((String) msg.obj);
			}
		}
	};
	*/
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_plant, container, false);
		waterLevelText = (TextView) view.findViewById(R.id.waterData);
		lightLevelText = (TextView) view.findViewById(R.id.lightData);
		refreshButton = (Button) view.findViewById(R.id.refresh);
		operatorButton = (Button) view.findViewById(R.id.operator);
		updateDateText = (TextView) view.findViewById(R.id.update_date);
		refreshButton.setOnClickListener(this);
		operatorButton.setOnClickListener(this);
		
		if(!HttpUtil.isNetworkAvailable()) {
			Toast.makeText(getActivity(), "当前网络环境较差", 
					Toast.LENGTH_SHORT).show();
		}
		
		plantDatabase = PlantDatabase.getInstance();
		
		//在这里
		showPlantData();
	
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.refresh:
			refreshButton.setText("同步中...");
			queryFromServer(HttpUtil.URL_GET_DATA);
			break;
		case R.id.operator:
			Intent intent = new Intent(getActivity(), 
					PlantOperatorActivity.class);
			intent.putExtra("myWaterLevel", myWaterLevel);
			intent.putExtra("myLightLevel", myLightLevel);
			//startActivityForResult(intent, 1);
			startActivity(intent);
		default:
			break;
		}
	}
	
	/**
	 * 一般从prefs读，如果没有，访问server，server返回给LitePal数据库，
	 * 数据库存到prefs文件，在更新页面。
	 * 同时存在后台服务，有网络时定时更新。
	 */
	private void showPlantData() {
		SharedPreferences prefs = getActivity().
				getSharedPreferences("myPlantInfo" + MainActivity.id, 0);
		boolean isDataSaved = prefs.getBoolean("is_saved", false);
		if(isDataSaved) {
			myWaterLevel = prefs.getFloat("myWaterLevel", 0);
			myLightLevel = prefs.getFloat("myLightLevel", 0);
			myUpdateDate = prefs.getString("update_date", "");
			waterLevelText.setText(String.valueOf(myWaterLevel));
			lightLevelText.setText(String.valueOf(myLightLevel));
			updateDateText.setText(myUpdateDate);
		} else {
			queryFromServer(HttpUtil.URL_GET_DATA);
		}
	}
	
	/**
	 * 发送Http请求
	 * 接收返回的JSON数据，并用GSON技术解析成很多PlantInfo对象
	 * 再全部存储到数据库PlantInfo表中（之前已通过getInstance方法取得单例db）
	 * 最后再传入特定id，query到特定行，将该用户数据存储到易访问的sharedPrefs文件中
	 * 
	 * @param address 所要访问的Tomcat服务器的地址
	 */
	private void queryFromServer(final String address) {
		HttpUtil.sendAnotherHttpRequest(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				
				LogUtil.d("PlantFragement", "success.......");
				
				//parse JSON-data, write them to local database(format-ly, not object-ly)
				DataParser.parseJSONWithGSON(plantDatabase, response);
				//save to SharedPrefs
				plantDatabase.saveToSharedPrefs(MainActivity.id);
				
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						showPlantData();
						refreshButton.setText("同步数据");
					}
				});
			}
			
			@Override
			public void onError(Exception e) {
				
				LogUtil.d("PlantFragement", "fail.......");
				
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//DialogDisplayUtil.showAlertDialog("连接服务器失败");
						waterLevelText.setText("unknown");
						waterLevelText.setTextSize(14);
						lightLevelText.setText("unknown");
						lightLevelText.setTextSize(14);
						updateDateText.setText("2015年12月11日");
						refreshButton.setText("同步数据");
						Toast.makeText(getActivity(), "同步失败", 
								Toast.LENGTH_SHORT).show();
					}
				});     
			}
		});
	}
}
