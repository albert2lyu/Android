package com.lanbots.sqlite03;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContentActivity extends MainActivity {
	
	private Button ok_btn;
	private Button cancle_btn;
	private EditText conentWrite;
	//声明数据库
	private ContentHelper contentHelper;
	//声明游标
	private Cursor cursor;
	//声明字段
	private int _id = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		init();
		
	}
	
	private void init(){
		contentHelper = new ContentHelper(this);
		//得到游标
		cursor = contentHelper.select();
		
		ok_btn = (Button)findViewById(R.id.btn_ok);
		cancle_btn = (Button)findViewById(R.id.btn_cancle);
		conentWrite = (EditText)findViewById(R.id.et_content);
		
		ok_btn.setOnClickListener(listener);
		cancle_btn.setOnClickListener(listener);
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_ok:
				addData();
				Intent intent01 = new Intent();
				intent01.setClass(ContentActivity.this, MainActivity.class);
				startActivity(intent01);
				finish();
				break;
			case R.id.btn_cancle:
				Intent intent02 = new Intent();
				intent02.setClass(ContentActivity.this, MainActivity.class);
				startActivity(intent02);
				finish();
			default:
				break;
			}
		}
	};
	
	//添加数据
	private void addData(){
		//获取数据
		String content = conentWrite.getText().toString();
		//判断数据是否为空
		if( content.equals("") ){
			Toast.makeText(ContentActivity.this, "文本内容不能为空", Toast.LENGTH_LONG).show();
		}else {
			//插入数据
			contentHelper.insert(content);
			//刷新游标
			cursor.requery();
			conentWrite.setText("");
			//清零
			_id = 0;
		}
	}
	

}
