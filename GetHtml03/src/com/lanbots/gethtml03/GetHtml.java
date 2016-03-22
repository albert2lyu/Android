package com.lanbots.gethtml03;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHtml {
	
	public static String getHtml(String path) { 
		String content = null; 
		try { 
		// ���÷���url 
		URL url = new URL(path); 
		// ������ 
		HttpURLConnection httpURLConnection = (HttpURLConnection) url 
		.openConnection(); 
		// �����������Ϣ 
		httpURLConnection.setRequestMethod("GET"); 
		// ���������Ƿ�ʱʱ�� 
		httpURLConnection.setConnectTimeout(5000); 
		// �жϷ������Ƿ���Ӧ�ɹ� 
		if (httpURLConnection.getResponseCode() == 200) { 
		// ��ȡ��Ӧ������������ 
		InputStream is = httpURLConnection.getInputStream(); 
		byte data[] = Data.isToData(is); 
		// ��ת�����ַ��� 
		content = new String(data); 
		// ���ݱ��뷽ʽ 
		if (content.contains("gb2312")) { 
		content = new String(data, "gb2312"); 
		} 
		} 
		// �Ͽ����� 
		httpURLConnection.disconnect(); 
		} catch (MalformedURLException e) { 
		e.printStackTrace(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return content; 
		} 


}
