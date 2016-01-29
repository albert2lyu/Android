package com.lanbots.activity01;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * 点击按钮，跳转到发短信的Activity
 */
public class ThirdActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private Button thirdBtn;
	private Button fourBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_activity);
		Log.i(TAG, "ThirdActivity-->onCreate");
		thirdBtn = (Button)findViewById(R.id.thirdBtn);
		fourBtn = (Button)findViewById(R.id.fourBtn);
		thirdBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("smsto:15622175484");
				Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
				intent.putExtra("lanbots", "Hello,Android!");
				startActivity(intent);
//				Intent intent  = new Intent();
//				intent.setClass(ThirdActivity.this, SendMsm.class);
//				startActivity(intent);
		}
		});
		
		fourBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String mobile = "15622175484";
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mobile));
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity-->onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity--onRestart>");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity--onResume>");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity--onPause>");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity--onStop>");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "ThirdActivity--onDestroy>");
		super.onDestroy();
	}


}
