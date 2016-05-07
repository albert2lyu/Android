package com.example.linbots.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button add_btn;
    private ListView listView;
    //声明数据库
    private DBHelper dbHelper;
    //声明游标
    private Cursor cursor;
    //声明字段
    private int _id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this );
        //得到游标
        cursor = dbHelper.select();
        //初始化控件
        add_btn = (Button)findViewById(R.id.btn_new);
        listView = (ListView)findViewById(R.id.list);
        add_btn.setOnClickListener(listener);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this , R.layout.listitem ,
                cursor , new String[]{DBHelper.CONTENT} , new int[]{R.id.show_info});
        //绑定适配器
        listView.setAdapter(adapter);
        //动态属性ListView
        listView.invalidateViews();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查询方法
                cursor.moveToPosition(position);
                //没有查询到清零
                _id = cursor.getInt(0);
                //查询到了赋值
                Intent intent = new Intent(MainActivity.this,ModifyActivity.class);
                intent.putExtra("id", _id);
                //cursor.getString(1);获取该列的数据
                intent.putExtra("data", cursor.getString(1));
                startActivity(intent);
                finish();
            }
        });
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ( v.getId() ){
                case R.id.btn_new:
                    Intent intent = new Intent(MainActivity.this , ContentActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
