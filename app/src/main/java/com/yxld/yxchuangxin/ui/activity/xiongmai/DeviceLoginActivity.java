package com.yxld.yxchuangxin.ui.activity.xiongmai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lib.FunSDK;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.XMsxt;
import com.yxld.yxchuangxin.ui.activity.xiongmai.common.DialogWaitting;
import com.yxld.yxchuangxin.ui.activity.xiongmai.common.UIFactory;
import com.yxld.yxchuangxin.ui.activity.xiongmai.component.DaggerDeviceLoginComponent;
import com.yxld.yxchuangxin.ui.activity.xiongmai.contract.DeviceLoginContract;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.FunDevicePassword;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.FunSupport;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.OnFunDeviceListener;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevStatus;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevType;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevice;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunLoginType;
import com.yxld.yxchuangxin.ui.activity.xiongmai.module.DeviceLoginModule;
import com.yxld.yxchuangxin.ui.activity.xiongmai.presenter.DeviceLoginPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.XMAdapter;
import com.yxld.yxchuangxin.ui.adapter.wuye.SpacesItemDecoration;
import com.zbar.lib.CaptureActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: $description
 * @date 2017/07/18 09:30:54
 */

public class DeviceLoginActivity extends BaseActivity implements DeviceLoginContract.View, View.OnClickListener, OnFunDeviceListener, AdapterView.OnItemSelectedListener {

    @Inject
    DeviceLoginPresenter mPresenter;
    @BindView(R.id.reccyclerview)
    RecyclerView reccyclerview;

    private DialogWaitting mWaitDialog = null;
    private Toast mToast = null;
    private View mNavRightView = null;

    private TextView mTextTitle = null;
    private ImageButton mBtnBack = null;

    private Spinner mSpinnerDevType = null;
    private Spinner mSpinnerDevIpType = null;
    private EditText mEditDevSN;
    private EditText mEditDevLoginName;
    private EditText mEditDevLoginPasswd;
    private Button mBtnDevLogin = null;

    private EditText mEditDevIP;
    private EditText meditDevicePort;
    private EditText mEditDevIpLoginName;
    private EditText mEditDevIpLoginPasswd;
    private Button mBtnDevIpLogin = null;

    private FunDevice mFunDevice = null;
    private String mCurrDevSn = null;
    private FunDevType mCurrDevType = null;
    private FunDevType mCurrDevIpType = null;

    private ImageButton mBtnScanQrCode = null;

    private final int MESSAGE_DELAY_FINISH = 0x100;

    // 定义当前支持通过序列号登录的设备类型
    // 如果是设备类型特定的话,固定一个就可以了
    private final FunDevType[] mSupportDevTypes = {FunDevType.EE_DEV_NORMAL_MONITOR,
            FunDevType.EE_DEV_INTELLIGENTSOCKET, FunDevType.EE_DEV_SMALLEYE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_device_login);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextTitle = (TextView) findViewById(R.id.textViewInTopLayout);

        mBtnBack = (ImageButton) findViewById(R.id.backBtnInTopLayout);
        mBtnBack.setOnClickListener(this);

