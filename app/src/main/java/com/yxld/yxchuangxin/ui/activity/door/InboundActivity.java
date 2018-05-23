package com.yxld.yxchuangxin.ui.activity.door;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.HomeService;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.ui.activity.door.component.DaggerInboundComponent;
import com.yxld.yxchuangxin.ui.activity.door.contract.InboundContract;
import com.yxld.yxchuangxin.ui.activity.door.module.InboundModule;
import com.yxld.yxchuangxin.ui.activity.door.presenter.InboundPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.door
 * @Description: $description
 * @date 2018/04/19 14:12:55
 */

public class InboundActivity extends BaseActivity implements InboundContract.View {
    private static final String TAG = "InboundActivity";
    public static final int MSG_RTC_ONVIDEO_IN = 10011;
    public static final int MSG_RTC_DISCONNECT = 10012;
    public static final int MSG_RTC_CONNECTED = 10013;
    public static final int MSG_IMG_CHANGE = 20001;
    public static final int MSG_APPEND_IMAGE = 20002;

    //public static final int MSG_CANCEL_CALL_COMPLETE=10012;

    public static final int CALL_MODE = 1;
    public static final int ONCONNECTED_MODE = 2;
    public static final int ONVIDEO_MODE = 4;
    public static final int RRC_Disconnect = 3;
    public static final int ERROR_MODE = 6;
    public static final int REFUSE_MODE = 7;

    protected Messenger serviceMessenger;
    protected Messenger inboundMessenger;
    protected Handler handler = null;

    public int currentStatus = CALL_MODE;
    public static Activity instance = null;

    TextView callingText = null;
    TextView switchMicText = null;
    ImageView guestImage = null;
    ImageView voiceImage = null;

    FrameLayout videoLayout = null;
    SurfaceView remoteView = null;
    SurfaceView localView = null;
    LinearLayout remoteLayout = null;
    LinearLayout localLayout = null;

    RelativeLayout callingLayout = null;
    RelativeLayout speakingLayout = null;
    LinearLayout openDoorLayout = null;
    LinearLayout openDoorAndCloseLayout = null;

    String imageUrl = null;
    String unitName = null;
    String communityName = null;
    String lockName = null;

