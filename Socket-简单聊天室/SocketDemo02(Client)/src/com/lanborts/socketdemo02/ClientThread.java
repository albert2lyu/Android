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
	// ������UI�̷߳�����Ϣ��Handler����
	private Handler handler;
	// �������UI�̵߳�Handler����
	public Handler rHandler;
	// ���̴߳���Socket�����õ����������
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
			//�ֱ��ȡ���������
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = socket.getOutputStream();
			// ����һ�����߳�����ȡ��������Ӧ������
			new Thread() {

				@Override
				public void run() {
					String content = null;
					// ���ϵĶ�ȡSocket������������
					try {
						while ((content = bufferedReader.readLine()) != null) {
							// ÿ����ȡ�����Է�����������֮�󣬷��͵���Ϣ֪ͨ����
							// ������ʾ������
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
			// Ϊ��ǰ�̳߳�ʼ��Looper
			Looper.prepare();
			// ����revHandler����
			rHandler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// ���յ�UI�̵߳����û����������
					if (msg.what == 1) {
						// ���û����ı������������д������
						try {
							outputStream.write((msg.obj.toString() + "\r\n").getBytes("gbk"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			};
			// ����Looper
			Looper.loop();

		} catch (SocketTimeoutException e) {
			Message msg = new Message();
			msg.what = 1;
			msg.obj = "�������ӳ�ʱ��";
			handler.sendMessage(msg);
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
}
