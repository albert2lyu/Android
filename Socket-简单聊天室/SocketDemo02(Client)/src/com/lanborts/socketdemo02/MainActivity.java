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

	// ��������ϵ������ı���
	private EditText input;
	private TextView show;
	// ��������ϵ�һ����ť
	private Button send;
	private Handler handler;
	// �����������ͨ�ŵ����߳�
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
				// �����Ϣ�������߳�
				if (msg.what == 1) {
					// ����ȡ������׷����ʾ���ı�����
					show.append("\n" + msg.obj.toString());
				}
			}
		};
		
		clientThread = new ClientThread(handler);
		// �ͻ�������ClientThread�̴߳����������ӡ���ȡ���Է�����������
		new Thread(clientThread).start();
		
		
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// ���û����°�ť֮�󣬽��û���������ݷ�װ��Message
					// Ȼ���͸����߳�Handler
					Message msg = new Message();
					msg.what = 1;
					msg.obj = input.getText().toString();
					clientThread.rHandler.sendMessage(msg);
					//��������
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
