package com.lanbots.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	// ���嵱ǰ�̴߳����Socket
	private Socket socket = null;
	// ���߳��������Socket����Ӧ��������
	private BufferedReader bufferedReader = null;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		// ��ȡ��socket��Ӧ��������
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		String content = null;
		try {
			// ����ѭ�����ϵĴ�Socket�ж�ȡ�ͻ��˷��͹���������
			while ((content = readFromClient()) != null) {
				// ����socketList�е�ÿ��Socket
				// ����ȡ��������ÿ����Socket����һ��
				for (Socket socket : Server.socketList) {
					//��ȡ��socket��Ӧ�������
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write((content + "\n").getBytes("gbk"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �����ȡ�ͻ��˵���Ϣ
	public String readFromClient() {
		try {
			return bufferedReader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
