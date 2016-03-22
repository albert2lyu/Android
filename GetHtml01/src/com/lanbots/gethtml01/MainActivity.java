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
		// TextView滚动条
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
				show.setText("出现错误" + e.toString());
			}

		}
	};

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			getHtml();
		}
	};

	// 获取网页HTMLs
	private String getHtml() {
		// 获取输入框中的字符
		edt = (EditText) findViewById(R.id.edt);
		uriString = "http://" + edt.getText().toString();
		InputStreamReader in = null;
		StringBuffer sb = null;

		try {

			// 通过网络地址创建URL对象
			URL url = new URL(uriString);
			System.out.print(uriString);
			// 打开链接，并且强制类型转换成Http连接对象
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			// getInputStream，读取连接的数据
			// 使用InputStreamReader将字节流转换成字符流
			in = new InputStreamReader(urlConnection.getInputStream());
			// 下面的代码可以完整输出网上的天气预报信息
			BufferedReader buffer = new BufferedReader(in);
			// 生成一个StringBuffer对象，用来存储读出来的数据
			sb = new StringBuffer();
			String htmlString = "";
			// 使用循环来读取获得的数据
			while ((htmlString = buffer.readLine()) != null) {
				Log.v("网页HTML", htmlString);
				sb.append(htmlString);
			}
			//显示获取的html
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
