package com.example.handler04;
/*
 * Handler��Ϣ�����С���ӣ��������õ���Timer
 * @AUTHOR LANBOTS
 * @EMAIL lanbots@foxmail.com 
 */

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	
	private int num = 0;
	//����һ��Handler����,�ڲ���дhandleMessage()����
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				updateTitle();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(android.R.layout.simple_list_item_1);
		//����һ��Timer����
		Timer timer = new Timer();
		//����Timer�����schedule����
		timer.schedule(new MyTask(), 1, 3000);
	}
	
	class MyTask extends TimerTask{

		@Override
		public void run() {
			//�õ�һ����Ϣ����Message������Android����ϵͳ�ṩ��
			//Message msg = new Message();
			Message msg = handler.obtainMessage();
			//����Ϣ����ֵ
			msg.what = 1;
			//����Ϣ�����͵�Handler������
			handler.sendMessage(msg);
		}
		
	}
	
	//ͳ��ˢ�´���
	private void updateTitle(){
		setTitle("��ǰˢ�´�����" + num);
		num++;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
