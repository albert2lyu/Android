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

	// �����ؼ�
	public EditText et_url;
	public TextView tv_ie;
	// ��·������
	public NetWorkUtils netWorkUtils;
	private Handler handler;
	public String content;
	public static final int TEXT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ȡet_url����
		et_url = (EditText) findViewById(R.id.et_url);
		tv_ie = (TextView) findViewById(R.id.tv_ie);
		// ʵ����
		netWorkUtils = new NetWorkUtils(this);
		// ʵ�������������
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case TEXT:
					tv_ie.setText(content);// ������ʾ���ı�
					break;
				default:
					break;
				}
			}
		};
	}

	public void sendHttp(View v) {
		// ��������
		boolean flag = netWorkUtils.setActiveNetWork();
		if (flag) {
			// run���� ִ����� ����߳̾�������
			// ���߳�
			new Thread(new Runnable() {
				@Override
				public void run() {
					send();
					handler.sendEmptyMessage(TEXT);
				}
			}).start();
		}
	}

	// �����������
	@SuppressLint("NewApi")
	public void send() {

		// ��ȡurl��path·��
		String path = et_url.getText().toString();
		if ( path.isEmpty() ) {
			Toast.makeText(MainActivity.this, "���� ��url��ַ����Ϊ��",
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
