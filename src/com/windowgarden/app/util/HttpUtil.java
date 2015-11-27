package com.windowgarden.app.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {

	//192.168.0.108 
	//10.0.2.2
	
	public static final String BASE_URL = "http://192.168.43.160:8080/"
			+ "cloudgarden/CloudGardenServlet?";
	public static final String URL_GET_DATA = "http://192.168.43.160:8080/"
			+ "cloudgarden/CloudGardenSecondServlet";
	
	public static void sendDataWithHttpClient(final String address, final 
			HttpCallbackListener listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(address);
				try {
					HttpResponse httpResponse = httpClient.execute(httpGet);
					listener.onFinish(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(listener != null) {
						listener.onError(e);					
					}
				}
			}
		}).start();
	}
	
	public static void sendAnotherHttpRequest(final String address, final 
			HttpCallbackListener listener) {
		new Thread(new Runnable() {
	
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					
					int code = 0;
 					
					URL url = new URL(address);
			        connection = (HttpURLConnection) url.openConnection();
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setRequestMethod("GET");
					connection.setDoInput(true);
					connection.setDoOutput(true);
					code = connection.getResponseCode();
					LogUtil.d("HttpUtil", String.valueOf(code));
					if(code == 200) {
						InputStream inputStream = connection.getInputStream();
						String jsonString = changeInputStream(inputStream);
						listener.onFinish(jsonString);
					}
				} catch (IOException e) {
					if(listener != null) {
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
	
	protected static String changeInputStream(InputStream inputStream) {
		String jsonString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int length = 0;
		byte[] buffer = new byte[1024];
		try {
			while((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			jsonString = new String(outputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

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
