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
		//�õ�Intent������������
		Intent intent = getIntent();
		String data = intent.getStringExtra("data");
		_id = intent.getIntExtra("id", _id);
		//��Intent��������������ʾ��EditText
		show_edt.setText(data);
		
		//ʵ��������
		contentHelper = new ContentHelper(this);
		//�õ��α�
		cursor = contentHelper.select();
		
		//��ʼ���ؼ�
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
	
	//�޸�����
	private void updata(){
		//��������id���޸�����
		if(_id == 0){
			Toast.makeText(Modify.this, "����Ϊ��", Toast.LENGTH_LONG).show();
		}else {
			contentHelper.updata(_id, show_edt.getText().toString());
			//ˢ���α�
			cursor.requery();
			//����
			_id = 0;
		}
	}
	
	//ɾ������
	private void delete(){
		if( _id == 0){
			Toast.makeText(Modify.this, "����Ϊ��", Toast.LENGTH_LONG).show();
		}else {
			contentHelper.delete(_id);
			//ˢ���α�
			cursor.requery();
			//����
			_id = 0;
		}
	}
}
