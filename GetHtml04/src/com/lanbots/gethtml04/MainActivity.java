package com.lanbots.gethtml04;

import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 输入网址，获取该网址的HTMl
 * 加入去除空格
 * @author LinBots
 *
 */
public class MainActivity extends Activity {

	private EditText edt;
	private TextView tv;
	private Button btn_get;
	private Button btn_save;
	private Context context = this;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String content = (String) msg.obj;
			// 设置滚动条
			tv.setMovementMethod(ScrollingMovementMethod.getInstance());
			tv.setText(content);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化布局元素
		initView();
	}

	// 初始化视图
	private void initView() {
		edt = (EditText) this.findViewById(R.id.edt);
		btn_get = (Button) this.findViewById(R.id.btn_get);
		btn_save = (Button) this.findViewById(R.id.btn_save);
		tv = (TextView) this.findViewById(R.id.tv);

		btn_get.setOnClickListener(listener);
		btn_save.setOnClickListener(listener);
	}

	// 去除输入框空格
	private boolean checkInput() {
		if (edt.getText().toString().trim().equals("")) {
			Toast.makeText(MainActivity.this, "输入网址", 1).show();
			return false;
		}
		return true;
	}


	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_get:
				if (checkInput()) {
					new Thread(new ConnectWeb(edt.getText().toString().trim(),
							handler)).start();
				}
				break;
			case R.id.btn_save:

				if (tv.getText().equals("")) {
					Toast.makeText(MainActivity.this, "请先抓取网站信息", Toast.LENGTH_LONG)
							.show();
					return;
				}
				saveFile();
				
				break;
			}
		}
	};
	
	//保存文件
	private void saveFile() {
		//获取URL输入的网址
		String str = edt.getText().toString();
		//截取域名，(注：如果用“.”，做分隔符的话，必须加上“\\”,即split("\\.")，而不能用split("."))
		String spStr[] = str.split("\\.");
		String fileName = spStr[1];
		// 获取文件内容
		String fileContent = tv.getText().toString();
		FileOutputStream out = null;
		try {  
			System.out.println("This is try!");
            out = context.openFileOutput(fileName,  Context.MODE_PRIVATE);
            out.write(fileContent.getBytes("UTF-8"));  
            Toast.makeText(MainActivity.this,
					"文件已保存在data/data/com.lanbots.gethtml04/files",
					Toast.LENGTH_LONG).show();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
                out.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
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
