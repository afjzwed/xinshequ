package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mediatek.elian.ElianNative;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerCameraConfigComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraConfigContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraConfigModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraConfigPresenter;
import com.yxld.yxchuangxin.yoosee.UDPHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:21:40
 */

public class CameraConfigActivity extends BaseActivity implements CameraConfigContract.View, View.OnClickListener {

    @Inject
    CameraConfigPresenter mPresenter;
    @BindView(R.id.camera_config_name)
    TextView cameraConfigName;
    @BindView(R.id.camera_config_ssid)
    TextView cameraConfigSsid;
    @BindView(R.id.camera_config_pwd)
    EditText cameraConfigPwd;
    @BindView(R.id.camera_config_commit)
    Button cameraConfigCommit;

    private UDPHelper mHelper;
    private ElianNative elain;
    private boolean isSendWifiStop = true;
    byte type = 9;

    static {
        System.loadLibrary("elianjni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera_config);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cameraConfigCommit.setOnClickListener(this);
        getConnectWifiSsid();
        mHelper = new UDPHelper(9988);
        listen();
        mHelper.StartListen();
    }


    private void sendWifi() {
        KLog.i("开始连接");
        if (elain == null) {
            elain = new ElianNative();
        }
        String ssid = cameraConfigSsid.getText().toString();
        String pwd = cameraConfigPwd.getText().toString().trim();
        KLog.i("连接的wifi为" + ssid);
        KLog.i("连接的wifi密码为" + pwd);
        if (pwd.equals("") || ssid.equals("")) {
            Toast.makeText(CameraConfigActivity.this, "请输入连接wifi并输入密码", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            Toast.makeText(this, "发包中...请等待", Toast.LENGTH_SHORT).show();
            elain.InitSmartConnection(null, 1, 1);
            elain.StartSmartConnection(ssid, pwd, "", type);
            isSendWifiStop = false;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!isSendWifiStop) {
            stopSendWifi();
        }
    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        sendWifi();
    }

    /**
     * 停止发包
     */
    private void stopSendWifi() {
        if (elain != null) {
            Toast.makeText(this, "停止发包", Toast.LENGTH_SHORT).show();
            elain.StopSmartConnection();
            isSendWifiStop = true;
        }
    }

    private void listen() {

        mHelper.setCallBack(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch (msg.what) {
                    case UDPHelper.HANDLER_MESSAGE_BIND_ERROR:
                        progressDialog.hide();
                        Toast.makeText(CameraConfigActivity.this, "配网失败", Toast.LENGTH_SHORT).show();
                        KLog.i("配网失败");
                        break;
                    case UDPHelper.HANDLER_MESSAGE_RECEIVE_MSG:
                        //根据flag跳转到对应的界面,flag为0就是没有初始密码,为1就是有初始密码
                        String ip = msg.getData().getString("ip");
                        String contactId = msg.getData().getString("contactId");
                        String frag = msg.getData().getString("frag");
                        String ipFlag = msg.getData().getString("ipFlag");
                        progressDialog.hide();
                        Toast.makeText(CameraConfigActivity.this, "配网成功 ip:" + ip + "contactId", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CameraConfigActivity.this, CameraAddActivity.class);
                        intent.putExtra("ishasContactId", true);
                        intent.putExtra("ip", ip);
                        intent.putExtra("contactId", contactId);
                        intent.putExtra("frag", frag);
                        intent.putExtra("ipFlag", ipFlag);
                        startActivity(intent);
                        KLog.i("配网成功 ip:" + ip + "contactId:" + contactId + "frag:" + frag + "ipFlag:" + ipFlag);
                        break;
                }
            }
        });
    }

    private String getConnectWifiSsid() {
        WifiManager wifiManager = (WifiManager) getApplication().getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        KLog.i("wifiInfo", wifiInfo.toString());
        KLog.i("SSID", wifiInfo.getSSID());
        if (wifiInfo.getSSID() == null) {
            Toast.makeText(CameraConfigActivity.this, "请连接wifi后进行首次发包", Toast.LENGTH_SHORT).show();
        } else {
            String ssid = wifiInfo.getSSID();
            ssid = ssid.substring(1, ssid.length() - 1);
            cameraConfigSsid.setText(ssid);
        }

        return wifiInfo.getSSID();
    }

    //隐藏键盘
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getApplication().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCameraConfigComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cameraConfigModule(new CameraConfigModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CameraConfigContract.CameraConfigContractPresenter presenter) {
        mPresenter = (CameraConfigPresenter) presenter;
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