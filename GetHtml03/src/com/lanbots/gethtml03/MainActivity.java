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
					show.setText("���ִ���" + e.toString());
				}

			} else if (arg0 == saveHtmlButton) {
				//������ļ���Ϊ���������ַ����������www.baidu.com,������ļ�����Ϊ��www.baidu.com
				String filename = edt.getText().toString();
				String filecontent = content;
				FileOutputStream out = null;
				// �洢���ֻ��ڴ���
				// ������ļ���data-->data-->com.lanbots.gethtml03-->files��

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
				//��ȡHTML,����������뱣����ļ��������ɲ鿴����Ϊ���ļ�����HTML
				String filename = edt.getText().toString(); //��ö�ȡ���ļ������ƣ�����Ϊ�������ַ��  
                FileInputStream in = null;  
                ByteArrayOutputStream bout = null;  
                byte[]buf = new byte[1024];  
                bout = new ByteArrayOutputStream();  
                int length = 0;  
                try {  
                    in = context.openFileInput(filename); //���������  
                    while((length=in.read(buf))!=-1){  
                        bout.write(buf,0,length);  
                    }  
                    byte[] content = bout.toByteArray();
    				// TextView������
    				show.setMovementMethod(ScrollingMovementMethod.getInstance());
                    show.setText(new String(content,"UTF-8")); //�����ı���Ϊ��ȡ������  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                show.invalidate(); //ˢ����Ļ  
                try{  
                    in.close();  
                    bout.close();  
                }  
                catch(Exception e){}  
            }  
		}

	};
	// ����һ��Runnabler����
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			geHtml();
			handler.sendEmptyMessage(TEXT);
		}
	};

	// ����һ��Handler����
	Handler handler = new Handler() {
		// ��дhandlerMessage����
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case TEXT:
				// TextView������
				show.setMovementMethod(ScrollingMovementMethod.getInstance());
				show.setText(content);// �����ı���ʾ
				break;
			default:
				break;
			}

		}

	};

	// ��ȡHtml����
	private void geHtml() {
		// ��ȡ������е��ַ�
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
