package com.lanbots.sqlite03;


import com.lanbots.sqlite03.R.id;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modify extends MainActivity {
	
	private Button updata_btn;
	private Button delete_btn;
	private Button back_btn;
	private EditText show_edt;
	private ContentHelper contentHelper;
	private Cursor cursor;
	private int _id = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify);
		init();
	}
	
	private void init(){
		show_edt = (EditText)findViewById(R.id.et_modify);
		//得到Intent传过来的数据
		Intent intent = getIntent();
		String data = intent.getStringExtra("data");
		_id = intent.getIntExtra("id", _id);
		//把Intent传过来的数据显示到EditText
		show_edt.setText(data);
		
		//实例化数组
		contentHelper = new ContentHelper(this);
		//得到游标
		cursor = contentHelper.select();
		
		//初始化控件
		updata_btn = (Button)findViewById(R.id.tbn_update);
		delete_btn = (Button)findViewById(R.id.btn_delete);
		back_btn = (Button)findViewById(R.id.btn_back);
		
		updata_btn.setOnClickListener(listener);
		delete_btn.setOnClickListener(listener);
		back_btn.setOnClickListener(listener);
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tbn_update:
				updata();
				Intent intent01 = new Intent(Modify.this,MainActivity.class);
				startActivity(intent01);
				finish();
				break;
			case R.id.btn_delete:
				delete();
				Intent intent02 = new Intent(Modify.this,MainActivity.class);
				startActivity(intent02);
				finish();
				break;
			case R.id.btn_back:
				Intent intent03 = new Intent(Modify.this,MainActivity.class);
				startActivity(intent03);
				finish();
				break;
			default:
				break;
			}
		}
	};
	
	//修改数据
	private void updata(){
		//根据数据id来修改数据
		if(_id == 0){
			Toast.makeText(Modify.this, "不能为空", Toast.LENGTH_LONG).show();
		}else {
			contentHelper.updata(_id, show_edt.getText().toString());
			//刷新游标
			cursor.requery();
			//清零
			_id = 0;
		}
	}
	
	//删除数据
	private void delete(){
		if( _id == 0){
			Toast.makeText(Modify.this, "不能为空", Toast.LENGTH_LONG).show();
		}else {
			contentHelper.delete(_id);
			//刷新游标
			cursor.requery();
			//清零
			_id = 0;
		}
	}
}
