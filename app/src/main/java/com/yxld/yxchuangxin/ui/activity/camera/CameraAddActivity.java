package com.yxld.yxchuangxin.ui.activity.camera;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.p2p.core.P2PHandler;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerCameraAddComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraAddContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraAddModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraAddPresenter;
import com.yxld.yxchuangxin.view.DeleteEditText;
import com.yxld.yxchuangxin.yoosee.CheckEvent;
import com.yxld.yxchuangxin.yoosee.InitPasswordEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:21:55
 */

public class CameraAddActivity extends BaseActivity implements CameraAddContract.View {

    @Inject
    CameraAddPresenter mPresenter;
    @BindView(R.id.device_name)
    DeleteEditText deviceName;
    @BindView(R.id.device_id)
    DeleteEditText deviceId;
    @BindView(R.id.device_pwd)
    DeleteEditText devicePwd;
    @BindView(R.id.reset_device_pwd)
    DeleteEditText resetDevicePwd;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    private String ip;
    private String contactId;
    private String frag;
    private String ipFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera_add);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EventBus.getDefault().register(this);
        if (getIntent().getBooleanExtra("ishasContactId", false)) {
            ip = getIntent().getStringExtra("ip");
            contactId = getIntent().getStringExtra("contactId");
            frag = getIntent().getStringExtra("frag");
            ipFlag = getIntent().getStringExtra("ipFlag");
            if (frag.equals("0")) {
                resetDevicePwd.setVisibility(View.VISIBLE);
                tvReset.setVisibility(View.VISIBLE);
            } else {

            }
            deviceId.setText(contactId);
        }
    }

    /**
     * 根据返回码判断返回的情况
     * 具体的码值参考：http://doc.cloud-links.net/SDK/Android/P2PCore/com/p2p/core/P2PHandler.html
     * @param checkEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void CameraEventBus(CheckEvent checkEvent) {
        switch (checkEvent.result) {
            case 9999:
                progressDialog.hide();
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                break;
            case 9998:
                progressDialog.hide();
                Toast.makeText(this, "网络连接错误", Toast.LENGTH_SHORT).show();
                break;
            case 9997:
                Toast.makeText(this, "密码正确", Toast.LENGTH_SHORT).show();
                Map<String, String> map = new HashMap<String, String>();
                map.put("sb_name", deviceName.getText().toString());//用户自定义设备名称
                map.put("sb_zhanghao", "");//设备账号
                map.put("sb_ipc_id", deviceId.getText().toString());//设备ID
                map.put("sb_ipc_pwd", devicePwd.getText().toString());//设备密码
                map.put("apptoken", Contains.uuid);//用户TOKEN
                mPresenter.cameraAdd(map);
                AppConfig.getInstance().mAppActivityManager.finishActivity(CameraConfigActivity.class);
                break;
            case 9996:
                progressDialog.hide();
                Toast.makeText(this, "访客密码正确", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 初始化密码的返回,返回码同上
     * @param checkEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void CameraEventBusInitPassWord(InitPasswordEvent checkEvent) {
        switch (checkEvent.result) {
            case 9999:
                progressDialog.hide();
                break;
            case 9998:
                progressDialog.hide();
                Toast.makeText(this, "网络连接错误", Toast.LENGTH_SHORT).show();
                break;
            case 9997:
                Toast.makeText(this, "密码设置成功", Toast.LENGTH_SHORT).show();
                progressDialog.show();
                String callID = deviceId.getText().toString().trim();//设备号
                String pwd = P2PHandler.getInstance().EntryPassword(devicePwd.getText().toString().trim());//经过转换后的设备密码
                KLog.i("确认密码");
                P2PHandler.getInstance().checkPassword(callID, pwd);
                break;
            case 9996:
                progressDialog.hide();
                Toast.makeText(this, "访客密码正确", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCameraAddComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cameraAddModule(new CameraAddModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CameraAddContract.CameraAddContractPresenter presenter) {
        mPresenter = (CameraAddPresenter) presenter;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.camera_save:
                if (deviceId.getText().toString().trim().equals("") ||devicePwd.getText().toString().trim().equals("") ||deviceName.getText().toString().trim().equals("")) {
                    ToastUtil.show(this, "请完善信息");
                    return true;
                }
                progressDialog.show();
                String callID = deviceId.getText().toString().trim();//设备号
                String pwd = P2PHandler.getInstance().EntryPassword(devicePwd.getText().toString().trim());//经过转换后的设备密码
                if (getIntent().getBooleanExtra("ishasContactId", false) || (getIntent().getBooleanExtra("ishasContactId", false) && frag.equals("0"))) {
                    KLog.i("设置初始密码");
                    P2PHandler.getInstance().setInitPassword(ipFlag, pwd, "", "");
                } else {
                    KLog.i("确认密码");
                    P2PHandler.getInstance().checkPassword(callID, pwd);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}