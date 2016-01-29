package com.lanbots.sqlite02;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * ��װ���е�ҵ�񷽷�
 * @AUTHOR LANBOTS
 */
public class DBStudent {

	private StuDBHelper helper;
	private SQLiteDatabase db;

	public DBStudent(Context context) {
		helper = new StuDBHelper(context);
		// ��ΪgetWritableDatabase�ڲ�������mContext.openOrCreateDatabase(mName,0,mFactory);
		// ����Ҫȷ��context�ѳ�ʼ�������ǿ��԰�ʵ����DBStudent�Ĳ������Activity��onCreate��
		db = helper.getWritableDatabase();
	}
	
	/*
	 * ���ѧ��
	 */
	public void add(List<Student> students){
		db.beginTransaction();//��ʼ����
		try{
			for(Student student : students){
				db.execSQL("insert into stu_table values(null,?,?,?", new Object[]{student.name, student.age, student.sex});
			}
			db.setTransactionSuccessful();//��������ɹ����
		}finally{
			db.endTransaction();//��������
		}
	}
	
	/*
	 * ����ѧ������
	 */
	
	public void updateAge(Student student){
		//����ContentValues����
		ContentValues cv = new ContentValues();
		cv.put("age", student.age);
		db.update("stu_table", cv, "name = ?", new String[]{student.name});
	}
	
	/*
	 * ɾ����������ѧ��
	 */
	public void deleteOldStudent(Student student){
		db.delete("stu_table", " age >= ?", new String[]{String.valueOf(student.age)});
	}
	
	/*
	 * ��ѯ����ѧ��,return list
	 */
	public List<Student> query(){
		ArrayList<Student> students = new ArrayList<Student>();
		Cursor c = queryTheCursor();
		while(c.moveToNext()){
			Student student = new Student();
			student.id = c.getInt(c.getColumnIndex("id"));
			student.name = c.getString(c.getColumnIndex("name"));
			student.age = c.getInt(c.getColumnIndex("age"));
			student.sex = c.getString(c.getColumnIndex("sex"));
			students.add(student);
		}
		c.close();
		return students;
	}
	/*
	 * ��ѯ����ѧ����return cursor
	 */
	public Cursor queryTheCursor(){
		Cursor c = db.rawQuery("select * from stu_table", null);
		return c;
	}
	
	/*
	 * �ر����ݿ�
	 */
	public void closeDB(){
		db.close();
	}
}
