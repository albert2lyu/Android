package com.lanbots.sqlite03;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
/**
 * 简易记事本
 * @author LinBots
 *
 */
public class MainActivity extends Activity {
	
	private Button createContent;
	private ListView list;
	//声明数据库
	private ContentHelper contentHelper;
	//声明游标
	private Cursor cursor;
	//声明字段
	private int _id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contentHelper = new ContentHelper(this);
		//得到游标
		cursor = contentHelper.select();
		//初始化控件
		createContent = (Button)findViewById(R.id.btn_write);
		list = (ListView)findViewById(R.id.lv_title);
		
		createContent.setOnClickListener(listener);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.listitem,
				cursor , new String[]{ContentHelper.CONTENT}, new int[]{R.id.tv_info});
		//绑定适配器
		list.setAdapter(adapter);
		//动态属性ListView
		list.invalidateViews();	
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//查询方法
				cursor.moveToPosition(position);
				//没有查询到清零
				_id = cursor.getInt(0);
				//查询到了赋值
				Intent intent = new Intent(MainActivity.this,Modify.class);
				intent.putExtra("id", _id);
				intent.putExtra("data", cursor.getString(1));
				startActivity(intent);
				finish();
			}
		} );
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_write:
				Intent intent = new Intent(MainActivity.this , ContentActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}
	}; 
	
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
