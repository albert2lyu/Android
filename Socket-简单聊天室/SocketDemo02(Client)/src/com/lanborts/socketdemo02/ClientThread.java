package com.lanborts.socketdemo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ClientThread implements Runnable {
	private Socket socket;
	// 定义向UI线程发送消息的Handler对象
	private Handler handler;
	// 定义接收UI线程的Handler对象
	public Handler rHandler;
	// 该线程处理Socket所对用的输入输出流
	private BufferedReader bufferedReader = null;
	private OutputStream outputStream = null;

	public ClientThread(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void run() {
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("10.24.10.78", 8989), 8000);
			//分别获取输入输出流
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = socket.getOutputStream();
			// 启动一条子线程来读取服务器相应的数据
			new Thread() {

				@Override
				public void run() {
					String content = null;
					// 不断的读取Socket输入流的内容
					try {
						while ((content = bufferedReader.readLine()) != null) {
							// 每当读取到来自服务器的数据之后，发送的消息通知程序
							// 界面显示该数据
							Message msg = new Message();
							msg.what = 1;
							msg.obj = content;
							handler.sendMessage(msg);
						}
					} catch (IOException io) {
						io.printStackTrace();
					}
				}

			}.start();
			// 为当前线程初始化Looper
			Looper.prepare();
			// 创建revHandler对象
			rHandler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// 接收到UI线程的中用户输入的数据
					if (msg.what == 1) {
						// 将用户在文本框输入的内容写入网络
						try {
							outputStream.write((msg.obj.toString() + "\r\n").getBytes("gbk"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			};
			// 启动Looper
			Looper.loop();

		} catch (SocketTimeoutException e) {
			Message msg = new Message();
			msg.what = 1;
			msg.obj = "网络连接超时！";
			handler.sendMessage(msg);
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
}
