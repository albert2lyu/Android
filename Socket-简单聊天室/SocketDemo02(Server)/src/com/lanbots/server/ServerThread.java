package com.lanbots.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	// 定义当前线程处理的Socket
	private Socket socket = null;
	// 该线程所处理的Socket所对应的输入流
	private BufferedReader bufferedReader = null;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		// 获取该socket对应的输入流
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		String content = null;
		try {
			// 采用循环不断的从Socket中读取客户端发送过来的数据
			while ((content = readFromClient()) != null) {
				// 遍历socketList中的每个Socket
				// 将读取到的内容每个向Socket发送一次
				for (Socket socket : Server.socketList) {
					//获取该socket对应的输出流
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write((content + "\n").getBytes("gbk"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 定义读取客户端的信息
	public String readFromClient() {
		try {
			return bufferedReader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
