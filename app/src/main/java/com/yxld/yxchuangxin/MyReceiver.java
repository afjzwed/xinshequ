package com.yxld.yxchuangxin;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FixListActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "jiguang";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        KLog.i(TAG, "onReceive - " + intent.getAction() + ", extras: " + bundle.toString());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            KLog.i(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            KLog.i(TAG, "接受到推送下来的自定义消息");
            openNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            KLog.i(TAG, "接受到推送下来的通知");
            receivingNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            KLog.i(TAG, "用户点击打开了通知");
            openNotification(context, bundle);
        } else {
            KLog.i(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        KLog.i(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        KLog.i(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        KLog.i(TAG, "extras : " + extras);
        KLog.i("title : " + title);
        KLog.i("message : " + message);
        KLog.i("extras : " + extras);
        Log.d("geek", "openNotification: extras" + extras.toString());
        String customs = "";
        Intent intent = new Intent();
        try {
            JSONObject extrasJson = new JSONObject(extras);
            customs = extrasJson.optString("custom");
            Log.d("geek", "openNotification: customs" + customs.toString());
        } catch (Exception e) {
            Logger.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        if (customs != null && "tongzhi".equals(customs)) {
            //收到新通知推送
            Log.d("geek", "onNotificationMessageArrived: customs=" + customs);
            EventBus.getDefault().post("通知");
        } else if (customs != null && "OUTlOGIN".equals(customs)) {
            //收到退出登录通知
            JPushInterface.setAlias(AppConfig.getInstance(), "", new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {

                }
            });
            Log.d("geek", "onNotificationMessageArrived: customs=" + customs);
            EventBus.getDefault().post("退出登录");
        }
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d("geek", "openNotification: extras" + extras.toString());
        String customs = "";
        Intent intent = new Intent();
        try {
            JSONObject extrasJson = new JSONObject(extras);
            customs = extrasJson.optString("custom");
            Log.d("geek", "openNotification: customs" + customs.toString());
        } catch (Exception e) {
            Logger.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        if (customs != null && "baoxiu".equals(customs)) {
            intent.setClass(context, FixListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else if (customs != null && "mallTousu".equals(customs)) {
            intent.setClass(context, OrderComplainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else if (customs != null && "tousu".equals(customs)) {
            intent.setClass(context, ComplainListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else if (customs != null && "baojing".equals(customs)) {
            intent.setClass(context, DeviceActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}