package com.lanbots.activity01;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

/*
 * 1、Activity的生命周期的测试
 * 2、Intent的测试
 * 3、Menu测试
 * @AUTHOR LANBOTS
 */
public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private Button firstBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(TAG, "MainActivity-->onCreate");
		
		firstBtn = (Button)findViewById(R.id.firstBtn);
		firstBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "MainActivity-->onDestroy");
		super.onDestroy();
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
		if(id == R.id.action_theme){
			Toast.makeText(this, "This is Theme Menu", Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}

}
