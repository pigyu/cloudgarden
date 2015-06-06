package com.windowgarden.app.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {

	public static final String BASE_URL = "http://localhost/WindowGardenServer/";
	
	public static void sendHttpRequest(final String address, final
			HttpCallbackListener listener) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null) {
						response.append(line);
					}
					if(listener != null) {
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if(listener != null) {
						//e.printStackTrace();
						listener.onError(e);
					}
				} finally {
					if(connection != null) {
						connection.disconnect();
					}
			    }
			}
		}).start();
	}

	/**
	 * 可以在后期换用网上的Map容器，增强代码块的复用性
	 */
	public static void postDataWithHttpURLConnection(String address, String id,
			String waterLevel, String lightLevel, HttpCallbackListener listener) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(address);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			String stringData = "id=" + URLEncoder.encode(id, "UTF-8") + "&waterLevel=" + 
					URLEncoder.encode(waterLevel, "UTF-8") + "&lightLevel=" + 
							URLEncoder.encode(lightLevel, "UTF-8");
			byte[] data = stringData.getBytes();
			
			connection.setRequestProperty("Content-Type", 
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", 
					String.valueOf(data.length));
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(data);
			out.flush();
			out.close();
			int responseCode = connection.getResponseCode();
			listener.onFinish(String.valueOf(responseCode));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			listener.onError(e);
		} finally {
			if(connection != null) {
				connection.disconnect();
			}
		}
	}
	
	/*
	public static void postDataWithHttpURLConnection(String address, String id,
			String waterLevel, String lightLevel) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(address);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", 
					"application/x-www-form-urlencoded");
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			String data = "id=" + URLEncoder.encode(id, "UTF-8") + "&waterLevel=" + 
			URLEncoder.encode(waterLevel, "UTF-8") + "&lightLevel=" + 
					URLEncoder.encode(lightLevel, "UTF-8");
			out.writeBytes(data);
			out.flush();
			out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.disconnect();
			}
		}
	}
	*/
	
	public static boolean isNetworkAvailable() {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) 
				MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}
}
