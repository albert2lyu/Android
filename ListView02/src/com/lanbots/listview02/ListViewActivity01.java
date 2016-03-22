package com.lanbots.listview02;

import java.util.ArrayList;
import java.util.List;

import com.lanbots.listview02.R.id;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ListViewActivity01 extends Activity {
	
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listView = new ListView(this);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity01.this,
				android.R.layout.simple_list_item_1, getData());
		
		listView.setAdapter(adapter);
		setContentView(listView);

	}
	
	private List<String> getData(){
		
		List<String> data = new ArrayList<String>();
		
		for(int i = 1; i<= 4; i++){
			
			data.add("²âÊÔÊý¾Ý" + i);
		}
		
		return data;
		
	}

}
