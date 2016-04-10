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
	 * ���´��벻���
	 * 
	 * @param httpUri
	 * @param data
	 */
	private Vector<Bitmap> bitmap = new Vector<Bitmap>();
	private Vector<String> icon = new Vector<String>();
	private Vector<String> weatherInfo = new Vector<String>();

	// ����һ�����췽��
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
			//����XML
			parse(isr);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	//������˻�ȡXML�ļ���
	public InputStreamReader connectWeb() {

		InputStreamReader isr = null;
		try {
			// ����һ��Uri����
			URL url = new URL(httpUri);
			// �����ӣ���ǿ������ת����Http���Ӷ���
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// ����httpURLConnection�����getInputStream��������ȡ���ӵ�����
			// ʹ��InputStreamReader���ֽ���ת�����ַ���
			isr = new InputStreamReader(httpURLConnection.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isr;

	}
	
	public void parse(InputStreamReader isr) throws Exception{
		
		String tempWeatherInfo;
		//���XmlPullParser������
		XmlPullParser xpp = Xml.newPullParser();
		xpp.setInput(isr);
		int evenType = xpp.getEventType();
		while( evenType != XmlPullParser.END_DOCUMENT){
			if( evenType == XmlPullParser.START_TAG){
				String tag = xpp.getName();
				//�����city��ǩ����˵��Ҫʵ��������
				if( tag.equalsIgnoreCase("city")){
					//��������Ԥ��
					tempWeatherInfo = xpp.getAttributeValue(null, "cityname");
					tempWeatherInfo = tempWeatherInfo + "  " + xpp.getAttributeValue(null, "stateDetailed");
					icon.addElement(weatherIcon + xpp.getAttributeValue(null, "state1") + ".gif");
					Map map = new HashMap();// ����һ��HashMap����

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
			// �ͷ�����
			try {
				din.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
