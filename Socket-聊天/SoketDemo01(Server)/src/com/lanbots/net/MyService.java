package com.lanbots.net;

import java.net.ServerSocket;
import java.net.Socket;


public class MyService {
	
	public static void main(String[] args) {
		boolean bTrue=true;
		try{
			//ServerSocket���Զ���������˵�Socket
			ServerSocket svr = new ServerSocket(8989);//����һ����������������Ķ˿ں���8989
			//�������󡡡�������������
			System.out.println("Server begin to start....");
			//svr.accept()���ص���ĳ������������ӵĿͻ��˵�����
			
			while(bTrue){
				Socket sk = svr.accept();//�ȴ����ӣ�ֻ�������ӣ���һ�д���Żᱻִ��
				Thread trThread = new Thread(new ServiceThread(sk));
				trThread.start();
			}
		     
		}catch(Exception ex){
			ex.printStackTrace();
		}
		

	}
	

}
