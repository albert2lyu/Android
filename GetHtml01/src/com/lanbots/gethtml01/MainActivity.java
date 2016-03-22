package com.lanbots.gethtml01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lanbots.test02.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String uriString = "";
	private EditText edt;
	private Button searchButton;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (TextView) findViewById(R.id.show);
		// TextView������
		show.setMovementMethod(ScrollingMovementMethod.getInstance());
		searchButton = (Button) findViewById(R.id.search);
		searchButton.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			try {
				Thread thread = new Thread(runnable);
				thread.start();
			} catch (Exception e) {
				show.setText("���ִ���" + e.toString());
			}

		}
	};

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			getHtml();
		}
	};

	// ��ȡ��ҳHTMLs
	private String getHtml() {
		// ��ȡ������е��ַ�
		edt = (EditText) findViewById(R.id.edt);
		uriString = "http://" + edt.getText().toString();
		InputStreamReader in = null;
		StringBuffer sb = null;

		try {

			// ͨ�������ַ����URL����
			URL url = new URL(uriString);
			System.out.print(uriString);
			// �����ӣ�����ǿ������ת����Http���Ӷ���
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			// getInputStream����ȡ���ӵ�����
			// ʹ��InputStreamReader���ֽ���ת�����ַ���
			in = new InputStreamReader(urlConnection.getInputStream());
			// ����Ĵ����������������ϵ�����Ԥ����Ϣ
			BufferedReader buffer = new BufferedReader(in);
			// ����һ��StringBuffer���������洢������������
			sb = new StringBuffer();
			String htmlString = "";
			// ʹ��ѭ������ȡ��õ�����
			while ((htmlString = buffer.readLine()) != null) {
				Log.v("��ҳHTML", htmlString);
				sb.append(htmlString);
			}
			//��ʾ��ȡ��html
			show.setText(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
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
		return super.onOptionsItemSelected(item);
	}

}
