package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.p2p.core.BasePlayBackActivity;
import com.p2p.core.P2PHandler;
import com.p2p.core.P2PView;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.entity.RecordFile;
import com.yxld.yxchuangxin.view.ProgressDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.p2p.core.P2PView.scale;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:22:52
 */

public class PlayBackActivity extends BasePlayBackActivity {

    @BindView(R.id.toolbarBusiness)
    Toolbar toolbarBusiness;
    @BindView(R.id.pview)
    P2PView pview;
    @BindView(R.id.rl_p2pview)
    RelativeLayout rlP2pview;
    //    @BindView(R.id.btn_palyback)
//    Button btnPalyback;
//    @BindView(R.id.tx_text)
//    TextView txText;
    @BindView(R.id.image_mute1)
    ImageView imageMute;
    @BindView(R.id.features)
    AutoRelativeLayout features;
    @BindView(R.id.image_link1)
    ImageView imageLink1;
    private MediaPlayer mediaPlayer;
    private RecordFile recordFile;
    private String deviceId;
    private String devicePwd;
    private boolean isMute = false;
    private AudioManager manager;
    public static String P2P_ACCEPT = "com.yxld.P2P_ACCEPT";
    public static String P2P_READY = "com.yxld.P2P_READY";
    public static String P2P_REJECT = "com.yxld.P2P_REJECT";
    public ProgressDialog progressDialog;
    public ArrayList<RecordFile> recordFileList = new ArrayList();
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        setContentView(R.layout.activity_play_back);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        toolbarBusiness.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(toolbarBusiness);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarBusiness.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
        Bundle bundle = getIntent().getBundleExtra("recordFile");
        recordFile = (RecordFile) bundle.getSerializable("file");
        recordFileList = (ArrayList) bundle.getSerializable("fileList");
        deviceId = getIntent().getStringExtra("deviceId");
        devicePwd = getIntent().getStringExtra("devicePwd");
        currentPosition = getIntent().getIntExtra("position", 0);
        Logger.d(recordFile + "=====" + deviceId + "----------" + devicePwd);
        devicePwd = P2PHandler.getInstance().EntryPassword(devicePwd);
        initp2pView();
        regFilter();
        play();
    }