        // 初始化设备类型选择器
        mSpinnerDevType = (Spinner) findViewById(R.id.spinnerDeviceType);
        mSpinnerDevIpType = (Spinner) findViewById(R.id.spinnerDeviceIpType);
        String[] spinnerStrs = new String[mSupportDevTypes.length];
        for (int i = 0; i < mSupportDevTypes.length; i++) {
            spinnerStrs[i] = getResources().getString(mSupportDevTypes[i].getTypeStrId());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                spinnerStrs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerDevType.setAdapter(adapter);
        mSpinnerDevType.setSelection(0);
        mCurrDevType = mSupportDevTypes[0];
        mSpinnerDevType.setOnItemSelectedListener(this);
        mSpinnerDevIpType.setAdapter(adapter);
        mSpinnerDevIpType.setSelection(0);
        mCurrDevIpType = mSupportDevTypes[0];
        mSpinnerDevIpType.setOnItemSelectedListener(this);

        mEditDevSN = (EditText) findViewById(R.id.editDeviceSN);
        mEditDevLoginName = (EditText) findViewById(R.id.editDeviceLoginName);
        mEditDevLoginPasswd = (EditText) findViewById(R.id.editDeviceLoginPasswd);
        mBtnDevLogin = (Button) findViewById(R.id.devLoginBtn);
        mBtnDevLogin.setOnClickListener(this);

        mEditDevIP = (EditText) findViewById(R.id.editDeviceIP);
        meditDevicePort = (EditText) findViewById(R.id.editDevicePort);
        mEditDevIpLoginName = (EditText) findViewById(R.id.editDeviceIpLoginName);
        mEditDevIpLoginPasswd = (EditText) findViewById(R.id.editDeviceIpLoginPasswd);
        mBtnDevIpLogin = (Button) findViewById(R.id.devLoginBtnIP);
        mBtnDevIpLogin.setOnClickListener(this);

        mBtnScanQrCode = (ImageButton) findViewById(R.id.btnScanCode);
        mBtnScanQrCode.setOnClickListener(this);

        mTextTitle.setText(R.string.guide_module_title_device_sn);

        // 设置登录方式为本地登录
        FunSupport.getInstance().setLoginType(FunLoginType.LOGIN_BY_LOCAL);

        // 监听设备类事件
        FunSupport.getInstance().registerOnFunDeviceListener(this);

        mEditDevSN.setText("");
        mEditDevLoginName.setText("");
        mEditDevLoginPasswd.setText("");
        mEditDevIP.setText("");
        mEditDevIpLoginName.setText("");
        mEditDevIpLoginPasswd.setText("");
    }

