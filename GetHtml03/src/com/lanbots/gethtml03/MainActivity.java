package com.lanbots.gethtml03;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int TEXT = 1;
	private String content;
	private String uriString = "";
	private EditText edt;
	private Button searchButton;
	private Button saveHtmlButton;
	private Button readHtmlButton;
	private TextView show;
	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (TextView) findViewById(R.id.show);
		edt = (EditText) findViewById(R.id.edt);
		searchButton = (Button) findViewById(R.id.search);
		saveHtmlButton = (Button) findViewById(R.id.saveHtml);
		readHtmlButton = (Button)findViewById(R.id.readHtml);
		searchButton.setOnClickListener(listener);
		saveHtmlButton.setOnClickListener(listener);
		readHtmlButton.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {
		;
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (arg0 == searchButton) {
				try {
					Thread thread = new Thread(runnable);
					thread.start();
				} catch (Exception e) {
					show.setText("出现错误" + e.toString());
				}

			} else if (arg0 == saveHtmlButton) {
				//保存的文件名为你输入的网址，例如输入www.baidu.com,保存的文件名即为：www.baidu.com
				String filename = edt.getText().toString();
				String filecontent = content;
				FileOutputStream out = null;
				// 存储到手机内存中
				// 保存的文件在data-->data-->com.lanbots.gethtml03-->files中

				try {
					out = context
							.openFileOutput(filename, Context.MODE_PRIVATE);
					out.write(filecontent.getBytes("UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else if(arg0 == readHtmlButton){
				//读取HTML,在输入框输入保存的文件名，即可查看保存为该文件名的HTML
				String filename = edt.getText().toString(); //获得读取的文件的名称（这里为输入的网址）  
                FileInputStream in = null;  
                ByteArrayOutputStream bout = null;  
                byte[]buf = new byte[1024];  
                bout = new ByteArrayOutputStream();  
                int length = 0;  
                try {  
                    in = context.openFileInput(filename); //获得输入流  
                    while((length=in.read(buf))!=-1){  
                        bout.write(buf,0,length);  
                    }  
                    byte[] content = bout.toByteArray();
    				// TextView滚动条
    				show.setMovementMethod(ScrollingMovementMethod.getInstance());
                    show.setText(new String(content,"UTF-8")); //设置文本框为读取的内容  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                show.invalidate(); //刷新屏幕  
                try{  
                    in.close();  
                    bout.close();  
                }  
                catch(Exception e){}  
            }  
		}

	};
	// 生成一个Runnabler方法
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			geHtml();
			handler.sendEmptyMessage(TEXT);
		}
	};

	// 生成一个Handler对像
	Handler handler = new Handler() {
		// 复写handlerMessage方法
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case TEXT:
				// TextView滚动条
				show.setMovementMethod(ScrollingMovementMethod.getInstance());
				show.setText(content);// 设置文本显示
				break;
			default:
				break;
			}

		}

	};

	// 获取Html数据
	private void geHtml() {
		// 获取输入框中的字符
		uriString = "http://" + edt.getText().toString();
		content = GetHtml.getHtml(uriString);
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