//    @OnClick(R.id.btn_palyback)
//    public void onViewClicked() {
//        play();
//    }

    public void initp2pView() {
        pView = (P2PView) findViewById(R.id.pview);
        //7是设备类型(技威定义的)
        //LAYOUTTYPE_TOGGEDER 录像回放连接命令和P2P_ACCEPT、P2P_READY、P2P_REJECT等命令在同一界面
        //LAYOUTTYPE_SEPARATION 录像回放连接命令和P2P_ACCEPT、P2P_READY、P2P_REJECT等命令不在同一界面
        this.initP2PView(7, P2PView.LAYOUTTYPE_TOGGEDER);
        mediaPlayer = new MediaPlayer();
        pView.halfScreen();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCaptureScreenResult(boolean isSuccess, int prePoint) {
        KLog.i(prePoint + "prePoint--------");
    }

    @Override
    protected void onVideoPTS(long videoPTS) {
        KLog.i(videoPTS + "videoPTS--------");
    }

    @Override
    protected void onP2PViewSingleTap() {

    }


    public void regFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(P2P_REJECT);
        filter.addAction(P2P_ACCEPT);
        filter.addAction(P2P_READY);
        registerReceiver(mReceiver, filter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(P2P_ACCEPT)) {
                int[] type = intent.getIntArrayExtra("type");
                P2PView.type = type[0];
                scale = type[1];
                //  txText.append("\n 监控数据接收");
                Log.e("dxsTest", "监控数据接收:" + deviceId);
                P2PHandler.getInstance().openAudioAndStartPlaying(2);//打开音频并准备播放，calllType与call时type一致
            } else if (intent.getAction().equals(P2P_READY)) {
                // txText.append("\n 监控回放准备,开始播放");
                Log.e("dxsTest", "监控回放准备,开始播" + deviceId);
                progressDialog.hide();
                pView.sendStartBrod();
            } else if (intent.getAction().equals(P2P_REJECT)) {
                //  txText.append("\n 监控挂断");
                progressDialog.hide();
            }
        }
    };

    public void play() {
        progressDialog.show();
        String filename = recordFileList.get(currentPosition).getName();
        //录像回放连接
        P2PHandler.getInstance().playbackConnect(deviceId,
                devicePwd, filename, recordFile.getPosition(), 0, 0, 896, 896, 0);
    }

    @Override
    public int getActivityInfo() {
        return 33;
    }

    @Override
    protected void onGoBack() {

    }

    @Override
    protected void onGoFront() {

    }

    @Override
    protected void onExit() {

    }


    @Override
    public void onDestroy() {
        progressDialog.hide();
        //防止内存泄漏
        mediaPlayer.release();
        mediaPlayer = null;
        pview = null;
        unregisterReceiver(mReceiver);
        P2PHandler.getInstance().reject();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("dxsTest", "config:" + newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RelativeLayout.LayoutParams parames = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            pview.setLayoutParams(parames);//调整画布容器宽高(比例)
            toolbarBusiness.setVisibility(View.GONE);
        } else {
            //这里简写,只考虑了16:9的画面类型  大部分设备画面比例是这种
            int Heigh = UIUtils.getDisplayWidth(this) * 9 / 16;
            RelativeLayout.LayoutParams parames = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            parames.height = Heigh;
            pview.setLayoutParams(parames);
            toolbarBusiness.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private boolean isKujin;
    private boolean isPause;

    @OnClick({R.id.image_link1, R.id.camera_quality_btn1, R.id.realplay_video_btn1, R.id.image_mute1, R.id.image_screen1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_link1:
                isPause = !isPause;
                if (isPause) {
                    pausePlayBack();
                    KLog.i("暂停");
                    imageLink1.setImageResource(R.mipmap.play_full_play);
                } else {
                    startPlayBack();
                    KLog.i("播放");
                    imageLink1.setImageResource(R.mipmap.play_full_stop);
                }
                break;
//            case R.id.zhuatu1:
//                //快进
//                isKujin = !isKujin;
//                if (isKujin) {
//
//                     fastPlay(SetNoneState());
//                    KLog.i("快进" );
//                } else {
//
//                    fastPlayCancel(SetNoneState());
//                    KLog.i("取消快进");
//                }
//
//
//                break;
            case R.id.camera_quality_btn1:
                //下一个
                currentPosition++;
                KLog.i("currentPosition" + currentPosition + "---" + "recordFileList.size" + recordFileList.size());
                if (currentPosition >= recordFileList.size() - 1) {
                    currentPosition = recordFileList.size() - 1;
                    return;
                }
                //重置播放按钮状态
                imageLink1.setImageResource(R.mipmap.play_full_stop);
                isPause=false;
                next(recordFileList.get(currentPosition).getName());
                break;
            case R.id.realplay_video_btn1:
                //上一个视频
                currentPosition--;
                KLog.i("currentPosition" + currentPosition + "---" + "recordFileList.size" + recordFileList.size());
                if (currentPosition <= 0) {
                    currentPosition = 0;
                    //   ToastUtil.showShort("当前是最后一个");
                    return;
                }
                //重置播放按钮状态
                imageLink1.setImageResource(R.mipmap.play_full_stop);
                isPause=false;
                KLog.i("currentPosition" + currentPosition);
                previous(recordFileList.get(currentPosition).getName());
                //  play();
                break;
            case R.id.image_mute1:
                changeMuteState();
                break;
            case R.id.image_screen1:
                break;
        }
    }

    private void changeMuteState() {
        isMute = !isMute;
        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (manager != null) {
            if (isMute) {
                Log.d("666", "静音");
                imageMute.setImageResource(R.mipmap.camera_mute);
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            } else {
                Log.d("666", "声音 ");
                imageMute.setImageResource(R.mipmap.camera_sound);
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, manager.getStreamMaxVolume
                        (AudioManager.STREAM_MUSIC), 0);
            }
        }
    }
}