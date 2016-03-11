package com.lanbots.listview02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

/*
 * SimpleAdapter
 * ListView的练习
 * @AUTHOR LANBOTS
 */

public class ListViewActivity03 extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//生成一个SimleAdapter对象
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				getData(), R.layout.listview02, 
				new String[]{"title","info","img"}, 
				new int[]{R.id.title, R.id.info, R.id.img});
		setListAdapter(simpleAdapter);
	}
	
	private List<Map<String, Object>> getData(){
		//创建一个List
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//创建一个Map对象
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
	

}
