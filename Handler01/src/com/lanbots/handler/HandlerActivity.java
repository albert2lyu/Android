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
 * Handler使用小测试
 * @AUTHOR LANBOTS
 */
public class HandlerActivity extends Activity {

	private TextView txt1;
	private Button startBtn;
	private Button endBtn;
	
	//创建Handler对象
	Handler hander = new Handler();
	//定义runnable接口
	Runnable update_thread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//线程每次执行输出Hello,lanbots! 
			//TextView 的 append功能不会覆盖前面的输出
			txt1.append("\nHello,lanbots!");
			//延迟2秒后又将线程加入到线程队列中
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
				//将线程接口立即送到线程队列中
				hander.post(update_thread);
			}
		});
		
		endBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//将线程接口从线程队列中移除
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
