package com.lanbots.saxdemo02;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 使用SAX解析XML文档
 * 使用InputStream is1 = getResources().openRawResource(R.raw.date);获取文件输入流
 * @author LinBots
 *
 */
public class MainActivity extends Activity {

	// private ListView show;
	private TextView showXML;
	private Button parser;
	// 声明装载Person对象的List
	private ArrayList<Person> personList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件元素
		initView();


	}
	
	private void initView(){
		
		parser = (Button)findViewById(R.id.parser);
		parser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 初始化personList链表
				if (personList == null) {
					personList = new ArrayList<Person>();
				}

				getPersons();
				// 初始化控件元素
				showXmlParser();
			}
		});
		
	}

	/**
	 * 使用SAX解析器解析XML文件
	 */
	private void getPersons() {
		try {
			// //获取AsseManager管理对象
			// AssetManager as = this.getAssets();
			// //通过AssetManager的Open方法获取date.xml文件输入流
			// //date.xml文件在assets目录下
			// InputStream is1 = as.open("date.xml");
			// 用InputStream获取资源
			InputStream is1 = getResources().openRawResource(R.raw.date);
			System.out.println(is1);
			// 通过获取的InputStream来得到InputSource实例
			InputSource is2 = new InputSource(is1);
			// 使用工厂方法初始化SAXParserFactory变量
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 通过SAXParserFactory对象得到SAXParser 实例
			SAXParser paser = factory.newSAXParser();
			// 通过SAXParser得到XMLReader实例
			XMLReader reader = paser.getXMLReader();
			// 初始化自定义类PersonSaxHandler变量，将personList传递给它，以便加载数据
			PersonSaxHandler psh = new PersonSaxHandler(personList);
			// 将对象psh传递给XMLReader对象
			reader.setContentHandler(psh);
			// 调用reader的parse方法解析输入流
			reader.parse(is2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将解析结果显示到界面
	 */
	private void showXmlParser() {
		String result = "";
		for (Person p : personList) {
			result += p.toString() + "\n";
		}
		showXML = (TextView) findViewById(R.id.show);
		showXML.setText(result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish(); //退出
		}
		return super.onOptionsItemSelected(item);
	}
}
