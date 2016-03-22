package com.lanbots.sqlite02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StuDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "stu.db";
	private static final int VERSION = 1;
	//必须要有此构造函数
	public StuDBHelper(Context context) {
		//CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, VERSION);
	}
	//数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists stu_table(id int, name varchar(20), age int, sex varchar(10)");
	}
	//VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("ALTER TABLE stu_table ADD COLUMN other STRING"); 
	}

}
