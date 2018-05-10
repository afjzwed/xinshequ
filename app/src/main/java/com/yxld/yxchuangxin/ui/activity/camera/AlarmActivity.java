package com.yxld.yxchuangxin.ui.activity.camera;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.p2p.core.P2PHandler;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerAlarmComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:23:03
 */

public class AlarmActivity extends BaseActivity implements AlarmContract.View, CompoundButton.OnCheckedChangeListener {

    @Inject
    AlarmPresenter mPresenter;
    @BindView(R.id.alarm_buzzer)
    TextView alarmBuzzer;
    /**
     * 蜂鸣器的开关
     */
    @BindView(R.id.switch_buzzer)
    Switch switchBuzzer;
    @BindView(R.id.alarm_motion)
    TextView alarmMotion;
    /**
     * 移动监测的开关
     */
    @BindView(R.id.switch_motion)
    Switch switchMotion;
    @BindView(R.id.alarm_reverse)
    TextView alarmReverse;
    /**
     * 图像翻转的开关
     */
    @BindView(R.id.switch_reverse)
    Switch switchReverse;

    private String deviceId, devicePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        deviceId = bundle.getString("deviceId");
        devicePwd = bundle.getString("devicePwd");
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    /**
     *设备信息的返回
     *Buzzer 为鸣笛
     *Motion 为移动监测
     *ImageReverse 为图像翻转
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void NpcSettingEventBus(String msg) {
        Log.d("...", "NpcSettingEventBus: " + msg);
        switch (msg) {
            case "Buzzer" + 0:
                switchBuzzer.setChecked(false);
                break;
            case "Buzzer" + 1:
                switchBuzzer.setChecked(true);
            case "Buzzer" + 2:
                switchBuzzer.setChecked(true);
            case "Buzzer" + 3:
                switchBuzzer.setChecked(true);
                break;
            case "Motion" + 0:
                switchMotion.setChecked(false);
                break;
            case "Motion" + 1:
                switchMotion.setChecked(true);
                break;
            case "ImageReverse" + 0:    //0是不翻转
                KLog.i("ImageReverse0是不翻转");
                switchReverse.setChecked(false);
                break;
            case "ImageReverse" + 1:       //1是翻转
                KLog.i("ImageReverse1是翻转");
                switchReverse
                        .setChecked(true);
                break;
            case "SetBuzzer" + 0:
                Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
                break;
            case "SetMotion" + 0:
                Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
            case "SetImageReverse" + 0:
                Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_buzzer:
                if (isChecked) {
                    P2PHandler.getInstance().setBuzzer(deviceId, devicePwd, 3);
                } else {
                    P2PHandler.getInstance().setBuzzer(deviceId, devicePwd, 0);
                }
                break;
            case R.id.switch_motion:
                if (isChecked) {
                    P2PHandler.getInstance().setMotion(deviceId, devicePwd, 1);
                } else {
                    P2PHandler.getInstance().setMotion(deviceId, devicePwd, 0);
                }
                break;
            case R.id.switch_reverse:
                if (isChecked) {
                    P2PHandler.getInstance().setImageReverse(deviceId, devicePwd, 1);
                } else {
                    P2PHandler.getInstance().setImageReverse(deviceId, devicePwd, 0);
                }
                break;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        P2PHandler.getInstance().getNpcSettings(deviceId, devicePwd);
        switchBuzzer.setOnCheckedChangeListener(this);
        switchMotion.setOnCheckedChangeListener(this);
        switchReverse.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerAlarmComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .alarmModule(new AlarmModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AlarmContract.AlarmContractPresenter presenter) {
        mPresenter = (AlarmPresenter) presenter;
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