package com.yxld.yxchuangxin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yxld.yxchuangxin.entity.CxwyMallUser;
import com.yxld.yxchuangxin.entity.SearchHistoryEntityWh;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
//	private static final String DATABASE_NAME = "tuoxing.db";
	private static final String DATABASE_NAME = "tuoxin.db";

//	private static final int DATABASE_VERSION = 1; //v1.1.1
	private static final int DATABASE_VERSION = 3; //v1.2.0

	public MyDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		creatTable(db, SearchHistoryEntityWh.class);
		creatTable(db, CxwyMallUser.class);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion == 2){
			upGradeToV2(db);
		}
	}

	private void upGradeToV2(SQLiteDatabase db) {
		//TODO 加入新字段和新表
	}

	/**
	 * 利用反射建表
	 * 
	 * @param
	 */
	private <T> void creatTable(SQLiteDatabase db, Class<T> clazz) {
		StringBuffer buffer = new StringBuffer();

		String[] fieldNames = getFiledName(clazz);

		buffer.append("CREATE TABLE IF NOT EXISTS "
				+ clazz.getSimpleName()
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,flag VARCHAR,");
		Log.d("geek","creatTable="+buffer.toString());
		for (int i = 0; i < fieldNames.length; i++) {
			Log.d("geek",i+"循环 ="+fieldNames[i]);
			buffer.append(fieldNames[i] + " VARCHAR");
			if (i != fieldNames.length - 1) {
				buffer.append(", ");
			}
		}
		buffer.append(")");
		Log.d("geek","creatTable结束="+buffer.toString());
		db.execSQL(buffer.toString());
	}

	/**
	 * 获取属性名数组
	 * */
	public <T> String[] getFiledName(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		//剔除不需要的属性
		List<String> fieldNames = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.isSynthetic()){
				continue;
			}
			String fieldName = field.getName();
			Log.d("geek",i+"获取属性名fieldName="+fieldName);
			if("serialVersionUID".equals(fieldName) || "CREATOR".equals(fieldName)){
				continue;
			}
			fieldNames.add(fieldName);
		}
		Log.d("geek","获取属性名fieldNames="+fieldNames.toString());
		return fieldNames.toArray(new String[fieldNames.size()]);
	}

	/**
	 * 获取对象的属性键值对</br> 如果值为空，设置值为空字符串 </br> \
	 * @param userId 筛选的值
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 * */
	public ContentValues getFiledValues(Object o, String userId) throws Exception {
		Field[] fields = o.getClass().getDeclaredFields();
		ContentValues values = new ContentValues();
		for (int i = 0; i < fields.length; i++) {
			//剔除不需要的属性
			if("serialVersionUID".equals(fields[i].getName()) || "CREATOR".equals(fields[i].getName())){
				continue;
			}
			Field field = fields[i];
			if(field.isSynthetic()){
				continue;
			}
			field.setAccessible(true);
			Object value = fields[i].get(o);
			if (value == null) {
				value = "";
			}
			values.put(field.getName(), String.valueOf(value));
		}
		if(!values.containsKey("flag")){
			values.put("flag", userId);
		}
		return values;
	}
}
