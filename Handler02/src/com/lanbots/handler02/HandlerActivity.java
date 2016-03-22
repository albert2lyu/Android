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
 * Handlerʹ�õ�С����
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
	
	//����һ��Handler�����ڲ���дhandleMessage()����
	Handler updateBarHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
		bar.setProgress(msg.arg1);
		updateBarHandler.post(update_Thread);
		}
		
	};
	//�߳��࣬����ʹ�������ڲ���ķ�ʽ��������
	Runnable update_Thread = new Runnable() {
		int i = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			i = i + 10;
			//�õ�һ����Ϣ����Message������Android����ϵͳ�ṩ��
			Message msg = updateBarHandler.obtainMessage();
			//����Ϣ����ֵ
			msg.arg1 = i;
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			updateBarHandler.sendMessage(msg);
			//��i=100ʱ�����̴߳Ӷ������Ƴ�
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
