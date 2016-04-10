package com.lanbots.xml01;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.security.auth.x500.X500Principal;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Xml;

public class ConnectWeb implements Runnable {

	private String httpUri;
	private List data;
	private String weatherIcon = "http://m.weather.com.cn/img/c";
	/**
	 * 以下代码不理解
	 * 
	 * @param httpUri
	 * @param data
	 */
	private Vector<Bitmap> bitmap = new Vector<Bitmap>();
	private Vector<String> icon = new Vector<String>();
	private Vector<String> weatherInfo = new Vector<String>();

	// 创建一个构造方法
	public ConnectWeb(String httpUri, List data) {
		// TODO Auto-generated constructor stub
		this.httpUri = httpUri;
		this.data = data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStreamReader isr = connectWeb();
		try{
			//解析XML
			parse(isr);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	//从网络端获取XML文件流
	public InputStreamReader connectWeb() {

		InputStreamReader isr = null;
		try {
			// 生成一个Uri对象
			URL url = new URL(httpUri);
			// 打开连接，并强制类型转换成Http连接对象
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 调用httpURLConnection对象的getInputStream方法，读取连接的数据
			// 使用InputStreamReader将字节流转换成字符流
			isr = new InputStreamReader(httpURLConnection.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isr;

	}
	
	public void parse(InputStreamReader isr) throws Exception{
		
		String tempWeatherInfo;
		//获得XmlPullParser解析器
		XmlPullParser xpp = Xml.newPullParser();
		xpp.setInput(isr);
		int evenType = xpp.getEventType();
		while( evenType != XmlPullParser.END_DOCUMENT){
			if( evenType == XmlPullParser.START_TAG){
				String tag = xpp.getName();
				//如果是city标签，则说明要实例化对象
				if( tag.equalsIgnoreCase("city")){
					//城市天气预报
					tempWeatherInfo = xpp.getAttributeValue(null, "cityname");
					tempWeatherInfo = tempWeatherInfo + "  " + xpp.getAttributeValue(null, "stateDetailed");
					icon.addElement(weatherIcon + xpp.getAttributeValue(null, "state1") + ".gif");
					Map map = new HashMap();// 定义一个HashMap对象

					map.put("info", tempWeatherInfo);
					// map.put("imge", R.drawable.ic_launcher );
					map.put("imge", getBitmap(weatherIcon + xpp.getAttributeValue(null, "state1") + ".gif"));
					data.add(map);
				}
				
			}
			evenType = xpp.next();
		}
		
		
	}
	

	public Bitmap getBitmap(String iconUrl) {
		HttpURLConnection httpConn = null;
		InputStream din = null;
		try {
			URL url = new URL(iconUrl);
			// System.out.println(icon.elementAt(i));
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("GET");
			din = httpConn.getInputStream();
			return BitmapFactory.decodeStream(din);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// 释放连接
			try {
				din.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
