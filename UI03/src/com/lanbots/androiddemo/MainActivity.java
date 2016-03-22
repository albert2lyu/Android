package com.lanbots.androiddemo;


/*
 * ������沼��
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageButton btn_img; //�˳�
	private Button btn_register; //ע��
	private Button btn_login; //����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_img = (ImageButton) findViewById(R.id.img_btn);
		btn_register = (Button) findViewById(R.id.register);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_img.setOnClickListener(new listener());
		btn_register.setOnClickListener(new listener());
		btn_login.setOnClickListener(new listener());

	}
	
	private class listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_btn:
				showDialog(0); // ���õ�����ʾ
				break;
			case R.id.register:
				Toast.makeText(MainActivity.this, "ע��", Toast.LENGTH_LONG)
						.show();
				break;
			case R.id.btn_login:
				Toast.makeText(MainActivity.this, "����", Toast.LENGTH_LONG).show();
			default:
				break;
			}
		}
	}
	
	
	/**
	 * ����
	 */
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 0:
			return show();
		}
		return super.onCreateDialog(id);
	}

	/**
	 * ������ʾ
	 */
	private Dialog show() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("ȷ���˳���")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						/* User clicked Yes so do some stuff */
						MainActivity.this.finish();
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		return builder.create();
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
