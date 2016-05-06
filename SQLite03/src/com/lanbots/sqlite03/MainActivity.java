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
 * ���׼��±�
 * @author LinBots
 *
 */
public class MainActivity extends Activity {
	
	private Button createContent;
	private ListView list;
	//�������ݿ�
	private ContentHelper contentHelper;
	//�����α�
	private Cursor cursor;
	//�����ֶ�
	private int _id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contentHelper = new ContentHelper(this);
		//�õ��α�
		cursor = contentHelper.select();
		//��ʼ���ؼ�
		createContent = (Button)findViewById(R.id.btn_write);
		list = (ListView)findViewById(R.id.lv_title);
		
		createContent.setOnClickListener(listener);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.listitem,
				cursor , new String[]{ContentHelper.CONTENT}, new int[]{R.id.tv_info});
		//��������
		list.setAdapter(adapter);
		//��̬����ListView
		list.invalidateViews();	
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//��ѯ����
				cursor.moveToPosition(position);
				//û�в�ѯ������
				_id = cursor.getInt(0);
				//��ѯ���˸�ֵ
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
