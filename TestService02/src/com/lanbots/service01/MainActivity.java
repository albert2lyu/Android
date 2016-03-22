package com.lanbots.service01;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

/*
 * Service学习练习
 * 利用Service播放MP3
 * @AUTHOR LANBOTS
 */
public class MainActivity extends Activity {
	
	private Button strat;
	private Button stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		strat = (Button)findViewById(R.id.strat);
		stop = (Button)findViewById(R.id.stop);
		strat.setOnClickListener(listener);
		stop.setOnClickListener(listener);

	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ServiceActivity.class);
			switch (view.getId()) {
			case R.id.strat:
				startService(intent);//开始播放
				break;
			case R.id.stop:
				stopService(intent);//暂停播放
				break;
			default:
				break;
			}
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
