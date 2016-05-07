package com.example.linbots.notepad;

import android.content.ContentValues;
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
 * 新建记事本以及保存
 * Created by LinBots on 2016/5/6.
 */
public class ContentActivity extends  MainActivity {

    private TextView show_time;
    private Button save_btn;
    private Button back_btn;
    private EditText content_edt;
    //声明数据库
    private DBHelper dbHelper;
    //声明游标
    private Cursor cursor;
    //声明字段
    private int _id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        dbHelper = new DBHelper(this);
        cursor = dbHelper.select();
        //初始化控件
        content_edt = (EditText)findViewById(R.id.content_edt);
        show_time = (TextView)findViewById(R.id.show_time);
        save_btn = (Button)findViewById(R.id.btn_save);
        back_btn = (Button)findViewById(R.id.btn_back);
        //显示系统时间
        show_time.setText(getTime());
        save_btn.setOnClickListener(listener);
        back_btn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    addData();
                    Intent intent01 = new Intent(ContentActivity.this , MainActivity.class);
                    startActivity(intent01);
                    break;
                case R.id.btn_back:
                    Intent intent02 = new Intent(ContentActivity.this , MainActivity.class);
                    startActivity(intent02);
                default:
                    break;
            }
        }
    };

    //保存（添加数据）
//添加数据
    private void addData(){
        //获取数据
        String content = content_edt.getText().toString();
        //判断数据是否为空
        if( content.equals("") ){
            Toast.makeText(ContentActivity.this, "文本内容不能为空", Toast.LENGTH_LONG).show();
        }else {
            //插入数据
            dbHelper.insert(content);
            //刷新游标
            cursor.requery();
            content_edt.setText("");
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