    @Override
    protected void initData() {
        mPresenter.getSXT(new HashMap<String, String>());
//        KLog.i("开始查询设备状态");
//        FunSupport.getInstance().requestDeviceStatus(mCurrDevType, "03bfc0b72325ea66");
        mCurrDevSn = "03bfc0b72325ea66";
        mFunDevice = FunSupport.getInstance().buildTempDeivce(mCurrDevType, "03bfc0b72325ea66");
        FunSDK.DevSetLocalPwd(mFunDevice.getDevSn(), mFunDevice.loginName, mEditDevLoginPasswd.getText().toString().trim());
        if (null != mFunDevice) {
            // 传入用户名/密码
            mFunDevice.loginName = mEditDevLoginName.getText().toString().trim();
            mFunDevice.loginName = "admin";
            mFunDevice.loginPsw = "123456";
            //Save the password to local file 保存密码到本地
            FunDevicePassword.getInstance().saveDevicePassword(mFunDevice.getDevSn(), mFunDevice.loginPsw);
            //给录像机设置本地密码.
            FunSDK.DevSetLocalPwd(mFunDevice.getDevSn(), mFunDevice.loginName, mFunDevice.loginPsw);
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerDeviceLoginComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .deviceLoginModule(new DeviceLoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(DeviceLoginContract.DeviceLoginContractPresenter presenter) {
        mPresenter = (DeviceLoginPresenter) presenter;
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
    protected void onDestroy() {

        // 注销设备事件监听
        FunSupport.getInstance().removeOnFunDeviceListener(this);

        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtnInTopLayout: {
                // 返回/退出
                finish();
            }
            break;
            case R.id.devLoginBtn: {
                requestDeviceStatus();
            }
            break;
            case R.id.devLoginBtnIP: {
                requestDeviceIpStatus();
            }
            break;
            case R.id.btnScanCode: {
                startScanQrCode();
            }
            break;
        }
    }

    @Override
    public void onDeviceStatusChanged(FunDevice funDevice) {
        KLog.i("返回了设备的状态给activity");
        // 设备状态变化,如果是当前登录的设备查询之后是在线的,打开设备操作界面
        if (null != mCurrDevSn && mCurrDevSn.equals(funDevice.getDevSn())) {

            mFunDevice = funDevice;

            showToast(R.string.device_get_status_success);

            hideWaitDialog();

            if (funDevice.devStatus == FunDevStatus.STATUS_ONLINE) {
                // 如果设备在线,获取设备信息
                if ((funDevice.devType == null || funDevice.devType == FunDevType.EE_DEV_UNKNOWN)) {
                    funDevice.devType = mCurrDevType;
                }

                if (null != mHandler) {
                    mHandler.removeMessages(MESSAGE_DELAY_FINISH);
                    mHandler.sendEmptyMessageDelayed(MESSAGE_DELAY_FINISH, 1000);
                }
            } else {
                // 设备不在线
                showToast(R.string.device_stauts_offline);
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_DELAY_FINISH: {
                    hideWaitDialog();

                    // 启动/打开设备操作界面
                    if (null != mFunDevice) {

                        // 传入用户名/密码
                        mFunDevice.loginName = mEditDevLoginName.getText().toString().trim();
                        if (mFunDevice.loginName.length() == 0) {
                            // 用户名默认是:admin
                            mFunDevice.loginName = "admin";
                        }
                        mFunDevice.loginPsw = "123456";
                        //Save the password to local file 保存密码到本地
                        FunDevicePassword.getInstance().saveDevicePassword(mFunDevice.getDevSn(), mFunDevice.loginPsw );
                        //给录像机设置本地密码.
                        FunSDK.DevSetLocalPwd(mFunDevice.getDevSn(), mFunDevice.loginName, mFunDevice.loginPsw);
//                        setAdapter(16);
                        //跳转页面
//                        DeviceActivitys.startDeviceActivity(DeviceLoginActivity.this, mFunDevice);
                    }

//                    mFunDevice = null;
//                    finish();
                }
                break;
            }
        }

    };

    // 扫描二维码
    private void startScanQrCode() {
        Intent intent = new Intent();
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, 1);
    }

    // 设备登录
    private void requestDeviceStatus() {
        String devSN = mEditDevSN.getText().toString().trim();

        if (devSN.length() == 0) {
            showToast(R.string.device_login_error_sn);
            return;
        }

        mFunDevice = null;
        mCurrDevSn = devSN;

        showWaitDialog(R.string.device_stauts_unknown);

        FunSupport.getInstance().requestDeviceStatus(mCurrDevType, devSN);
    }

    // 设备登录  ip登录设备,我们不用
    private void requestDeviceIpStatus() {
        String devIP = mEditDevIP.getText().toString().trim();
        String devport = meditDevicePort.getText().toString().trim();
        if (devIP.length() == 0) {
            showToast(R.string.device_login_error_sn);
            return;
        }
        if (devport.length() == 0) {
            devport = "34567";
        }
        FunDevType devType = null;
        String devMac = null;
        String dev = null;
        dev = devIP + ":" + devport;
        mFunDevice = FunSupport.getInstance().buildTempDeivce(devType, devMac);
        mCurrDevSn = devIP;
        mFunDevice.devType = mCurrDevIpType;
        mFunDevice.devIp = devIP;
        mFunDevice.tcpPort = Integer.parseInt(devport);
        mFunDevice.devSn = dev;
        // 传入用户名/密码
        mFunDevice.loginName = mEditDevIpLoginName.getText().toString().trim();
        if (mFunDevice.loginName.length() == 0) {
            // 用户名默认是:admin
            mFunDevice.loginName = "admin";
        }
        mFunDevice.loginPsw = mEditDevIpLoginPasswd.getText().toString().trim();
        DeviceActivitys.startDeviceActivity(DeviceLoginActivity.this, mFunDevice);

    }

    @Override
    public void onDeviceListChanged() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeviceAddedSuccess() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeviceAddedFailed(Integer errCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeviceRemovedSuccess() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeviceRemovedFailed(Integer errCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAPDeviceListChanged() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLanDeviceListChanged() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
        if (position >= 0 && position < mSupportDevTypes.length) {
            mCurrDevType = mSupportDevTypes[position];
            mCurrDevIpType = mSupportDevTypes[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == 1 && responseCode == 1) {
            // Demo, 扫描二维码的结果
            if (null != data) {
                String deviceSn = data.getStringExtra("SN");
                if (null != deviceSn && null != mEditDevSN) {
                    mEditDevSN.setText(deviceSn);
                }
            }
        }

    }

    public void showWaitDialog() {
        if (null == mWaitDialog) {
            mWaitDialog = new DialogWaitting(this);
        }
        mWaitDialog.show();
    }

    public void showWaitDialog(int resid) {
        if (null == mWaitDialog) {
            mWaitDialog = new DialogWaitting(this);
        }
        mWaitDialog.show(resid);
    }

    public void showWaitDialog(String text) {
        if (null == mWaitDialog) {
            mWaitDialog = new DialogWaitting(this);
        }
        mWaitDialog.show(text);
    }

    public void hideWaitDialog() {
        if (null != mWaitDialog) {
            mWaitDialog.dismiss();
        }
    }

    public void showToast(String text) {
        if (null != text) {
            if (null != mToast) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public void showToast(int resid) {
        if (resid > 0) {
            if (null != mToast) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, resid, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    /**
     * 判断某个字符串是否存在于数组中
     * 用来判断该配置是否通道相关
     *
     * @param stringArray 原数组
     * @param source      查找的字符串
     * @return 是否找到
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);

        // 利用list的包含方法,进行判断
        return tempList.contains(source);
    }

    // 只有布局中有指定的标题栏的Activity才允许设置
    protected View setNavagateRightButton(int resource) {
        return setNavagateRightButton(resource, 48, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    protected View setNavagateRightButton(int resource, int witdhOfDP, int heightOfDP) {
        if (null != findViewById(R.id.layoutTop)) {
            RelativeLayout navLayout = (RelativeLayout) findViewById(R.id.layoutTop);
            if (null != mNavRightView) {
                navLayout.removeView(mNavRightView);
            }

            mNavRightView = LayoutInflater.from(this).inflate(resource, null);
            RelativeLayout.LayoutParams lp = null;

            int lpWidth = 0;
            int lpHeight = 0;
            if (witdhOfDP > 0) {
                lpWidth = UIFactory.dip2px(this, witdhOfDP);
            } else {
                lpWidth = witdhOfDP;
            }

            if (heightOfDP > 0) {
                lpHeight = UIFactory.dip2px(this, heightOfDP);
            } else {
                lpHeight = heightOfDP;
            }

            lp = new RelativeLayout.LayoutParams(
                    lpWidth, lpHeight);

            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);

            lp.rightMargin = UIFactory.dip2px(this, 5);

            navLayout.addView(mNavRightView, lp);

            return mNavRightView;
        }
        return null;
    }


    @Override
    public void setAdapter(XMsxt xMsxt) {
        List<XMsxt.DataBean.SxtBean> list = new ArrayList<>();
        for (int i = 0; i < xMsxt.getData().size(); i++) {
            if (i == 0) {
                FunDevice funDevice = FunSupport.getInstance().buildTempDeivce(FunDevType.EE_DEV_NORMAL_MONITOR, xMsxt.getData().get(0).getDevmac());
                if (null != funDevice) {
                    // 传入用户名/密码
                    funDevice.loginName = xMsxt.getData().get(0).getLoginName();
                    funDevice.loginPsw = xMsxt.getData().get(0).getLoginpsw();
                    //Save the password to local file 保存密码到本地
                    FunDevicePassword.getInstance().saveDevicePassword(funDevice.getDevSn(), funDevice.loginPsw);
                    //给录像机设置本地密码.
                    FunSDK.DevSetLocalPwd(funDevice.getDevSn(), funDevice.loginName, funDevice.loginPsw);
                }
            }
            for (XMsxt.DataBean.SxtBean sxtBean : xMsxt.getData().get(i).getSxt()) {
                list.add(sxtBean);
            }
        }
        XMAdapter xmAdapter = new XMAdapter(list);
        reccyclerview.setHasFixedSize(true);
        reccyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        reccyclerview.addItemDecoration(new SpacesItemDecoration(UIUtils.getDisplayWidth(this) / 1080 * 20));
//        xmAdapter.setNewData(data);
        reccyclerview.setAdapter(xmAdapter);
        xmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KLog.i("点击了item");
                DeviceActivitys.startDeviceActivity(DeviceLoginActivity.this, FunSupport.getInstance().findTempDevice(xmAdapter.getData().get(position).getShebeixuliehao()), position);
            }
        });
//        if (data == null || data.size() == 0) {
//            ToastUtil.show(this, "数据为空");
//            return;
//        }
    }

}