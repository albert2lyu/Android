package com.lanbots.sqlite02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StuDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "stu.db";
	private static final int VERSION = 1;
	//����Ҫ�д˹��캯��
	public StuDBHelper(Context context) {
		//CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
		super(context, DATABASE_NAME, null, VERSION);
	}
	//���ݿ��һ�α�����ʱonCreate�ᱻ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists stu_table(id int, name varchar(20), age int, sex varchar(10)");
	}
	//VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade  
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("ALTER TABLE stu_table ADD COLUMN other STRING"); 
	}

}
