package com.example.testwifi;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

/*
 * 测试WIFI
 */
public class MainActivity extends Activity {

	private Button startButton;
	private Button stopButton;
	private Button checkButton;
	private WifiManager wifiManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.btn1);
		stopButton = (Button) findViewById(R.id.btn2);
		checkButton = (Button) findViewById(R.id.btn3);

		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) MainActivity.this
						.getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(true);
				//
				System.out.println("wifi state ---->"
						+ wifiManager.getWifiState());
				Toast.makeText(MainActivity.this,
						"当前WIFI网卡状态为：" + wifiManager.getWifiState(),
						Toast.LENGTH_LONG).show();
			}
		});

		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) MainActivity.this
						.getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(false);
				//
				System.out.println("wifi state ---->"
						+ wifiManager.getWifiState());
				Toast.makeText(MainActivity.this,
						"当前WIFI网卡状态为：" + wifiManager.getWifiState(),
						Toast.LENGTH_LONG).show();
			}
		});

		checkButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) MainActivity.this
						.getSystemService(Context.WIFI_SERVICE);
				System.out.println("wifi state ---->"
						+ wifiManager.getWifiState());
				Toast.makeText(MainActivity.this,
						"当前WIFI网卡状态为：" + wifiManager.getWifiState(),
						Toast.LENGTH_LONG).show();
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
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
