package com.lanbots.ks;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Build;

public class LoginActivity extends Activity {

	private EditText userName;
	private EditText passWord;
	private CheckBox rmpsd;
	private Button btn_login;
	private Button btn_register;
	private ImageButton btn_img;
	private String userNameValue;
	private String passWordValue;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// 获得实例对象
		sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

		userName = (EditText) findViewById(R.id.edt1);
		passWord = (EditText) findViewById(R.id.edt2);
		rmpsd = (CheckBox) findViewById(R.id.rmpsd);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button) findViewById(R.id.register);
		btn_img = (ImageButton) findViewById(R.id.img_btn);

		btn_register.setOnClickListener(new listener());
		btn_img.setOnClickListener(new listener());
		btn_login.setOnClickListener(new listener());
		rmpsd.setOnCheckedChangeListener(new CheckedChangeListener());

		// 判断记住密码多选框的状态
		if (sp.getBoolean("ISCHECK", false)) {
			// 设置默认是记录密码状态
			rmpsd.setChecked(true);
			userName.setText(sp.getString("USER_NAME", ""));
			passWord.setText(sp.getString("PASSWORD", ""));
		}

	}

	private class listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_login:
				btn_login();
				break;
			case R.id.img_btn:
				showDialog(0); // 设置弹窗提示
				break;
			case R.id.register:
				Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_LONG)
						.show();
			default:
				break;
			}
		}

	}

	// 监听记住密码多选框按钮事件
	private class CheckedChangeListener implements OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (rmpsd.isChecked()) {

				System.out.println("记住密码已选中");
				sp.edit().putBoolean("ISCHECK", true).commit();

			} else {

				System.out.println("记住密码没有选中");
				sp.edit().putBoolean("ISCHECK", false).commit();

			}

		}

	}

	// 登录监听事件 现在默认为用户名为：lanbots 密码：123456
	private void btn_login() {
		userNameValue = userName.getText().toString();
		passWordValue = passWord.getText().toString();

		if (userNameValue.equals("lanbots") && passWordValue.equals("123456")) {
			Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT)
					.show();
			// 登录成功和记住密码框为选中状态才保存用户信息
			if (rmpsd.isChecked()) {
				// 记住用户名、密码、
				Editor editor = sp.edit();
				editor.putString("USER_NAME", userNameValue);
				editor.putString("PASSWORD", passWordValue);
				editor.commit();
			}
			// 跳转界面
			Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
			startActivity(intent);
			// finish();

		} else {

			Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 弹窗
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
	 * 弹窗显示
	 */
	private Dialog show() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("确定退出？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						/* User clicked Yes so do some stuff */
						LoginActivity.this.finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		return builder.create();
	}
}
