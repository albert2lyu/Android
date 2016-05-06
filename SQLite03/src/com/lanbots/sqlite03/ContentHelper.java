package com.lanbots.sqlite03;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState.CustomAction;

/**
 * 助手类，完成数据表的创建，以及增删改查
 * @author LinBots
 *
 */
public class ContentHelper extends SQLiteOpenHelper {
	
	//数据库名称
	private static final String DATABASE_NAME = "person.db";
	//数据库版本
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "info_table";
	private static final String ID = "_id";
	public static final String CONTENT = "content";
	
	//生命SQLiteDatabase对象
	private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

	public ContentHelper(Context context) {
		super(context, DATABASE_NAME , null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	//创建数据表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		String sql = "CREATE TABLE	" + TABLE_NAME + "(" + ID + "INTEGER primary key autoincrement, " + 
//				CONTENT + " text );";
//		
//		db.execSQL(sql);
		db.execSQL("create table " + TABLE_NAME + "(" + ID
				+ "  integer  primary key autoincrement," + CONTENT + " text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	//插入数据
	public long insert( String text ){
		ContentValues contentValues = new ContentValues();
		contentValues.put("content", text);
		long row = sqLiteDatabase.insert(TABLE_NAME, null, contentValues	);
		return row;
	}
	//修改数据
	public void updata( int _id, String text	){
		ContentValues contentValues  = new ContentValues();
		contentValues.put("content", text);
		sqLiteDatabase.update(TABLE_NAME, contentValues , ID+"=?", new String[]{Integer.toString(_id)});
	}
	//删除数据库
	public void delete(int _id){
		sqLiteDatabase.delete(TABLE_NAME, ID+"=?", new String[]{Integer.toString(_id)});
	}
	//查询数据库
	public Cursor select(){
		Cursor cursor = sqLiteDatabase.query( TABLE_NAME, null, null, null, null, null, null );
		return cursor;
	}
	
}
