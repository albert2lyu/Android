package com.lanbots.handler;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;
/*
 * Handlerʹ��С����
 * @AUTHOR LANBOTS
 */
public class HandlerActivity extends Activity {

	private TextView txt1;
	private Button startBtn;
	private Button endBtn;
	
	//����Handler����
	Handler hander = new Handler();
	//����runnable�ӿ�
	Runnable update_thread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//�߳�ÿ��ִ�����Hello,lanbots! 
			//TextView �� append���ܲ��Ḳ��ǰ������
			txt1.append("\nHello,lanbots!");
			//�ӳ�2����ֽ��̼߳��뵽�̶߳�����
			hander.postDelayed(update_thread, 2000);
		}
	};
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txt1 = (TextView)findViewById(R.id.txt1);
		startBtn = (Button)findViewById(R.id.startBtn);
		endBtn = (Button)findViewById(R.id.endBtn);
		
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//���߳̽ӿ������͵��̶߳�����
				hander.post(update_thread);
			}
		});
		
		endBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//���߳̽ӿڴ��̶߳������Ƴ�
				hander.removeCallbacks(update_thread);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
