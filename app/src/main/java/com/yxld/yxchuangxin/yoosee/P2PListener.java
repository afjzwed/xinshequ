package com.yxld.yxchuangxin.yoosee;

import android.content.Intent;
import android.util.Log;

import com.p2p.core.P2PInterface.IP2P;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.ui.activity.camera.CameraActivity;
//import com.yxld.yxchuangxin.activity.camera.CameraActivity;
//import com.yxld.yxchuangxin.base.AppConfig;

/**
 * Created by wzy on 2016/6/13.
 */
public class P2PListener implements IP2P {
    @Override
    public void vCalling(boolean isOutCall, String threeNumber, int type) {

    }

    @Override
    public void vReject(String deviceId, int reason_code, int exCode1, int exCode2) {
        Log.d("...", "vReject: "+reason_code);
        Intent intent = new Intent();
        intent.setAction(CameraActivity.P2P_REJECT);
        intent.putExtra("reason_code", reason_code);
        intent.putExtra("exCode1", exCode1);
        intent.putExtra("exCode2", exCode2);
        Log.d("...", "vReject: exCode1"+exCode1);
        Log.d("...", "vReject: exCode2"+exCode2);
        AppConfig.app.sendBroadcast(intent);
    }

    @Override
    public void vAccept(int type, int state) {
        Intent accept = new Intent();
        accept.setAction(CameraActivity.P2P_ACCEPT);
        accept.putExtra("type", new int[]{type, state});
        AppConfig.app.sendBroadcast(accept);
    }

    @Override
    public void vConnectReady() {
        Intent intent = new Intent();
        intent.setAction(CameraActivity.P2P_READY);
        AppConfig.app.sendBroadcast(intent);
    }

    @Override
    public void vAllarming(String srcId, int type, boolean isSupportExternAlarm, int iGroup, int iItem, boolean isSurpportDelete) {

    }

    @Override
    public void vChangeVideoMask(int state) {

    }

    @Override
    public void vRetPlayBackPos(int length, int currentPos) {

    }

    @Override
    public void vRetPlayBackStatus(int state) {

    }

    @Override
    public void vGXNotifyFlag(int flag) {

    }

    @Override
    public void vRetPlaySize(int iWidth, int iHeight) {

    }

    @Override
    public void vRetPlayNumber(int iNumber, int[] data) {

    }

    @Override
    public void vRecvAudioVideoData(byte[] AudioBuffer, int AudioLen, int AudioFrames, long AudioPTS, byte[] VideoBuffer, int VideoLen, long VideoPTS) {

    }

    @Override
    public void vAllarmingWitghTime(String srcId, int type, int option, int iGroup, int iItem, int imagecounts, String imagePath, String alarmCapDir, String VideoPath, String sensorName, int deviceType) {

    }

    @Override
    public void vRetNewSystemMessage(int iSystemMessageType, int iSystemMessageIndex) {

    }

    @Override
    public void vRetRTSPNotify(int arg2, String msg) {

    }

    @Override
    public void vRetPostFromeNative(int what, int iDesID, int arg1, int arg2, String msgStr) {

    }

    @Override
    public void vRetUserData(byte cmd, byte option, int[] data) {

    }
}
