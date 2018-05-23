package com.yxld.yxchuangxin;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.door.InboundActivity;

import org.json.JSONException;
import org.json.JSONObject;

import jni.http.HttpManager;
import jni.http.HttpResult;
import jni.http.RtcHttpClient;
import rtc.sdk.clt.RtcClientImpl;
import rtc.sdk.common.RtcConst;
import rtc.sdk.common.SdkSettings;
import rtc.sdk.core.RtcRules;
import rtc.sdk.iface.ClientListener;
import rtc.sdk.iface.Connection;
import rtc.sdk.iface.ConnectionListener;
import rtc.sdk.iface.Device;
import rtc.sdk.iface.DeviceListener;
import rtc.sdk.iface.RtcClient;


/**
 * @author xlei
 * @Date 2018/4/19.
 */

public class HomeService extends Service {
    private static final String TAG = "HomeService";
    public static final int REGISTER_ACTIVITY_HOME = 1; //MainActivity绑定Service的消息编号
    public static final int REGISTER_ACTIVITY_INBOUND = 2; //InboundActivity绑定Service的消息编号
    public static final int REGISTER_ACTIVITY_OUTBOUND = 3; //OutboundActivity绑定Service的消息编号
    public static final int MSG_OPEN_DOOR = 20030; //开门消息编号
    public static final int MSG_ONE_OPEN_DOOR = 200333; //一键开门消息编号
    public static final int MSG_OPEN_RTC = 20031; //打开视频对讲消息编号
    public static final int MSG_REJECT_CALL = 20032; //拒绝接听消息编号
    public static final int MSG_CLOSE_CALL = 20035; //挂断接听消息编号
    public static final int MSG_OPEN_LOCK = 20033; //直接开门消息编号
    public static final int MSG_SWITCH_MIC = 20034;//切换免提
    public static final int MSG_CHECK_RTC_STATUS = 40001; //查看RTC连接状态
    public static final int MSG_CALL_INDOOR = 50001; //呼叫室内机
    public static final int MSG_CALL_ADMIN_CENTER = 50002; //呼叫管理中心

    public static final int MSG_SCAN_BLE_LOCK = 60001; //扫描蓝牙门禁设备
    public static final int MSG_OPEN_BLE_LOCK = 60002; //发送打开蓝牙门禁请求
    public static final int MSG_OPEN_BLE_LOCK_FAILED = 60003; //打开蓝牙门禁失败
    public static final int MSG_OPEN_BLE_LOCK_SUCCESS = 60004; //打开蓝牙门禁失败
    public static final int MSG_FIND_BLE_LOCK = 60005; //扫描蓝牙门禁设备
    public static final int MSG_STOP_BLE_SCAN = 60006; //扫描蓝牙门禁设备
    //rtc
    public static final String APP_ID = "71012";
    public static final String APP_KEY = "71007b1c-6b75-4d6f-85aa-40c1f3b842ef";

    private MediaPlayer ringingPlayer;//声音提示
    private Handler handler;//服务端handler
    private Messenger serviceMessenger;
    private Messenger inboundMessenger;
    private Messenger homeMessenger;
    RtcClient rtcClient = null;
    Device device = null;
    public static Connection callConnection;
    int rtcStatus = 0; //0:初始状态 1：获得账号 2：  9:失去网络 10:正常
    protected Call call = new Call();
    protected String lastOpenedCallUuid = null;
    private String username = null;
    private String token = null;
    private int micFlag = 0; //是否免提 0 听筒 1免提

