package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.p2p.core.P2PHandler;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerCameraSettingComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraSettingContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraSettingModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraSettingPresenter;
import com.yxld.yxchuangxin.yoosee.CheckUpdate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:21:20
 */

public class CameraSettingActivity extends BaseActivity implements CameraSettingContract.View, OnItemClickListener {

    @Inject
    CameraSettingPresenter mPresenter;
    @BindView(R.id.camera_header)
    ImageView cameraHeader;
    @BindView(R.id.camera_deviceId)
    TextView cameraDeviceId;
    @BindView(R.id.camera_deviceName)
    TextView cameraDeviceName;
    @BindView(R.id.s_baojin)
    TextView sBaojin;
    @BindView(R.id.s_tianjia)
    TextView sTianjia;
    @BindView(R.id.s_update)
    TextView sUpdate;
    @BindView(R.id.s_video)
    TextView sVideo;
    @BindView(R.id.s_firmware)
    TextView sFirmware;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.video_size)
    TextView videoSize;
    private AlertView mAlertViewExt;
    private AlertView mAlertView;
    private InputMethodManager imm;
    private String new_pwd, deviceId, devicePwd, deviceName, filepath; //输入的新密码  设备号  设备密码 设备名称 文件地址

    private EditText password;
    private EditText new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera_setting);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deviceId = bundle.getString("deviceId");
        devicePwd = bundle.getString("devicePwd");
        deviceName = bundle.getString("deviceName");
        filepath = Environment.getExternalStorageDirectory() + "/screenshot/tempHead/" + mPresenter.getUserID() + "/" + deviceId + ".jpg";
        cameraDeviceId.setText("设备ID:" + deviceId);
        cameraDeviceName.setText("设备名称:" + deviceName);
        // TODO: 2017/11/28 保存声音的设置
        // seekBar.setProgress(bundle.getInt("videoVolume"));
        seekBar.setProgress(sp.getInt("videoVolume", 0));
        videoSize.setText("(" + sp.getInt("videoVolume", 0) + ")");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                KLog.i("开始设置");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                KLog.i("结束设置");
                P2PHandler.getInstance().setVideoVolume(deviceId, devicePwd, seekBar.getProgress());
                videoSize.setText("(" + seekBar.getProgress() + ")");
                sp.edit().putInt("videoVolume", seekBar.getProgress()).apply();
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCameraSettingComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cameraSettingModule(new CameraSettingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CameraSettingContract.CameraSettingContractPresenter presenter) {
        mPresenter = (CameraSettingPresenter) presenter;
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
    public void setNewPassWord() {
        //设置设备的密码

        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        AppConfig.getInstance().mAppActivityManager.finishActivity(CameraActivity.class);
        AppConfig.getInstance().mAppActivityManager.finishActivity(DeviceActivity.class);
    }

    @OnClick({R.id.s_baojin, R.id.s_tianjia, R.id.s_update, R.id.s_video, R.id.s_firmware})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.s_baojin:
                if (deviceId.equals("") && devicePwd.equals("")) {
                    Toast.makeText(this, "请先选择设备进行连接", Toast.LENGTH_SHORT).show();
                } else {
                    Intent learn = new Intent(this, AlarmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", deviceId);
                    bundle.putString("devicePwd", devicePwd);
                    learn.putExtras(bundle);
                    startActivity(learn);
                }
                break;
            case R.id.s_tianjia:
                //添加传感器
                if (deviceId.equals("") && devicePwd.equals("")) {
                    Toast.makeText(this, "请先选择设备进行连接", Toast.LENGTH_SHORT).show();
                } else {
                    Intent learn = new Intent(this, LearnActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", deviceId);
                    bundle.putString("devicePwd", devicePwd);
                    learn.putExtras(bundle);
                    startActivity(learn);
                }
                break;
            case R.id.s_update:
                //显示修改密码的弹窗
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mAlertViewExt = new AlertView("修改密码", null, "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
                ViewGroup layout = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_cameras_dialog, null);
                //  对布局中的控件监听
                password = (EditText) layout.findViewById(R.id.password);
                new_password = (EditText) layout.findViewById(R.id.new_password);
                password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean focus) {
                        //输入框出来则往上移动
                        boolean isOpen = imm.isActive();
                        mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                        System.out.println(isOpen);
                    }
                });
                new_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean focus) {
                        //输入框出来则往上移动
                        boolean isOpen = imm.isActive();
                        mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                        System.out.println(isOpen);
                    }
                });
                mAlertViewExt.addExtView(layout);
                mAlertViewExt.show();
                break;
            case R.id.s_video://录像设置
                Intent video = new Intent(this, CameraVideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("deviceId", deviceId);
                bundle.putString("devicePwd", devicePwd);
                video.putExtras(bundle);
                startActivity(video);
                break;
            case R.id.s_firmware://固件更新
                P2PHandler.getInstance().checkDeviceUpdate(deviceId, devicePwd);
                break;
        }
    }

    //修改密码点击事件
    @Override
    public void onItemClick(Object o, int position) {
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if (o == mAlertViewExt && position != AlertView.CANCELPOSITION) {
            closeKeyboard();
            new_pwd = new_password.getText().toString();
            if (new_pwd.isEmpty()) {
                Toast.makeText(this, "请输入好密码和新密码", Toast.LENGTH_SHORT).show();
                return;
            }
            String pwd = P2PHandler.getInstance().EntryPassword(new_pwd);//经过转换后的设备密码
            String ordpwd = P2PHandler.getInstance().EntryPassword(devicePwd);//经过转换后的设备密码
            P2PHandler.getInstance().setDevicePassword(deviceId, ordpwd, pwd, new_pwd, new_pwd);

//            if (pwd.isEmpty() && new_pwd.isEmpty()) {
//                Toast.makeText(this, "请输入好密码和新密码", Toast.LENGTH_SHORT).show();
//            } else {
//                Map<String, String> map = new HashMap<>();
//                map.put("sb.sb_ipc_id", deviceId);
//                map.put("sb.sb_ipc_pwd", new_pwd);
//                map.put("uuid", Contains.uuid);
//                mPresenter.getCameraUpdate(map);
//            }
        } else if (o == mAlertView && position != AlertView.CANCELPOSITION) {
            P2PHandler.getInstance().doDeviceUpdate(deviceId, devicePwd);
        }
    }

    /**
     * 设置摄像头信息的回调
     * SetDevicePassword0 为修改成功
     * SetDevicePassword1 为修改失败
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateEventBus(String message) {
        KLog.i(message+"------------------------------");
        if (message.equals("SetDevicePassword0")) {
            //?sb.sb_ipc_id=%1$s&sb.sb_ipc_pwd=%2$s&uuid=%3$s
            new_pwd = new_password.getText().toString();
            Map<String, String> map = new HashMap<>();
            map.put("sb.sb_ipc_id", deviceId);
            map.put("sb.sb_ipc_pwd", new_pwd);
            map.put("uuid", Contains.uuid);
            mPresenter.getCameraUpdate(map);

        } else if (message.equals("SetDevicePassword1")) {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    //固件更新版本
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void CheckUpdateEventBus(CheckUpdate msg) {
        if (msg.result == 1) {
            mAlertView = new AlertView("固件更新", "固件版本" + msg.cur_version + ",可更新至" + msg.upg_version, "下次再说", null, new String[]{"立即更新"}, this, AlertView.Style.Alert, this);
            mAlertView.show();
        } else {
            Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void closeKeyboard() {
        //关闭软键盘
        imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(new_password.getWindowToken(), 0);
        //恢复位置
        mAlertViewExt.setMarginBottom(0);
    }
}