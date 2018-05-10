package com.yxld.yxchuangxin.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.JsonObject;
import com.yxld.yxchuangxin.Utils.GsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作,操作完成后记得调用close()函数
 * 
 * @author wwx
 * 
 */
public class DBUtil {
	MyDbHelper dbHelper;
	private SQLiteDatabase db;

	public DBUtil(Context context) {
		dbHelper = new MyDbHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 关闭数据库，</br> 在每一个context中使用数据库操作完成后必须调用此函数
	 */
	public void close() {
		if (db != null) {
			if (db.isOpen()) {
				db.close();
				db = null;
			}
		}
		dbHelper.close();
	}

	/**
	 * 将实例化的对象插入数据库
	 * 
	 * @param o
	 * @param flag
	 * @return
	 */
	public <T> long insert(T o, String flag) {
		long result = 0;
		if (db == null) {
			return result;
		}
		db.beginTransaction();
		try {
			result = db.insert(o.getClass().getSimpleName(), null,
					dbHelper.getFiledValues(o, flag));
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.endTransaction();
		return result;
	}

	/**
	 * 将实例化的对象插入数据库
	 * 
	 * @param
	 * @param flag
	 * @return
	 */
	public <T> long insert(List<T> list, String flag) {
		long result = 0;
		if (db == null) {
			return result;
		}
		db.beginTransaction();
		try {
			for (int i = 0; i < list.size(); i++) {
				result = db.insert(list.get(i).getClass().getSimpleName(),
						null, dbHelper.getFiledValues(list.get(i),flag));
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.endTransaction();
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param
	 *
	 * @param flag
	 *            查询条件的值
	 * @return
	 */
	public <T> List<T> query(Class<T> clazz, String flag) {
		if (db == null) {
			return null;
		}
		List<T> objects = new ArrayList<T>();
		Cursor c = null;
		try {
			if (flag == null) {
				c = db.query(clazz.getSimpleName(), null, null, null, null, null,
						null);
			} else {
				c = db.query(clazz.getSimpleName(), null, "flag=?",
						new String[] { flag }, null, null, null);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(c == null){
			return null;
		}
		JsonObject jsonObject = new JsonObject();
		String[] filedNames = dbHelper.getFiledName(clazz);
		while (c.moveToNext()) {
			for (int i = 0; i < filedNames.length; i++) {
				String value = c.getString(c.getColumnIndex(filedNames[i]));
				jsonObject.addProperty(filedNames[i], value);
			}
			T result = GsonHelper.fromJson(jsonObject.toString(), clazz);
			objects.add(result);
		}
		c.close();
		if (objects.isEmpty()) {
			return null;
		}
		return objects;
	}

	/**
	 * 查询数据
	 * 
	 * @param
	 *
	 * @param
	 *
	 * @return
	 */
	public <T> List<T> query(Class<T> clazz, String selection,
                             String[] selectionArg) {
		if (db == null) {
			return null;
		}
		List<T> objects = new ArrayList<T>();
		Cursor c;

		c = db.query(clazz.getSimpleName(), null, selection,
				selectionArg, null, null, null);

		JsonObject jsonObject = new JsonObject();
		String[] filedNames = dbHelper.getFiledName(clazz);
		while (c.moveToNext()) {
			for (int i = 0; i < filedNames.length; i++) {
				String value = c.getString(c.getColumnIndex(filedNames[i]));
				jsonObject.addProperty(filedNames[i], value);
			}
			T result = GsonHelper.fromJson(jsonObject.toString(), clazz);
			objects.add(result);
		}
		c.close();
		if (objects.isEmpty()) {
			return null;
		}
		return objects;
	}
	/**
	 * 更新数据
	 * @param
	 * @param
	 * @param userId
	 */
	public <T> int updateData(T o, String userId){
		int result = 0;
		if (db == null) {
			return result;
		}
		db.beginTransaction();
		try {
			result = db.update(o.getClass().getSimpleName(), dbHelper.getFiledValues(o, userId), "flag=?",  new String[]{userId});
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.endTransaction();
		return result;
		
	}
	/**
	 * 清除表中的数据
	 * 
	 * @param clazz
	 */
	public <T> void clearData(Class<T> clazz) {
		if (db == null) {
			return;
		}
		String sql = "delete from " + clazz.getSimpleName();
		db.execSQL(sql);
	}
}