    Thread checkDialStatusThread = null;
    @Inject
    InboundPresenter mPresenter;
    private boolean isStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        initScreen();
        initHandler();
        Intent intent = new Intent(InboundActivity.this, HomeService.class);
        bindService(intent, connection, 0);
        startCheckDialStatus();
    }

    protected void startCheckDialStatus() {
        checkDialStatusThread = new Thread() {
            public void run() {

                while (!isStop) {
                    try {
                        Thread.sleep(1000 * 20);
                    } catch (InterruptedException e) {
                    }
                    if (currentStatus == CALL_MODE) {
                        KLog.i(TAG, "startCheckDialStatus MSG_CLOSE_CALL");
                        sendMainMessenge(HomeService.MSG_REJECT_CALL);
                    }
                    checkDialStatusThread = null;
                }
            }
        };
        checkDialStatusThread.start();
    }

    @Override
    protected void initData() {
        Log.e(TAG, "初始化数据");
        Intent getIntent = getIntent();
        imageUrl = getIntent.getStringExtra("imageUrl");
        unitName = getIntent.getStringExtra("unitName");
        communityName = getIntent.getStringExtra("communityName");
        lockName = getIntent.getStringExtra("lockName");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.i("onDestroy");
        unbindService(connection);
        isStop=true;
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_RTC_ONVIDEO_IN) {
                    Log.e(TAG, "接收到视频呼叫");
                    onRtcVideoOn();
                } else if (msg.what == MSG_RTC_CONNECTED) {
                    Log.e(TAG, "RTC 连接");
                    onRtcConnected();
                } else if (msg.what == MSG_RTC_DISCONNECT) {
                    Log.e(TAG, "RTC 断开连接");
                    onRtcDisconnect();
                } else if (msg.what == MSG_IMG_CHANGE) {
                    KLog.i(TAG, "获取到图片bitmap");
                    Bitmap bitmap = (Bitmap) msg.obj;
                    guestImage.setImageBitmap(bitmap);
                    voiceImage.setImageBitmap(bitmap);
                } else if (msg.what == MSG_APPEND_IMAGE) {
                    KLog.i(TAG, "获取到图片地址");
                    String imageUrl = (String) msg.obj;
                    // setImageView(imageUrl);
                    KLog.i(TAG, API.PIC+ imageUrl);
                    Glide.with(InboundActivity.this).load("http://p8i51cxnv.bkt.clouddn.com/"+ imageUrl).into(guestImage);
                    Glide.with(InboundActivity.this).load("http://p8i51cxnv.bkt.clouddn.com/"+ imageUrl).into(voiceImage);
                }
            }
        };
        inboundMessenger = new Messenger(handler);
    }

    protected void sendMainMessenge(int code) {
        Message message = Message.obtain();
        message.what = code;
        try {
            Log.e(TAG, "发送主消息code1=" + code);
            serviceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void sendMainMessenge(int code, Object object) {
        Message message = Message.obtain();
        message.what = code;
        message.obj = object;
        try {
            Log.e(TAG, "发送主消息带数据的code2=" + code);
            serviceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void sendInboundMessenge(int code, Object object) {
        Message message = Message.obtain();
        message.what = code;
        message.obj = object;
        Log.e(TAG, "发送Inbound界面的消息 code=" + code);
        try {
            inboundMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取Service端的Messenger
            Log.e(TAG, "获取Service端的Messenger");
            serviceMessenger = new Messenger(service);
            Message message = Message.obtain();
            message.what = HomeService.REGISTER_ACTIVITY_INBOUND;
            message.replyTo = inboundMessenger;
            try {
                //通过ServiceMessenger将注册消息发送到Service中的Handler
                serviceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    protected void initScreen() {
        videoLayout = (FrameLayout) findViewById(R.id.videoLayout);
        remoteLayout = (LinearLayout) findViewById(R.id.remoteLayout);
        localLayout = (LinearLayout) findViewById(R.id.localLayout);
        speakingLayout = (RelativeLayout) findViewById(R.id.speakingLayout);
        callingLayout = (RelativeLayout) findViewById(R.id.callingLayout);
        callingLayout.setVisibility(View.VISIBLE);
        speakingLayout.setVisibility(View.INVISIBLE);
        callingText = (TextView) findViewById(R.id.callingText);
        callingText.setText(communityName + lockName);
        switchMicText = (TextView) findViewById(R.id.switchMicText);
        switchMicText.setText("免提");
        guestImage = (ImageView) findViewById(R.id.guestImage);
        voiceImage = (ImageView) findViewById(R.id.voiceImage);
        if (imageUrl != null && !imageUrl.equals("")) {
            //setImageView(imageUrl);
        }
        openDoorLayout = (LinearLayout) findViewById(R.id.openDoorLayout);
        ;
        openDoorAndCloseLayout = (LinearLayout) findViewById(R.id.openDoorAndCloseLayout);
        if (lockName.equals("管理中心")) {
            openDoorLayout.setVisibility(View.GONE);
            openDoorAndCloseLayout.setVisibility(View.GONE);
        } else {
            localLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(convertImageUrl(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void setImageView(final String url) {
        new Thread() {
            public void run() {
                Log.e(TAG, "根据URL获取图片");
                Bitmap bitmap = returnBitmap(url);
                if (bitmap != null) {
                    sendInboundMessenge(MSG_IMG_CHANGE, bitmap);
                }
            }
        }.start();
    }

    private String convertImageUrl(String url) {
        if (url != null && url.length() > 4 && (!url.substring(0, 4).toLowerCase().equals("http"))) {
            //url = DeviceConfig.SERVER_URL + url;
        }
        return url;
    }

    synchronized void setCurrentStatus(int status) {
        currentStatus = status;
    }

    void setTextView(int id, String txt) {
        ((TextView) findViewById(id)).setText(txt);
    }

    void initVideoViews() {
        Log.e(TAG, "初始化videoView");
        if (remoteView != null) {
            return;
        }
        if (HomeService.callConnection != null) {
            remoteView = (SurfaceView) HomeService.callConnection.createVideoView(false, this, true);
            localView = (SurfaceView) HomeService.callConnection.createVideoView(true, this, true);
        }
        remoteView.setVisibility(View.VISIBLE);
        remoteLayout.addView(remoteView);
        remoteView.setKeepScreenOn(true);
        remoteView.setZOrderMediaOverlay(true);
        remoteView.setZOrderOnTop(true);

        localView.setVisibility(View.VISIBLE);
        localLayout.addView(localView);
        localView.setKeepScreenOn(true);
        localView.setZOrderMediaOverlay(true);
        localView.setZOrderOnTop(true);

        remoteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "remoteLayout is onClick");
            }
        });

        localLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "localLayout is onClick");
            }
        });
    }

    public void onRtcVideoOn() {
        setCurrentStatus(ONVIDEO_MODE);
        initVideoViews();
        Log.e(TAG, "开始创建remoteView");
        HomeService.callConnection.buildVideo(remoteView);
        remoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "remoteView is onClick");
            }
        });
        videoLayout.setVisibility(View.VISIBLE);
    }

    public void onRtcDisconnect() {
        KLog.i("onRtcDisconnect");
        setCurrentStatus(RRC_Disconnect);
        videoLayout.setVisibility(View.GONE);
        //  close();

        if (instance != null) {
            instance.finish();
            instance = null;
        }
        // sendMainMessenge(HomeService.MSG_CLOSE_CALL);

    }

    public void onRtcConnected() {
        //setCallingText("正在通话中...");
        setCurrentStatus(ONCONNECTED_MODE);
    }

    protected void close() {
        KLog.i(TAG, "close()");
        if (instance != null) {
            instance.finish();
            instance = null;
        }
        sendMainMessenge(HomeService.MSG_CLOSE_CALL);
    }

    public void rejectCall(View view) {
//        close();
        KLog.i(TAG, "rejectCall ");
        if (instance != null) {
            instance.finish();
            instance = null;
        }
        sendMainMessenge(HomeService.MSG_REJECT_CALL);
    }

    public void rejectCall1(View view) {
//        close();
        KLog.i(TAG, "rejectCall ");
        if (instance != null) {
            instance.finish();
            instance = null;
        }
        currentStatus = REFUSE_MODE;
        sendMainMessenge(HomeService.MSG_CLOSE_CALL);
    }

    public void acceptCall(View view) {
        Log.e(TAG, "开始发送视频呼叫");
        sendMainMessenge(HomeService.MSG_OPEN_RTC, "video");
        callingLayout.setVisibility(View.GONE);//隐藏呼叫页面
        speakingLayout.setVisibility(View.VISIBLE);//
        voiceImage.setVisibility(View.GONE);//隐藏图片显示
        videoLayout.setVisibility(View.VISIBLE);
        //remoteView.setVisibility(View.VISIBLE);
        localLayout.setVisibility(View.VISIBLE);
    }

    public void acceptCallVoice(View view) {
        sendMainMessenge(HomeService.MSG_OPEN_RTC, "voice");
        callingLayout.setVisibility(View.GONE);//隐藏呼叫页面
        speakingLayout.setVisibility(View.VISIBLE);
        voiceImage.setVisibility(View.VISIBLE);//显示图片
        videoLayout.setVisibility(View.GONE);
    }

    public void openDoor(View view) {

        sendMainMessenge(HomeService.MSG_OPEN_DOOR);
        //  close();
        KLog.i(TAG, "openDoor ");
        if (instance != null) {
            instance.finish();
            instance = null;
        }
        setCurrentStatus(RRC_Disconnect);
        KLog.i(TAG, "openDoor MSG_CLOSE_CALL");
        sendMainMessenge(HomeService.MSG_CLOSE_CALL);
    }

    public void switchMic(View view) {
        if (switchMicText.getText().equals("免提")) {
            switchMicText.setText("听筒");
        } else {
            switchMicText.setText("免提");
        }
        sendMainMessenge(HomeService.MSG_SWITCH_MIC);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_inbound);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        KLog.i(TAG, "onBackPressed ");
        if (instance != null) {
            instance.finish();
            instance = null;
        }
        KLog.i(TAG, "onBackPressed MSG_CLOSE_CALL");
        sendMainMessenge(HomeService.MSG_CLOSE_CALL);
        // close();
    }


    @Override
    protected void setupActivityComponent() {
        DaggerInboundComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .inboundModule(new InboundModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(InboundContract.InboundContractPresenter presenter) {
        mPresenter = (InboundPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}