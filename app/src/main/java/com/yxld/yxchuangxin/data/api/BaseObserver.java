package com.yxld.yxchuangxin.data.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yxld.yxchuangxin.entity.BaseBack;

import io.reactivex.Observer;

/**
 * 作者：hu on 2017/5/19
 * 邮箱：365941593@qq.com
 * 描述：
 */

public abstract class BaseObserver<T> implements Observer<BaseBack> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context;
    }

    @Override
    public void onNext(BaseBack value) {
//        if (value.isSuccess()) {
//            T t = value.getData();
//            onHandleSuccess(t);
//        } else {
//            onHandleError(value.getMsg());
//        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onMiaoshaComplete");
    }


    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
