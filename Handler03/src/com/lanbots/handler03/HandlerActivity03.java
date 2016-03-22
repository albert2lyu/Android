package com.lanbots.handler03;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

/*
 * ����Handler�͵�������Activity�Ǵ���ͬһ�̵߳�
 * @AUTHOR LANBOTS
 */
public class HandlerActivity03 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler.post(r);
		setContentView(R.layout.main);
		
		// ��ӡ��ǰ�̵߳�ID��NAME
		System.out.println("activityID-->" + Thread.currentThread().getId());
		System.out.println("activityNAME-->" + Thread.currentThread().getName());

	}

	// ����һ��Handler����
	Handler handler = new Handler();
	// ����Runnable�ӿ�
	Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// ��ӡ��ǰ�̵߳�ID��NAME
			System.out.println("handlerID-->" + Thread.currentThread().getId());
			System.out.println("handlerNAME-->"
					+ Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.handler_activity03, menu);
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
