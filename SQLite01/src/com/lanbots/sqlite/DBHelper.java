package com.lanbots.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = "TestSQLite";
	public static final int VERSION = 1;

	// ����Ҫ�иù��캯��
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	// ����һ�δ������ݿ��ʱ�򣬵��ø÷���
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table stu_table(id int,name varchar(20),age int,sex varchar(10))";
		// ����������ݿ����־��Ϣ
		Log.i(TAG, "create Database------------->");
		// execSQL��������ִ��SQL���
		db.execSQL(sql);
	}

	// ���������ݿ��ʱ��ִ�и÷���
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// ����������ݿ����־��Ϣ
		Log.i(TAG, "update Database------------->");
	}
}