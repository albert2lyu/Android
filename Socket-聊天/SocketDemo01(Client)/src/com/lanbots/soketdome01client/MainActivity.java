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
	//����һ��Handler����
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {
				//��Message��ȡBundle���ݣ�������bundle
				Bundle bundle = msg.getData();
				show.append("server:" + bundle.getString("msg") + "\n");
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ���ؼ�Ԫ��
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
				// ��ȡ��������ݲ���ʾ��TextView
				input = inputMsg.getText().toString();
//				show.append("Client:" + input + "\n");

				// �����߳� ����������ͺͽ�����Ϣ
				new MyThread(input).start();
				// ��������
				inputMsg.setText("");

			}
		});

	}
	//�߳���
	public class MyThread extends Thread {
		private String input;

		public MyThread(String input) {
			this.input = input;
		}

		@Override
		public void run() {
			//����һ��bundle����
			Bundle bundle = new Bundle();
			//���Bundleӳ���е����б��������
			bundle.clear();
			try {
				// ���ӷ����� ���������ӳ�ʱΪ5��
				socket = new Socket("10.24.10.78", 8989);
				// �ֱ�ȡ������������
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(input);
				System.out.print(input);
				String str = dis.readUTF();
				System.out.println(str);
				//�Լ�ֵ�Է�ʽ�����������Ӧ�����ݣ���Ϣ��
				bundle.putString("msg", str);
				// ������Ϣ����
				Message msg = new Message();
				// ����Ϣ����ֵ
				msg.what = 1;
				//����Message��setData()���������ݣ�bundle�����в���
				msg.setData(bundle);
				// ������Ϣ 
				handler.sendMessage(msg);
				// �رո�����
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
