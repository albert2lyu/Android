package com.lanbots.saxdemo01;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonSaxHandler extends DefaultHandler {
	
	//声明一个装载Person类型的List
	private ArrayList<Person> mList;
	//声明一个Person类型的变量
	private Person person;
	//声明一个字符串变量
	private String content;
	
	/**
	 * 构造方法
	 * 装载返回结果的List对象
	 */
	public PersonSaxHandler(ArrayList<Person> list){
		this.mList = list;
	}
	
	//当SAX解析器解析到XML文档时，会调用的方法
	public void startDocument() throws SAXException{
		super.startDocument();
	}
	//当SAX解析器解析到文档结束时，会调用的方法
	public void endDocument() throws SAXException{
		super.endDocument();
	}


	/**
	 * 当SAX解析器解析到某个属性值时，会调用的方法
	 * 其中参数ch记录了这个属性的内容
	 */
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		content = new String (ch, start, length);
	}
	/**
	 * 当SAX解析器解析到某个素开始时，调用该方法
	 * 其中localName记录的是元素的属性名
	 */

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		if("person".equals(localName)){
			person = new Person(); //新建Person对象
		}
	}


	/**
	 * 当SAX解析器解析到某个元素结束时，调用该方法
	 * 其中localName记录的是元素的属性名
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if("name".equals(localName)){
			person.setName(content);
		}else if("age".equals(localName)){
			person.setAge(content);
		}else if("person".equals(localName)){
			 mList.add(person); //将Person对象加入到List中
		}
	}
	
	
	
		
}
