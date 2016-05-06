package com.lanborts.socketdemo02;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	// 定义界面上的两个文本框
	private EditText input;
	private TextView show;
	// 定义界面上的一个按钮
	private Button send;
	private Handler handler;
	// 定义与服务器通信的子线程
	private ClientThread clientThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		input = (EditText) findViewById(R.id.inputMsg);
		show = (TextView) findViewById(R.id.show);
		send = (Button) findViewById(R.id.send);
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// 如果消息来自子线程
				if (msg.what == 1) {
					// 将读取的内容追加显示在文本框中
					show.append("\n" + msg.obj.toString());
				}
			}
		};
		
		clientThread = new ClientThread(handler);
		// 客户端启动ClientThread线程创建网络连接、读取来自服务器的数据
		new Thread(clientThread).start();
		
		
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// 当用户按下按钮之后，将用户输入的数据封装成Message
					// 然后发送给子线程Handler
					Message msg = new Message();
					msg.what = 1;
					msg.obj = input.getText().toString();
					clientThread.rHandler.sendMessage(msg);
					//清空输入框
					input.setText("");

				} catch (Exception e) {

				}
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
