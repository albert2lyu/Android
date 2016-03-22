package com.lanbots.handler02;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Build;

/*
 * Handler使用的小例子
 * @AUTHR LANBOTS
 */
public class HandlerActivity extends Activity {

	private ProgressBar bar;
	private Button startBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bar = (ProgressBar) findViewById(R.id.bar);
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bar.setVisibility(View.VISIBLE);
				updateBarHandler.post(update_Thread);
			}
		});

	}
	
	//创建一个Handler对象，内部复写handleMessage()方法
	Handler updateBarHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
		bar.setProgress(msg.arg1);
		updateBarHandler.post(update_Thread);
		}
		
	};
	//线程类，该类使用匿名内部类的方式进行声明
	Runnable update_Thread = new Runnable() {
		int i = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			i = i + 10;
			//得到一个消息对象，Message类是由Android操作系统提供的
			Message msg = updateBarHandler.obtainMessage();
			//给消息对象赋值
			msg.arg1 = i;
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			updateBarHandler.sendMessage(msg);
			//当i=100时，把线程从队列中移除
			if( i == 100){
				updateBarHandler.removeCallbacks(update_Thread);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.handler, menu);
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
