package com.lanbots.gethtml03;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Data {
	
	public static byte[] isToData(InputStream is) throws IOException{ 

		//�ֽ������ 
		ByteArrayOutputStream bops = new ByteArrayOutputStream(); 
		//��ȡ���ݵĻ����� 
		byte buffer[] = new byte[1024]; 
		//��ȡ���� �ļ�¼ 
		int len = 0; 
		//ѭ����ȡ 
		while((len = is.read(buffer)) != -1){ 
		bops.write(buffer, 0, len); 
		} 
		//�Ѷ�ȡ������ת���� byte���� 
		byte data[] = bops.toByteArray(); 

		return data; 

		} 

}
