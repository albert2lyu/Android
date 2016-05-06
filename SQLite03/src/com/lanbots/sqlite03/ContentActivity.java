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
	//�������ݿ�
	private ContentHelper contentHelper;
	//�����α�
	private Cursor cursor;
	//�����ֶ�
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
		//�õ��α�
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
	
	//�������
	private void addData(){
		//��ȡ����
		String content = conentWrite.getText().toString();
		//�ж������Ƿ�Ϊ��
		if( content.equals("") ){
			Toast.makeText(ContentActivity.this, "�ı����ݲ���Ϊ��", Toast.LENGTH_LONG).show();
		}else {
			//��������
			contentHelper.insert(content);
			//ˢ���α�
			cursor.requery();
			conentWrite.setText("");
			//����
			_id = 0;
		}
	}
	

}
