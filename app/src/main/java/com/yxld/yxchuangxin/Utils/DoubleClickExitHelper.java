package com.yxld.yxchuangxin.Utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.view.sinaview.PopMenu;


public class DoubleClickExitHelper {

    private boolean isOnKeyBacking = false;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExitHelper() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public boolean onBackKeyDown() {
        if (PopMenu.onBackPressed()) {
            return true;
        }
        if (isOnKeyBacking) {
            if (mHandler != null) {
                mHandler.removeCallbacks(onBackTimeRunnable);
            }
            // 退出
            KLog.i("退出");
            AppConfig.getInstance().mAppActivityManager.AppExit();
            return true;
        } else {
            isOnKeyBacking = true;
            ToastUtil.showShort("再按一次退出欣社区");
            if (mHandler != null) {
                mHandler.postDelayed(onBackTimeRunnable, 2000);
            }
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
        }

    };

}
