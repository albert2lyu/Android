package com.lanbots.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	//定义ServerSocket的端口号
	private static final int SOCKET_PORT = 8989;
	// 定义保存所有的Socket
	public static List<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) {
		boolean bTrue = true;
		try{
			ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
			System.out.println("Server begin to start....");
			while( bTrue ){
				//侦听请求　　　　
				Socket socket = serverSocket.accept();
				socketList.add(socket);
				//每当客户端连接之后启动一条ServerThread线程为该客户端服务
				new Thread(new ServerThread(socket)).start();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
