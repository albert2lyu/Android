package com.example.linbots.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 修改记事本，加删除功能
 * Created by LinBots on 2016/5/6.
 */
public class ModifyActivity extends  MainActivity {

    private EditText show_edt;
    private Button ok_btn;
    private Button cancel_btn;
    private Button delete_btn;
    private TextView time;
    private DBHelper dbHelper;
    private Cursor cursor;
    private int _id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        init();
    }

    private void init(){
        dbHelper = new DBHelper(this);
        cursor = dbHelper.select();
        //初始化控件
        show_edt = (EditText)findViewById(R.id.edt_show);
        ok_btn = (Button)findViewById(R.id.btn_ok);
        cancel_btn = (Button)findViewById(R.id.btn_cancel);
        delete_btn = (Button)findViewById(R.id.btn_delete);
        time = (TextView )findViewById(R.id.show_time);
        //显示时间
        time.setText(getTime());
        //接收Intent发送过来的数据，并显示在EditText中
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        _id = intent.getIntExtra("id" , _id);
        show_edt.setText(data);
        ok_btn.setOnClickListener(listener);
        cancel_btn.setOnClickListener(listener);
        delete_btn.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_ok:
                    upData();
                    Intent intent01 = new Intent(ModifyActivity.this , MainActivity.class);
                    startActivity(intent01);
                    break;
                case R.id.btn_delete:
                    delete();
                    Intent intent02 = new Intent(ModifyActivity.this , MainActivity.class);
                    startActivity(intent02);
                    break;
                case R.id.btn_cancel:
                    Intent intent03 = new Intent(ModifyActivity.this , MainActivity.class);
                    startActivity(intent03);
                    break;
                default:
                    break;
            }

        }
    };

    //修改数据
    private void upData(){
        if (show_edt.getText().toString().equals("")) {
            Toast.makeText(ModifyActivity.this, "不能为空" , Toast.LENGTH_LONG).show();
        }else{
            dbHelper.updata( _id , show_edt.getText().toString());
            cursor.requery();
            _id = 0;
        }
    }

    //删除
    private void delete(){
        if( _id == 0){
            Toast.makeText(ModifyActivity.this, "出现错误，_id为0" , Toast.LENGTH_LONG).show();
        }else{
            dbHelper.delete(_id);
            cursor.requery();
            _id = 0;
        }
    }

    //获取系统当前时间
    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }


}
