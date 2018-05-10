package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.p2p.core.P2PHandler;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerCameraVideoComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraVideoContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraVideoModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraVideoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description 录像设置的界面
 * @date 2017/06/21 10:22:39
 */

public class CameraVideoActivity extends BaseActivity implements CameraVideoContract.View, CompoundButton.OnCheckedChangeListener{

    @Inject
    CameraVideoPresenter mPresenter;
    @BindView(R.id.switch_video)
    Switch switchVideo;

    private String deviceId, devicePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deviceId = bundle.getString("deviceId");
        devicePwd = bundle.getString("devicePwd");
        //P2PHandler.getInstance().setRecordType(deviceId,devicePwd,0);
        P2PHandler.getInstance().getNpcSettings(deviceId,devicePwd);
        switchVideo.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera_video);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setupActivityComponent() {
       DaggerCameraVideoComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .cameraVideoModule(new CameraVideoModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(CameraVideoContract.CameraVideoContractPresenter presenter) {
        mPresenter = (CameraVideoPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            P2PHandler.getInstance().setRecordType(deviceId,devicePwd,0);
        } else {
            P2PHandler.getInstance().setRecordType(deviceId,devicePwd,1);
        }
        P2PHandler.getInstance().getNpcSettings(deviceId,devicePwd);
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void SettingEventBus(Object state) {
//        if (state.toString().equals("1")){
//            switchVideo.setChecked(true);
//        }else {
//            switchVideo.setChecked(false);
//        }
//        ToastUtil.show(this, "设置成功");
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void SettingEventBus(String state) {
        if (state.equals("recordType0")){
            switchVideo.setChecked(true);
        }else if (state.equals("recordType1")){
            switchVideo.setChecked(false);
        }
        ToastUtil.show(this, "设置成功");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}