package com.yxld.yxchuangxin.db.green;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO".
*/
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PhoneNumber = new Property(1, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property IsLogin = new Property(2, boolean.class, "isLogin", false, "IS_LOGIN");
        public final static Property LouPan = new Property(3, String.class, "louPan", false, "LOU_PAN");
        public final static Property Password = new Property(4, String.class, "password", false, "PASSWORD");
        public final static Property XiangmuId = new Property(5, int.class, "xiangmuId", false, "XIANGMU_ID");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PHONE_NUMBER\" TEXT," + // 1: phoneNumber
                "\"IS_LOGIN\" INTEGER NOT NULL ," + // 2: isLogin
                "\"LOU_PAN\" TEXT," + // 3: louPan
                "\"PASSWORD\" TEXT," + // 4: password
                "\"XIANGMU_ID\" INTEGER NOT NULL );"); // 5: xiangmuId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(2, phoneNumber);
        }
        stmt.bindLong(3, entity.getIsLogin() ? 1L: 0L);
 
        String louPan = entity.getLouPan();
        if (louPan != null) {
            stmt.bindString(4, louPan);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
        stmt.bindLong(6, entity.getXiangmuId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(2, phoneNumber);
        }
        stmt.bindLong(3, entity.getIsLogin() ? 1L: 0L);
 
        String louPan = entity.getLouPan();
        if (louPan != null) {
            stmt.bindString(4, louPan);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
        stmt.bindLong(6, entity.getXiangmuId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // phoneNumber
            cursor.getShort(offset + 2) != 0, // isLogin
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // louPan
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // password
            cursor.getInt(offset + 5) // xiangmuId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPhoneNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsLogin(cursor.getShort(offset + 2) != 0);
        entity.setLouPan(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassword(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setXiangmuId(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
