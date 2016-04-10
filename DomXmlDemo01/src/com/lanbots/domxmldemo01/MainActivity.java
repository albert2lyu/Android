package com.lanbots.domxmldemo01;

import java.io.InputStream;
import java.util.List;

import com.lanbots.domxmldemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    private Button parser;
    private ListView list;
    private DomParserService dps;
    private ArrayAdapter<Person> adapter;  
    private List<Person>  persons;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局元素
        initView();
    }

    private void initView(){
        parser = (Button)findViewById(R.id.parser);
        list = (ListView)findViewById(R.id.list);
        

        parser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	try {
            		InputStream is = getResources().openRawResource(R.raw.person);
                	persons = dps.parserXml(is);
                	System.out.println(persons);
                	adapter = new ArrayAdapter<Person>(MainActivity.this,  
                            android.R.layout.simple_expandable_list_item_1, persons);  
                	list.setAdapter(adapter);  
					
				} catch (Exception e) {
					// TODO: handle exception
				}
            	
            	
            }
        });

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
