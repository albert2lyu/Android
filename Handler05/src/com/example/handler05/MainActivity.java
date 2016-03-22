package com.example.handler05;

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
import android.widget.TextView;
import android.os.Build;

/*
 * Handler学习例子
 * 计数器
 * @AUTHOR LANBOTS
 */
public class MainActivity extends Activity {

	private int num = 0;
	private TextView textView;

	// 生成一个Handler对像
	Handler handler = new Handler() {
		// 复写handlerMessage方法
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.arg1 == 1) {
				updateNum();
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Timer timer = new Timer();
		timer.schedule(new Tack(), 1, 1000);
	}

	private class Tack extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			// 定义Message对象
			Message msg = handler.obtainMessage();
			// 给Message对象赋值
			msg.arg1 = 1;
			handler.sendMessage(msg);

		}

	}

	private void updateNum() {
		textView = (TextView) findViewById(R.id.num);
		textView.setText(" " + num);
		num++;
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
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
