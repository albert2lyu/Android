package com.lanbots.net;

import java.net.ServerSocket;
import java.net.Socket;


public class MyService {
	
	public static void main(String[] args) {
		boolean bTrue=true;
		try{
			//ServerSocket可以定义服务器端的Socket
			ServerSocket svr = new ServerSocket(8989);//定义一个服务监听，监听的端口号是8989
			//侦听请求　　　　　　　　
			System.out.println("Server begin to start....");
			//svr.accept()返回的是某个与服务器连接的客户端的连接
			
			while(bTrue){
				Socket sk = svr.accept();//等待连接，只有有连接，下一行代码才会被执行
				Thread trThread = new Thread(new ServiceThread(sk));
				trThread.start();
			}
		     
		}catch(Exception ex){
			ex.printStackTrace();
		}
		

	}
	

}
