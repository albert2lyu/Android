package com.example.handler04;
/*
 * Handler消息处理的小例子，该例子用到了Timer
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
	//生成一个Handler对象,内部复写handleMessage()方法
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
		//创建一个Timer对象
		Timer timer = new Timer();
		//调用Timer对象的schedule方法
		timer.schedule(new MyTask(), 1, 3000);
	}
	
	class MyTask extends TimerTask{

		@Override
		public void run() {
			//得到一个消息对象，Message类是由Android操作系统提供的
			//Message msg = new Message();
			Message msg = handler.obtainMessage();
			//给消息对象赋值
			msg.what = 1;
			//将消息对象传送到Handler对象中
			handler.sendMessage(msg);
		}
		
	}
	
	//统计刷新次数
	private void updateTitle(){
		setTitle("当前刷新次数：" + num);
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
