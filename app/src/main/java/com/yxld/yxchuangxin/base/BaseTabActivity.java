package com.yxld.yxchuangxin.base;

import android.app.TabActivity;
import android.os.Build;
import android.os.Bundle;

/**
 * @author zhaoyun
 * @desc 功能描述
 * @date 2016/7/13 19:23
 */
public abstract class BaseTabActivity extends TabActivity implements ActivityDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public BaseTabActivity getContainerActivity() {
        return this;
    }

    @Override
    public boolean isContainerDead() {
        if(Build.VERSION.SDK_INT > 16){
            return this.isDestroyed();
        }else{
            return this.isFinishing();
        }
    }

    @Override
    public void destoryContainer() {
        finish();
    }

    protected abstract void initTabHost();

}
