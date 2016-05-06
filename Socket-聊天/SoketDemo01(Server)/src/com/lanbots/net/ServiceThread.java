package com.lanbots.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServiceThread implements Runnable {

	private Socket socket;
	private static final String MSG = "�㷢�͵���Ϣ�ǣ�";

	public ServiceThread(Socket socket)  {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// �ֱ�ȡ������������
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String getStr = dis.readUTF();
			System.out.println(getStr);
			dos.writeUTF(MSG + getStr);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
