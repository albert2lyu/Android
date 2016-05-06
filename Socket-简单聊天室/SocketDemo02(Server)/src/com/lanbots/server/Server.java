package com.lanbots.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	//����ServerSocket�Ķ˿ں�
	private static final int SOCKET_PORT = 8989;
	// ���屣�����е�Socket
	public static List<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) {
		boolean bTrue = true;
		try{
			ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
			System.out.println("Server begin to start....");
			while( bTrue ){
				//�������󡡡�����
				Socket socket = serverSocket.accept();
				socketList.add(socket);
				//ÿ���ͻ�������֮������һ��ServerThread�߳�Ϊ�ÿͻ��˷���
				new Thread(new ServerThread(socket)).start();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
