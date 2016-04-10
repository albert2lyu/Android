package com.lanbots.saxdemo01;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonSaxHandler extends DefaultHandler {
	
	//����һ��װ��Person���͵�List
	private ArrayList<Person> mList;
	//����һ��Person���͵ı���
	private Person person;
	//����һ���ַ�������
	private String content;
	
	/**
	 * ���췽��
	 * װ�ط��ؽ����List����
	 */
	public PersonSaxHandler(ArrayList<Person> list){
		this.mList = list;
	}
	
	//��SAX������������XML�ĵ�ʱ������õķ���
	public void startDocument() throws SAXException{
		super.startDocument();
	}
	//��SAX�������������ĵ�����ʱ������õķ���
	public void endDocument() throws SAXException{
		super.endDocument();
	}


	/**
	 * ��SAX������������ĳ������ֵʱ������õķ���
	 * ���в���ch��¼��������Ե�����
	 */
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		content = new String (ch, start, length);
	}
	/**
	 * ��SAX������������ĳ���ؿ�ʼʱ�����ø÷���
	 * ����localName��¼����Ԫ�ص�������
	 */

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		if("person".equals(localName)){
			person = new Person(); //�½�Person����
		}
	}


	/**
	 * ��SAX������������ĳ��Ԫ�ؽ���ʱ�����ø÷���
	 * ����localName��¼����Ԫ�ص�������
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
			 mList.add(person); //��Person������뵽List��
		}
	}
	
	
	
		
}
