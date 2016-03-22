package com.lanbots.sqlite02;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * 封装所有的业务方法
 * @AUTHOR LANBOTS
 */
public class DBStudent {

	private StuDBHelper helper;
	private SQLiteDatabase db;

	public DBStudent(Context context) {
		helper = new StuDBHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName,0,mFactory);
		// 所以要确保context已初始化，我们可以把实例化DBStudent的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}
	
	/*
	 * 添加学生
	 */
	public void add(List<Student> students){
		db.beginTransaction();//开始事务
		try{
			for(Student student : students){
				db.execSQL("insert into stu_table values(null,?,?,?", new Object[]{student.name, student.age, student.sex});
			}
			db.setTransactionSuccessful();//设置事务成功完成
		}finally{
			db.endTransaction();//结束事务
		}
	}
	
	/*
	 * 更新学生年龄
	 */
	
	public void updateAge(Student student){
		//生成ContentValues对象
		ContentValues cv = new ContentValues();
		cv.put("age", student.age);
		db.update("stu_table", cv, "name = ?", new String[]{student.name});
	}
	
	/*
	 * 删除年龄最大的学生
	 */
	public void deleteOldStudent(Student student){
		db.delete("stu_table", " age >= ?", new String[]{String.valueOf(student.age)});
	}
	
	/*
	 * 查询所有学生,return list
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
	 * 查询所有学生，return cursor
	 */
	public Cursor queryTheCursor(){
		Cursor c = db.rawQuery("select * from stu_table", null);
		return c;
	}
	
	/*
	 * 关闭数据库
	 */
	public void closeDB(){
		db.close();
	}
}
