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
 * DAO for table "SPINFO".
*/
public class SPInfoDao extends AbstractDao<SPInfo, Long> {

    public static final String TABLENAME = "SPINFO";

    /**
     * Properties of entity SPInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ProductId = new Property(1, long.class, "productId", false, "PRODUCT_ID");
        public final static Property BusinessNumber = new Property(2, String.class, "businessNumber", false, "BUSINESS_NUMBER");
        public final static Property ProductImage = new Property(3, String.class, "productImage", false, "PRODUCT_IMAGE");
        public final static Property Count = new Property(4, int.class, "count", false, "COUNT");
        public final static Property ProductPrice = new Property(5, double.class, "productPrice", false, "PRODUCT_PRICE");
        public final static Property ProductPreferentialPrice = new Property(6, double.class, "productPreferentialPrice", false, "PRODUCT_PREFERENTIAL_PRICE");
        public final static Property ProductName = new Property(7, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property ProductNum = new Property(8, int.class, "productNum", false, "PRODUCT_NUM");
    }


    public SPInfoDao(DaoConfig config) {
        super(config);
    }
    
    public SPInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SPINFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PRODUCT_ID\" INTEGER NOT NULL ," + // 1: productId
                "\"BUSINESS_NUMBER\" TEXT," + // 2: businessNumber
                "\"PRODUCT_IMAGE\" TEXT," + // 3: productImage
                "\"COUNT\" INTEGER NOT NULL ," + // 4: count
                "\"PRODUCT_PRICE\" REAL NOT NULL ," + // 5: productPrice
                "\"PRODUCT_PREFERENTIAL_PRICE\" REAL NOT NULL ," + // 6: productPreferentialPrice
                "\"PRODUCT_NAME\" TEXT," + // 7: productName
                "\"PRODUCT_NUM\" INTEGER NOT NULL );"); // 8: productNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SPINFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SPInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getProductId());
 
        String businessNumber = entity.getBusinessNumber();
        if (businessNumber != null) {
            stmt.bindString(3, businessNumber);
        }
 
        String productImage = entity.getProductImage();
        if (productImage != null) {
            stmt.bindString(4, productImage);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindDouble(6, entity.getProductPrice());
        stmt.bindDouble(7, entity.getProductPreferentialPrice());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(8, productName);
        }
        stmt.bindLong(9, entity.getProductNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SPInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getProductId());
 
        String businessNumber = entity.getBusinessNumber();
        if (businessNumber != null) {
            stmt.bindString(3, businessNumber);
        }
 
        String productImage = entity.getProductImage();
        if (productImage != null) {
            stmt.bindString(4, productImage);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindDouble(6, entity.getProductPrice());
        stmt.bindDouble(7, entity.getProductPreferentialPrice());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(8, productName);
        }
        stmt.bindLong(9, entity.getProductNum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SPInfo readEntity(Cursor cursor, int offset) {
        SPInfo entity = new SPInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // productId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // businessNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // productImage
            cursor.getInt(offset + 4), // count
            cursor.getDouble(offset + 5), // productPrice
            cursor.getDouble(offset + 6), // productPreferentialPrice
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // productName
            cursor.getInt(offset + 8) // productNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SPInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProductId(cursor.getLong(offset + 1));
        entity.setBusinessNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProductImage(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCount(cursor.getInt(offset + 4));
        entity.setProductPrice(cursor.getDouble(offset + 5));
        entity.setProductPreferentialPrice(cursor.getDouble(offset + 6));
        entity.setProductName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setProductNum(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SPInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SPInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SPInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
