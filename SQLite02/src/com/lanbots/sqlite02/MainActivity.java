package com.lanbots.sqlite02;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;
import android.provider.SyncStateContract.Columns;

public class MainActivity extends Activity {
	
	
	private DBStudent mgr;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.listView);
		//初始化DBStudent
		mgr = new DBStudent(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//应用的最后一个Activity关闭时因释放DB
		mgr.closeDB();
	}

	public void add(View view){
		ArrayList<Student> students = new ArrayList<Student>();
		
		Student student1 = new Student("linbots", 20, "man");
		Student student2 = new Student("lanbots", 21, "man" );
		Student student3 = new Student("linbo", 22, "woman");
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		
		mgr.add(students);
	}
	
	public void update(View view){
		Student student = new Student();
		student.name = "lanbots";
		student.age = 30;
		mgr.updateAge(student);
	}
	
	public void delete(View view){
		Student student = new Student();
		student.age = 30;
		mgr.deleteOldStudent(student);
	}
	
	public void query(View view){
		List<Student> students = mgr.query();
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(Student student : students){
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("name", student.name);
			map.put("sex", student.sex);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, 
				new String[]{"name", "sex"}, new int[]{android.R.id.text1, android.R.id.text2});
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
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	
}
