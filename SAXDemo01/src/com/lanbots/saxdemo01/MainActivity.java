package com.lanbots.saxdemo01;

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
import android.widget.TextView;

/**
 * ʹ��SAX����XML�ļ�
 * ʹ��AssetMessage�����ȡXML�ļ�
 * @author LinBots
 *
 */
public class MainActivity extends Activity {
	
	//private ListView show;
	private TextView showXML;
	//����װ��Person�����List
	private ArrayList<Person> personList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ��personList����
		if(personList == null){
			personList = new ArrayList<Person>();
		}
		
		getPersons();
		//��ʼ���ؼ�Ԫ��
		showXmlParser();
		
	}
	
	
	/**
	 * ʹ��SAX����������XML�ļ�
	 */
	private void getPersons(){
		try{
			//��ȡAsseManager�������
			AssetManager as = this.getAssets();
			//ͨ��AssetManager��Open������ȡdate.xml�ļ�������
			//date.xml�ļ���assetsĿ¼��
			InputStream is1 = as.open("date.xml");
			System.out.println(is1);
			//ͨ����ȡ��InputStream���õ�InputSourceʵ��
			InputSource is2 = new InputSource(is1);
			//ʹ�ù���������ʼ��SAXParserFactory����
			  SAXParserFactory factory  = SAXParserFactory.newInstance(); 
			  //ͨ��SAXParserFactory����õ�SAXParser ʵ��
			  SAXParser paser = factory.newSAXParser();
			  //ͨ��SAXParser�õ�XMLReaderʵ��
			  XMLReader reader = paser.getXMLReader();
			  //��ʼ���Զ�����PersonSaxHandler��������personList���ݸ������Ա��������
			  PersonSaxHandler psh = new PersonSaxHandler(personList);
			  //������psh���ݸ�XMLReader����
			  reader.setContentHandler(psh);
			  //����reader��parse��������������
			  reader.parse(is2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * �����������ʾ������
	 */
	private void showXmlParser(){
		String result = "";
		for(Person p : personList){
			result += p.toString() + "\n";
		}
		showXML = (TextView)findViewById(R.id.showXML);
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
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
