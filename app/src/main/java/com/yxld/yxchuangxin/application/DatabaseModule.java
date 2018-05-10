package com.yxld.yxchuangxin.application;

import android.app.Application;

import com.yxld.yxchuangxin.data.qualifier.Remote;
import com.yxld.yxchuangxin.db.green.DaoMaster;
import com.yxld.yxchuangxin.db.green.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：Android on 2017/10/11
 * 邮箱：365941593@qq.com
 * 描述：
 */

@Module
public class DatabaseModule {
    private final Application application;

    public DatabaseModule(Application application) {
        this.application = application;
    }
    @Provides
    @Singleton
    @Remote
    public DaoSession provideDaoSession() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(application, "user-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
}
