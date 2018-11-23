package com.yxld.yxchuangxin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.orhanobut.logger.Logger;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainActivity;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FixListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.OpinionSurveyActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.YeWeiHuiActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/12.
 */

public class MyALYReceiver extends MessageReceiver {



    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        KLog.i("Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
        receivingNotification(context, title, summary, extraMap);
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        KLog.i("onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        KLog.i("onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        openNotification(context, extraMap);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        KLog.i( "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
       openNotification(context, extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        KLog.i( "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        KLog.i( "onNotificationRemoved");
    }


    private void receivingNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        KLog.i(TAG, " title : " + title);
        KLog.i(TAG, "message : " + summary);
        KLog.i(TAG, "extras : " + extraMap);
        KLog.i("title : " + title);
        KLog.i("message : " + summary);
        KLog.i("extras : " + extraMap);
        Log.d("geek", "openNotification: extras" + extraMap.toString());
        String customs = extraMap.get("custom");
        //  Log.e(TAG,customs);
//        Intent intent = new Intent();
//        try {
//            JSONObject extrasJson = new JSONObject(extraMap);
//            customs = extrasJson.optString("custom");
//            Log.d("geek", "openNotification: customs" + customs.toString());
//        } catch (Exception e) {
//            Logger.w(TAG, "Unexpected: extras is not a valid json", e);
//            return;
//        }
        if (customs != null && "tongzhi".equals(customs)) {
            //收到新通知推送
            Log.d("geek", "onNotificationMessageArrived: customs=" + customs);
            EventBus.getDefault().post("通知");
        } else if (customs != null && "OUTlOGIN".equals(customs)) {
            //收到退出登录通知  移除别名
            Log.d("geek", "onNotificationMessageArrived: customs=" + customs);
            EventBus.getDefault().post("退出登录");
        }
    }

    private void openNotification(Context context, String extras) {
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
        } else if (customs != null && "order".equals(customs)) {
            intent.setClass(context, OrderListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("item", 1);
            context.startActivity(intent);
        } else if (customs != null && "tongzhi".equals(customs)) {
            intent.setClass(context, MessageActivityActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else if (customs != null && customs.contains("ywh")) {
            intent.setClass(context, YeWeiHuiActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else if (customs != null && "wj".contains(customs)) {
            intent.setClass(context, OpinionSurveyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}