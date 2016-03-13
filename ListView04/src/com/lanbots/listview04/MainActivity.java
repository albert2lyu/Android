package com.lanbots.listview04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/*
 * 使用用自定义ListView，继承Activity而不是继承ListActivity
 * @AUTHOR LANBOTS
 * @EMAIL lanbots@foxmail.com
 */
public class MainActivity extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.listView);
		
		SimpleAdapter adapter = new SimpleAdapter(this,
				getData(), R.layout.listview02, 
				new String[]{"title","info","img"}, new int[]{R.id.title,R.id.info,R.id.img});
		listView.setAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("title", "C1");
		map.put("info", "B1");
		map.put("img", R.drawable.i1);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title", "C2");
		map.put("info", "B2");
		map.put("img", R.drawable.i2);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title", "C3");
		map.put("info", "B2");
		map.put("img", R.drawable.i3);
		list.add(map);
		
		return list;
		
	}

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
