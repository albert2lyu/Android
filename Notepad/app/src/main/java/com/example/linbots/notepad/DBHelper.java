package com.example.linbots.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 創建數據庫表，以及增刪改查方法
 * Created by LinBots on 2016/5/6.
 */
public class DBHelper extends SQLiteOpenHelper {

    //定义数据库名
    private static final String DATABASE_NAME = "content.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //数据表名
    private static final String TABLE_NAME = "info_table";
    //字段名
    private static final String ID = "_id";
    public static final String CONTENT = "content";
    //生成SQLiteDatabase对象
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }


    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table  " + TABLE_NAME + "(" + ID
                + "  integer  primary key autoincrement, " + CONTENT +  " text not null)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //插入数据
    public long insert( String text ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", text);
        long row = sqLiteDatabase.insert(TABLE_NAME, null, contentValues	);
        return row;
    }
    //删除数据库
    public void delete(int _id){
        sqLiteDatabase.delete(TABLE_NAME , ID + "=?" , new String[]{Integer.toString(_id)});
    }
    //修改数据库
    public void updata(int _id, String text){
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", text);
        sqLiteDatabase.update(TABLE_NAME , contentValues, ID + "=?" , new String[]{Integer.toString(_id)});
    }
    //查询数据库
    public Cursor select(){
        Cursor cursor;
        cursor = sqLiteDatabase.query(TABLE_NAME , null ,null , null , null , null , null);
        return  cursor;
    }
}
