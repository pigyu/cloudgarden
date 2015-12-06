package com.windowgarden.app.activity;

import com.windowgarden.app.R;
import com.windowgarden.app.util.HttpCallbackListener;
import com.windowgarden.app.util.HttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlantOperatorActivity extends Activity implements OnClickListener{

	private float waterLevel;
	
	private float lightLevel;
	
	private String modifiedWaterLevel;
	
	private String modifiedLightLevel;
	
	private EditText waterLevelEditText;
	
	private EditText lightLevelEditText;
	
	private Button sendButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_plant_operator);
		waterLevel = getIntent().getFloatExtra("myWaterLevel", 0);
		lightLevel = getIntent().getFloatExtra("myLightLevel", 0);
		waterLevelEditText = (EditText) findViewById(R.id.modify_waterData);
		lightLevelEditText = (EditText) findViewById(R.id.modify_lightData);
		sendButton = (Button) findViewById(R.id.send);
		sendButton.setOnClickListener(this);
		waterLevelEditText.setHint((String.valueOf(waterLevel)));
		lightLevelEditText.setHint(String.valueOf(lightLevel));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.send:
			modifiedWaterLevel = waterLevelEditText.getText().toString();
			modifiedLightLevel = lightLevelEditText.getText().toString();
			
			
			//http://10.0.2.2:8080/cloudgarden/"
			//+ "CloudGardenServlet?id=1&waterLevel=36&lightLevel=36
			
		    String url = HttpUtil.BASE_URL + "id=" + MainActivity.id + "&expectedWaterLevel=" 
		    + modifiedWaterLevel + "&expectedLightLevel=" + modifiedLightLevel;
			
		    HttpUtil.sendDataWithHttpClient(url, new HttpCallbackListener() {
				
				@Override
				public void onFinish(final String response) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							if(Integer.parseInt(response) == 200) {
								Toast.makeText(PlantOperatorActivity.this, "修改数据成功", 
										Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(PlantOperatorActivity.this, "修改数据失败", 
										Toast.LENGTH_SHORT).show();
							}
						}
					});
				}
				
				@Override
				public void onError(Exception e) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(PlantOperatorActivity.this, "修改数据错误", 
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			});
			
			/*
			HttpUtil.postDataWithHttpURLConnection(url, MainActivity.id, modifiedWaterLevel, 
					modifiedLightLevel, new HttpCallbackListener() {
						
						@Override
						public void onFinish(final String responseCode) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									if(Integer.parseInt(responseCode) == 200) {
										Toast.makeText(PlantOperatorActivity.this, "修改数据成功", 
												Toast.LENGTH_SHORT).show();
									} else {
										Toast.makeText(PlantOperatorActivity.this, "修改数据失败", 
												Toast.LENGTH_SHORT).show();
									}
								}
							});
						}
						
						@Override
						public void onError(Exception e) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(PlantOperatorActivity.this, "修改数据错误", 
											Toast.LENGTH_SHORT).show();
								}
							});
						}
					});
				*/
		    
			break;
		default:
			break;
			
		}		
	 }	
}
