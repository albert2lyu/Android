package com.lanbots.soketdome01client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

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

	private Socket socket;
	private TextView show;
	private EditText inputMsg;
	private Button send;
	private String input;
	//生成一个Handler对象
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {
				//从Message提取Bundle数据，并赋给bundle
				Bundle bundle = msg.getData();
				show.append("server:" + bundle.getString("msg") + "\n");
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化控件元素
		initView();
	}

	private void initView() {
		show = (TextView) findViewById(R.id.show);
		inputMsg = (EditText) findViewById(R.id.message);
		send = (Button) findViewById(R.id.send);

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取输入空内容并显示在TextView
				input = inputMsg.getText().toString();
//				show.append("Client:" + input + "\n");

				// 启动线程 向服务器发送和接收信息
				new MyThread(input).start();
				// 清空输入空
				inputMsg.setText("");

			}
		});

	}
	//线程类
	public class MyThread extends Thread {
		private String input;

		public MyThread(String input) {
			this.input = input;
		}

		@Override
		public void run() {
			//定义一个bundle对象
			Bundle bundle = new Bundle();
			//清除Bundle映射中的所有保存的数据
			bundle.clear();
			try {
				// 连接服务器 并设置连接超时为5秒
				socket = new Socket("10.24.10.78", 8989);
				// 分别取得输入和输出流
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(input);
				System.out.print(input);
				String str = dis.readUTF();
				System.out.println(str);
				//以键值对方式保存服务器响应的数据（消息）
				bundle.putString("msg", str);
				// 定义消息对象
				Message msg = new Message();
				// 给消息对象赋值
				msg.what = 1;
				//调用Message的setData()方法对数据（bundle）进行操作
				msg.setData(bundle);
				// 发送消息 
				handler.sendMessage(msg);
				// 关闭各种流
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
