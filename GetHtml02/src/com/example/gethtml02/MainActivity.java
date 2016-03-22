package com.example.gethtml02;

import com.example.test03.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// 声明控件
	public EditText et_url;
	public TextView tv_ie;
	// 网路操作类
	public NetWorkUtils netWorkUtils;
	private Handler handler;
	public String content;
	public static final int TEXT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取et_url对象
		et_url = (EditText) findViewById(R.id.et_url);
		tv_ie = (TextView) findViewById(R.id.tv_ie);
		// 实例化
		netWorkUtils = new NetWorkUtils(this);
		// 实例化这个处理者
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case TEXT:
					tv_ie.setText(content);// 设置显示的文本
					break;
				default:
					break;
				}
			}
		};
	}

	public void sendHttp(View v) {
		// 设置网络
		boolean flag = netWorkUtils.setActiveNetWork();
		if (flag) {
			// run方法 执行完毕 这个线程就消耗了
			// 子线程
			new Thread(new Runnable() {
				@Override
				public void run() {
					send();
					handler.sendEmptyMessage(TEXT);
				}
			}).start();
		}
	}

	// 发送请求操作
	@SuppressLint("NewApi")
	public void send() {

		// 获取url的path路径
		String path = et_url.getText().toString();
		if ( path.isEmpty() ) {
			Toast.makeText(MainActivity.this, "访问 的url地址不能为空",
					Toast.LENGTH_LONG).show();
		} else {
			content = HttpUtils.sendGet(path);
		}
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
