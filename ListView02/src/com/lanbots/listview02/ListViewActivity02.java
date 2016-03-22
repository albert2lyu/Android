package com.lanbots.listview02;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
/*
 * SimpleCursorAdapter
 * 获取手机联系人名字，并在ListView列表中显示
 * @AUTHOR LANBOTS
 */
public class ListViewActivity02 extends Activity {
	
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.listview);
		listView = new ListView(this);
		
		Cursor cursor = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
		startManagingCursor(cursor);
		
        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, 
                cursor,
                new String[]{People.NAME}, 
                new int[]{android.R.id.text1});
		
		listView.setAdapter(listAdapter);
		setContentView(listView);
		
	}
	
	

}
