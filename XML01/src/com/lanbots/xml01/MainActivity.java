package com.lanbots.xml01;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
/**
 * 解析XML
 * @author LinBots
 *
 */
public class MainActivity extends Activity {
	
	private Button connectWeb;
	private Button parser;
	private ListView show;
    private String httpUrl="http://flash.weather.com.cn/wmaps/xml/guangzhou.xml";
	private List data=new ArrayList();
	private SimpleAdapter adapter;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化布局元素
		initView();
	}

	private void initView(){
		
		connectWeb = (Button)findViewById(R.id.intenet);
		parser = (Button)findViewById(R.id.parser);
		show = (ListView)findViewById(R.id.show);
		
		connectWeb.setOnClickListener(listener);
		parser.setOnClickListener(listener);
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch (v.getId()) {
			case R.id.intenet:
				Thread thread1=new Thread(new ConnectWeb(httpUrl,data));
				thread1.start();
				break;
			case R.id.parser:
				adapter = new SimpleAdapter(MainActivity.this,data,R.layout.activity_simple_adapter_demo,
		                new String[]{"imge","info"},
		                new int[]{R.id.imageView,R.id.weatherInfoView});
				adapter.setViewBinder(new ViewBinder() {
					@Override
					public boolean setViewValue(View view, Object data,
							String textRepresentation) {
						// TODO Auto-generated method stub
						if(view instanceof ImageView && data instanceof Bitmap){
							ImageView i = (ImageView)view;
							i.setImageBitmap((Bitmap) data);
							return true;
						}
						return false;
					}
				});
				show.setAdapter(adapter);
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			//退出
			finish();
		}
		return super.onOptionsItemSelected(item);
	}


}
