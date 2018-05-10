package com.yxld.yxchuangxin.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * @author zhaoyun
 * @desc 功能描述
 * @date 2016/7/20 15:06
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements ActivityDelegate {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((AppConfig) getApplication()).mAppActivityManager.addActivity(this);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();

    @Override
    public BaseAppCompatActivity getContainerActivity() {
        return this;
    }

    @Override
    public boolean isContainerDead() {
        if (Build.VERSION.SDK_INT > 16) {
            return this.isDestroyed();
        } else {
            return this.isFinishing();
        }
    }

    @Override
    public void destoryContainer() {
        finish();
    }

    @Override
    protected void onDestroy() {
//        ((YouhuaApplication) getApplication()).mAppActivityManager.removeActivity(this);
        super.onDestroy();
    }
}
