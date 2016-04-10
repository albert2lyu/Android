package com.lanbots.gethtml04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;

public class ConnectWeb implements Runnable {

	private String url;
	private Handler handler;

	public ConnectWeb(String url, Handler handler) {
		this.url = url;
		this.handler = handler;
	}

	@Override
	public void run() {
		InputStreamReader isr = getHttp();
		String content = handleStream(isr);
		Message msg = new Message().obtain();
		msg.obj = content;
		handler.sendMessage(msg);
	}

	private String handleStream(InputStreamReader isr) {
		String content = null;
		try {
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String len = "";
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}
			content = sb.toString();
			br.close();
			isr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	private InputStreamReader getHttp() {
		InputStreamReader isr = null;
		try {
			System.out.println(url);
			URL mUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			isr = new InputStreamReader(conn.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isr;
	}

}
