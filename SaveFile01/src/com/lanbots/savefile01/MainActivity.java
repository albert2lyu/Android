package com.lanbots.savefile01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 保存文件和读取文件
 * @author LinBots
 *
 */
public class MainActivity extends Activity {

	private Button saveButton, readButton;
	private EditText filenameEt, filecontentEt;
	private Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveButton = (Button) this.findViewById(R.id.saveButton);
		readButton = (Button) this.findViewById(R.id.readButton);
		filenameEt = (EditText) this.findViewById(R.id.filename);
		filecontentEt = (EditText) this.findViewById(R.id.filecontent);
		saveButton.setOnClickListener(listener);
		readButton.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == saveButton) {
				// 获取保存文件名
				String filename = filenameEt.getText().toString();
				// 获取保存文件内容
				String filecontent = filecontentEt.getText().toString();
				FileOutputStream out = null;
				try {
					out = context.openFileOutput(filename, Context.MODE_PRIVATE);
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
			} else if (v == readButton) {
				String filename = filenameEt.getText().toString(); // 获得读取的文件的名称
				FileInputStream in = null;
				byte[] buf = new byte[1024];
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				int length = 0;
				try {
					in = context.openFileInput(filename); // 获得输入流
					while ((length = in.read(buf)) != -1) {
						bout.write(buf, 0, length);
					}
					byte[] content = bout.toByteArray();
					filecontentEt.setText(new String(content, "UTF-8")); // 设置文本框为读取的内容
				} catch (Exception e) {
					e.printStackTrace();
				}
				filecontentEt.invalidate(); // 刷新屏幕
				try {
					in.close();
					bout.close();
				} catch (Exception e) {
				}
			}
		}

	};

}
