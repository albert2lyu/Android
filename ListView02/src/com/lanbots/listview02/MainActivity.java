package com.lanbots.listview02;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//定义Button对象
	private Button btn01;
	private Button btn02;
	private Button btn03;
	private Button btn04;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initViews();
	}
	
	//完成组件的初始化
	private void initViews(){
		btn01 = (Button)findViewById(R.id.btn01);
		btn02 = (Button)findViewById(R.id.btn02);
		btn03 = (Button)findViewById(R.id.btn03);
		btn04 = (Button)findViewById(R.id.btn04);
		btn01.setOnClickListener(listener);
		btn02.setOnClickListener(listener);
		btn03.setOnClickListener(listener);
		btn04.setOnClickListener(listener);
	}

	//OnClick事件
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			switch (view.getId()) {
			case R.id.btn01:
				intent.setClass(MainActivity.this, ListViewActivity01.class);
				break;
			case R.id.btn02:
				intent.setClass(MainActivity.this, ListViewActivity02.class);
				break;
			case R.id.btn03:
				intent.setClass(MainActivity.this, ListViewActivity03.class);
				break;
			case R.id.btn04:
				intent.setClass(MainActivity.this, ListViewActivity04.class);
				break;
			default:
				break;
			}
			startActivity(intent);
			
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
			finish();
		}
		return super.onOptionsItemSelected(item);
	}


}