    public HomeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.i(TAG, "启动HomeService");
        initHandle();
        initRingPlayer();
        if (Contains.user.getYezhuShouji() != null) {
            username = Contains.user.getYezhuShouji();
            username = "18670819116";
//            username = "17769259130";
            KLog.i(TAG, "username" + username);
            setRtcStatus(1); //设置状态，获取到用户账号
            initRtcClient();
        }
    }

    private void initRingPlayer() {
        try {
            ringingPlayer = MediaPlayer.create(this, R.raw.ring);
            //ringingPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandle() {
        KLog.i(TAG, "初始化handle");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case REGISTER_ACTIVITY_HOME:
                        homeMessenger = msg.replyTo;
                        break;
                    case REGISTER_ACTIVITY_INBOUND:
                        inboundMessenger = msg.replyTo;
                        break;
                    case MSG_OPEN_DOOR:
                        //开门
                        openDoor();
                        break;
                    case MSG_ONE_OPEN_DOOR:
                        //开门
                        openDoor("");
                        break;
                    case MSG_CLOSE_CALL:
                        //挂断
                        KLog.e(TAG, "挂断");
                        closeDial();
                        break;
                    case MSG_REJECT_CALL:
                        //拒绝
                        refuseDial();
                        break;
                    case MSG_OPEN_RTC:
                        // 打开视频或音频对讲
                        openRtc((String) msg.obj);
                        break;
                    case MSG_SWITCH_MIC:
                        // 切换免提
                        switchMic();
                        break;
                    default:
                        break;
                }

            }
        };
        serviceMessenger = new Messenger(handler);
    }

    /**
     * 切换免提
     */
    private void switchMic() {
        KLog.i(TAG, "切换免提");
        if (rtcClient != null) {
            AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
            if (micFlag == 0) {
                micFlag = 1;
                rtcClient.enableSpeaker(audioManager, true);
            } else {
                micFlag = 0;
                rtcClient.enableSpeaker(audioManager, false);
            }
        }
    }

    /**
     * 初始化rtc SDk 连接
     */
    private void initRtcClient() {
        KLog.i(TAG, "开始初始化rct SDK  initRtcClient()");
        rtcClient = new RtcClientImpl();
        rtcClient.initialize(this.getApplicationContext(), new ClientListener() {
            @Override   //初始化结果回调
            public void onInit(int result) {
                if (result == 0) {
                    setRtcStatus(2); //初始化成功
                    KLog.e(TAG, " -----------初始化rct成功-------------result=" + result + "-----------");
                    rtcClient.setAudioCodec(RtcConst.ACodec_OPUS);
                    rtcClient.setVideoCodec(RtcConst.VCodec_VP8);
                    rtcClient.setVideoAttr(RtcConst.Video_SD);
                    startGetToken();
                } else {
                    KLog.e(TAG, " -----------初始化rct失败-------------result=" + result + "-----------");
                    //onInitRtcError();
                }
            }
        });
    }

    private void startGetToken() {
        KLog.i(TAG, "开始获取Token ");
        new Thread() {
            public void run() {
                Looper.prepare();
                getTokenFromServer();
                Looper.loop();
            }
        }.start();
    }

    private void rtcRegister() {
        KLog.i(TAG, "开始注册rtc  username:" + username + "token:" + token);
        if (token != null) {
            try {
                JSONObject jargs = SdkSettings.defaultDeviceSetting();
                jargs.put(RtcConst.kAccPwd, token);
                //账号格式形如“账号体系-号码~应用id~终端类型”，以下主要设置账号内各部分内容，其中账号体系的值要在获取token之前确定，默认为私有账号
                jargs.put(RtcConst.kAccAppID, APP_ID);//应用id
                jargs.put(RtcConst.kAccUser, username); //号码
                jargs.put(RtcConst.kAccType, RtcConst.UEType_Current);//终端类型
                jargs.put(RtcConst.kAccRetry, 5);//设置重连时间
                device = rtcClient.createDevice(jargs.toString(), deviceListener);
                //注册
                KLog.i(TAG, " 设置监听 deviceListener   ");

            } catch (JSONException e) {
                KLog.i(TAG, "注册rtc失败   e:" + e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * 终端直接从rtc平台获取token，应用产品需要通过自己的服务器来获取，rtc平台接口请参考开发文档<2.5>章节.
     */
    private void getTokenFromServer() {
        KLog.i(TAG, "rtc平台获取token");
        RtcConst.UEAPPID_Current = RtcConst.UEAPPID_Self;//账号体系，包括私有、微博、QQ等，必须在获取token之前确定。
        JSONObject jsonobj = HttpManager.getInstance().CreateTokenJson(0, username, RtcHttpClient.grantedCapabiltyID,
                "");
        HttpResult ret = HttpManager.getInstance().getCapabilityToken(jsonobj, APP_ID, APP_KEY);
        onResponseGetToken(ret);
    }

    /**
     * 获取TOKEN
     */
    private void onResponseGetToken(HttpResult ret) {
        KLog.i(TAG, "rtc平台获取token 的状态  status=" + ret.getStatus());
        JSONObject jsonrsp = (JSONObject) ret.getObject();
        if (jsonrsp != null && jsonrsp.isNull("code") == false) {
            try {
                String code = jsonrsp.getString(RtcConst.kcode);
                String reason = jsonrsp.getString(RtcConst.kreason);
                Log.v("MainService", "Response getCapabilityToken code:" + code + " reason:" + reason);
                if (code.equals("0")) {
                    token = jsonrsp.getString(RtcConst.kcapabilityToken);
                    KLog.v(TAG, "获取token成功 token=" + token);
                    setRtcStatus(3); //成功获得token
                    rtcRegister();
                } else {
                    KLog.v(TAG, "获取token失败 [status:" + ret.getStatus() + "]" + ret.getErrorMsg());
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                KLog.v(TAG, "获取token失败 [status:" + e.getMessage() + "]");
            }
        } else {
        }
    }

    /**
     * 设置RTC状态
     *
     * @param status
     * @return
     */
    private synchronized int setRtcStatus(int status) {
        if (status != 0) {
            if (status != rtcStatus) {
                rtcStatus = status;
            }
        }
        return rtcStatus;
    }

    /**
     * 开门
     */
    protected void openDoor() {
        KLog.i("开门  openDoor()");
        lastOpenedCallUuid = call.imageUuid;
        stopRing();
        closeRtc();

        String userUrl = RtcRules.UserToRemoteUri_new(call.from, RtcConst.UEType_Any);
        String imageAppendValue = "";
        if (call.imageUrl != null && call.imageUrl.length() > 0) {
            imageAppendValue = "-" + call.imageUrl;
        }
        JSONObject data = new JSONObject();
        try {
            data.put("mac", "cc:4b:73:08:5d:e4");
            data.put("phone", Contains.user.getYezhuShouji());
            data.put("ka_id", "");
            data.put("kaimenfangshi", "1");
            if (call.imageUrl != null && call.imageUrl.length() > 0) {
                data.put("kaimenjietu", call.imageUrl);
            } else {
                data.put("kaimenjietu", "");
            }
            data.put("kaimenshijian", System.currentTimeMillis());
            data.put("uuid", Contains.uuid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = data.toString();
        KLog.e("-------s" + s);
        KLog.i(TAG, "userUrl" + userUrl + "---" + "open the door" + imageAppendValue);
        device.sendIm(userUrl, "text/plain", s);
        KLog.i(TAG, "发送消息device userUrl=" + userUrl + "  msg=open the door +imageAppendValue=" + imageAppendValue);
    }

    /**
     * 一键开门
     *
     * @param str
     */
    boolean isOpenDoor;

    protected void openDoor(String str) {
        isOpenDoor = true;
        String userUrl = RtcRules.UserToRemoteUri_new("cc4b73085de4", RtcConst.UEType_Any);
        if (device == null) return;
        JSONObject data = new JSONObject();
        try {
            data.put("mac", "cc:4b:73:08:5d:e4");
            data.put("phone", Contains.user.getYezhuShouji());
            data.put("ka_id", "");
            data.put("kaimenfangshi", "1");
            data.put("kaimenjietu", "");
            data.put("kaimenshijian", System.currentTimeMillis());
            data.put("uuid", Contains.uuid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = data.toString();
        device.sendIm(userUrl, "text/plain", s);
    }

    private void rtcDisconnect() {
        if (rtcClient != null) {
            rtcClient.release();
            rtcClient = null;
        }
        if (device != null) {
            device.release();
            device = null;
        }
    }

    private void rtcLogout() {
        rtcDisconnect();
        this.username = null;
    }

    /**
     * rtc收发消息监听
     */
    DeviceListener deviceListener = new DeviceListener() {
        @Override
        public void onDeviceStateChanged(int result) {
            KLog.i("注册状态 ,result=" + result);
            if (result == RtcConst.CallCode_Success) { //注销也存在此处
                setRtcStatus(10); //注册成功
                KLog.e(TAG, "-----------注册成功-------------username=" + username + "------------");
            } else if (result == RtcConst.NoNetwork) {
                KLog.i(TAG, "断网销毁，自动重连接");
            } else if (result == RtcConst.ChangeNetwork) {
                KLog.i(TAG, "网络状态改变，自动重连接");
            } else if (result == RtcConst.PoorNetwork) {
                KLog.i(TAG, "网络差，自动重连接");
            } else if (result == RtcConst.ReLoginNetwork) {
                KLog.i(TAG, " 网络原因导致多次登陆不成功，由用户选择是否继续，如想继续尝试，可以重建device");
            } else if (result == RtcConst.DeviceEvt_KickedOff) {
                KLog.i(TAG, "被另外一个终端踢下线，由用户选择是否继续，如果再次登录，需要重新获取token，重建device");
            } else if (result == RtcConst.DeviceEvt_MultiLogin) {
            } else if (result == RtcConst.CallCode_Forbidden) {
                KLog.i(TAG, "密码错误 重新注册啦 result=" + result);
            } else if (result == RtcConst.CallCode_NotFound) {
                KLog.i(TAG, "被叫号码从未获取token登录过 result=" + result);
            } else {
                KLog.i(TAG, "注册失败 result=" + result);
            }
        }

        @Override
        public void onSendIm(int nStatus) {
            if (nStatus == RtcConst.CallCode_Success) {
                KLog.i("发送IM成功" + nStatus);
                if (isOpenDoor) {
                    isOpenDoor = false;
                    ToastUtil.displayShortToast("开门成功");
                }
            } else {
                if (isOpenDoor) {
                    isOpenDoor = false;
                    ToastUtil.displayShortToast("开门失败");
                }
                KLog.i("发送IM失败" + nStatus);
            }
        }

        @Override
        public void onReceiveIm(String from, String mime, String content) {
            KLog.i("收到消息onReceiveIm:" + "from=" + from + "mine=" + mime + "content" + content);
            //收到消息onReceiveIm:442c05e69cc5cmd/json{"command":"call","from":"442c05e69cc5",
            // "imageUuid":"57fd8259-8e1b-4369-ae32-184f742bd399","communityName":"创欣物联","lockName":"01栋单元门"}
            JSONObject msg = null;
            String command = "";
            String unitName = "";
            String imageUrl = "";
            String imageUuid = "";
            String communityName = "";
            String lockName = "";
            try {
                msg = new JSONObject(content);
                command = msg.getString("command");
                try {
                    imageUrl = msg.getString("imageUrl");
                } catch (Exception e) {
                }
                try {
                    imageUuid = msg.getString("imageUuid");
                } catch (Exception e) {
                }
                try {
                    communityName = msg.getString("communityName");
                } catch (Exception e) {
                }
                try {
                    lockName = msg.getString("lockName");
                } catch (Exception e) {
                }
            } catch (JSONException e) {
            }
            if (command.equals("call")) {
                call.from = from;
                call.unitName = unitName;
                call.imageUrl = imageUrl;
                call.imageUuid = imageUuid;
                call.communityName = communityName;
                call.lockName = lockName;
                call.status = "N";
                KLog.e(TAG, "接收到呼叫来自" + from + "的呼叫消息  调用openDial（）方法进行呼叫");
                openDial();
            } else if (command.equals("cancelCall")) {
                if (call.from.equals(from)) {
                    KLog.e(TAG, "接收到呼叫来自" + from + "的取消呼叫消息  refuseDial（）方法进行拒绝");
                    refuseDial();
                }
            } else if (command.equals("appendImage")) {
                if (call.from.equals(from) && call.imageUuid.equals(imageUuid)) {
                    call.imageUrl = imageUrl;
                    //   appendImage(imageUrl);
                    KLog.i(TAG, "收到图片url 发送消息到InboundActivity");
                    sendInboundMessenge(InboundActivity.MSG_APPEND_IMAGE, imageUrl);
                } else if (imageUuid.equals(lastOpenedCallUuid)) {
                    //  sendAppendImageToServer(imageUuid, imageUrl);
                }
            }
        }

        @Override
        public void onNewCall(Connection call) {


        }

        @Override
        public void onQueryStatus(int status, String paramers) {
            // TODO Auto-generated method stub
        }
    };

    protected void appendImage(String imageUrl) {
        sendInboundMessenge(InboundActivity.MSG_APPEND_IMAGE, imageUrl);
    }

    /**
     * 接收到呼叫
     */
    protected void openDial() {
        startRing();
        startInboundActivity(call);
    }

    /**
     * 挂断
     */
    protected void closeDial() {//挂断
        KLog.i(TAG, "挂断 closeDial()");
        stopRing();
        closeRtc();
        String userUrl = RtcRules.UserToRemoteUri_new(call.from, RtcConst.UEType_Any);
        device.sendIm(userUrl, "text/plain", "reject call");
        KLog.i(TAG, "device发送消息 userUrl=" + userUrl + "  msg=" + "reject call");

    }

    /**
     * 拒绝
     */
    protected void refuseDial() {//拒绝
        stopRing();
        closeRtc();
//        String userUrl = RtcRules.UserToRemoteUri_new(call.from, RtcConst.UEType_Any);
//        device.sendIm(userUrl, "text/plain", "refuse call");
    }

    /**
     * 打开 接受视频页面
     *
     * @param call
     */
    protected void startInboundActivity(Call call) {
        if (InboundActivity.instance == null) {
            Intent intent = new Intent(getBaseContext(), InboundActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("imageUrl", call.imageUrl);
            intent.putExtra("unitName", call.unitName);
            intent.putExtra("communityName", call.communityName);
            intent.putExtra("lockName", call.lockName);
            startActivity(intent);
        }
    }

    /**
     * 关闭 接受视频页面
     */
    protected void stopInboundActivity() {
        KLog.i(TAG, "stopInboundActivity");
        if (InboundActivity.instance != null) {
            InboundActivity.instance.finish();
            InboundActivity.instance = null;
        }
        inboundMessenger = null;
    }

    /**
     * 打开视频或音频对讲
     *
     * @param type
     */

    protected void openRtc(String type) {
        KLog.i(TAG, "打开视频或音频对讲  type=" + type);
        stopRing();
        micFlag = 0;
        if (call.status.equals("N") || call.status.equals("T")) {
            JSONObject parameter = new JSONObject();
            String userUrl = RtcRules.UserToRemoteUri_new(call.from, RtcConst.UEType_Any);
            try {
                parameter.put(RtcConst.kCallRemoteUri, userUrl);
                if (type != null && type.equals("voice")) {
                    KLog.i(TAG, "音频通话");
                    parameter.put(RtcConst.kCallType, RtcConst.CallType_Audio);
                } else {
                    KLog.i(TAG, "视频通话");
                    parameter.put(RtcConst.kCallType, RtcConst.CallType_A_V);
                }
            } catch (JSONException e) {
            }
            callConnection = device.connect(parameter.toString(), connectionListener);
            if (callConnection == null) {
                closeRtc();
            }
        }
    }

    /**
     * 视频进行连接的回调
     */
    ConnectionListener connectionListener = new ConnectionListener() {
        @Override
        public void onConnecting() {
            KLog.i(TAG, "onConnecting正在进行视频或音频的连接....");
        }

        @Override
        public void onConnected() {
            KLog.i(TAG, "onConnected视频或音频的连接成功");
            if (call.status.equals("N")) {
                KLog.i(TAG, "连接完成 视频对讲");
                sendInboundMessenge(InboundActivity.MSG_RTC_CONNECTED, null);
            } else {
                KLog.i(TAG, "连接完成 音频对讲");
                //sendOutboundMessenge(OutboundActivity.MSG_RTC_CONNECTED, null);
            }
        }

        @Override
        public void onDisconnected(int code) {
            KLog.i(TAG, "onDisconnected视频或音频的连接失败 code=" + code);
            KLog.i(TAG, "onDisconnected timerDur" + callConnection.getCallDuration());
            callConnection = null;
            callingDisconnect();
            if (code != RtcConst.CallCode_Bye) {
                if (call.status.equals("N")) {

                    //  ReactBridge.sendReactMessage("onCallFailed", null);
                } else {
                    // ReactBridge.sendReactMessage("onTalkFailed", null);
                }
            }
        }

        @Override
        public void onVideo() {
            KLog.i(TAG, "onVideo");
            if (call.status.equals("N")) {
                KLog.i(TAG, "视频连接");
                sendInboundMessenge(InboundActivity.MSG_RTC_ONVIDEO_IN, null);
            } else {
                KLog.i(TAG, "音频连接");
                // sendOutboundMessenge(OutboundActivity.MSG_RTC_ONVIDEO_OUT, null);
            }
        }

        @Override
        public void onNetStatus(int msg, String info) {

        }
    };

    /**
     * 停止呼叫
     */
    protected void closeCallingConnection() {
        KLog.i("停止呼叫 closeCallingConnection()");
        if (callConnection != null) {
            callConnection.disconnect();
            callConnection = null;
            callingDisconnect();
        }
    }

    /**
     * 断开呼叫连接
     */
    private void callingDisconnect() {
        if (call.status.equals("N")) {
            KLog.i(TAG, "视频断开连接");
            sendInboundMessenge(InboundActivity.MSG_RTC_DISCONNECT, null);
        } else {
            Log.e(TAG, "音频断开连接");
            //sendOutboundMessenge(OutboundActivity.MSG_RTC_DISCONNECT, null);
        }
    }

    /**
     * 发送消息到 接受视频页面
     *
     * @param code
     * @param object
     */
    protected void sendInboundMessenge(int code, Object object) {
        if (inboundMessenger != null) {
            Message message = Message.obtain();
            message.what = code;
            message.obj = object;
            try {
                inboundMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭rtc 关闭接受视频页面
     */
    protected void closeRtc() {
        closeCallingConnection();
        if (call.status.equals("N")) {
            stopInboundActivity();
        } else {
            //  stopOutboundActivity();
        }
    }

    /**
     * 启动 响铃
     */
    protected void startRing() {
        ringingPlayer.start();
    }

    /**
     * 停止响铃
     */
    protected void stopRing() {
        ringingPlayer.pause();
        try {
            ringingPlayer.seekTo(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.i("HomeService", "onDestroy()");
        // closeCallingConnection();
        if (device != null) {
            device.release();
            device = null;
        }
        if (rtcClient != null) {
            rtcClient.release();
            rtcClient = null;
        }
        if (ringingPlayer != null) {
            ringingPlayer.release();
            ringingPlayer = null;
        }
    }

}

class Call {
    public String from = null;
    public String unitName = null;
    public String status = "N";     //区分视频还是音频  音频界面不知道有什么用 没写？？？
    public String imageUrl = null;
    public String imageUuid = null;
    public String communityName = null;
    public String lockName = null;
}