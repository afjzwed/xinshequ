package com.yxld.yxchuangxin.ui.activity.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.fingerlogin.FingerListener;
import com.yxld.yxchuangxin.Utils.fingerlogin.JsFingerUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerFingerProveComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.FingerProveContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.FingerProveModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.FingerProvePresenter;
import com.yxld.yxchuangxin.view.FingerDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2018/04/03 10:11:18
 */

public class FingerProveActivity extends BaseActivity implements FingerProveContract.View, CompoundButton.OnCheckedChangeListener, FingerListener {
    public final static String SP_FINGER_STATE = "sp_finger_state";
    public final static String SP_PATTERN_STATE = "sp_pattern_state";

    @Inject
    FingerProvePresenter mPresenter;
    @BindView(R.id.switch_ring)
    CheckBox mSwitchRing;
    @BindView(R.id.switch_pattern)
    CheckBox mSwitchPattern;
    @BindView(R.id.artl_change)
    AutoRelativeLayout mArtlChange;
    private FingerDialog dialog;
    private JsFingerUtils jsFingerUtils;
    private int error_num = 0;//识别失败次数
    private boolean isFingerOn; //判断指纹是否开启
    private boolean isPatternOn; //判断图案是否开启
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            switch (msg.what) {
                //成功
                case 1:
                    isFingerOn = !isFingerOn;
                    mSwitchRing.setChecked(isFingerOn);
                    sp.edit().putBoolean(SP_FINGER_STATE, isFingerOn).apply();
                    error_num = 0;
                    break;
                //失败
                case 2:
                    mSwitchRing.setChecked(false);
                    sp.edit().putBoolean(SP_FINGER_STATE, false).apply();
                    break;
                case 3:
                    //失败
                    mSwitchRing.setChecked(isFingerOn);
                    sp.edit().putBoolean(SP_FINGER_STATE, isFingerOn).apply();
                    finish();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_finger_prove);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        jsFingerUtils = new JsFingerUtils(this);
        //设置指纹开关
        isFingerOn = sp.getBoolean(SP_FINGER_STATE, false);
        mSwitchRing.setChecked(isFingerOn);
        mSwitchRing.setOnCheckedChangeListener(this);
        mArtlChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerProveActivity.this, PatternCheckActivity.class);
                intent.putExtra("inter", 2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFingerProveComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fingerProveModule(new FingerProveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FingerProveContract.FingerProveContractPresenter presenter) {
        mPresenter = (FingerProvePresenter) presenter;
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

        //防止触发mSwitchRing.setChecked(isFingerOn)这个方法 触发监听
        if (!buttonView.isPressed()) {
            return;
        }
        switch (buttonView.getId()) {
            case R.id.switch_ring:
                checkFinger(isChecked);
                break;
            case R.id.switch_pattern:
                if (isPatternOn) {
                    //关闭图案验证
                    Intent intent = new Intent(this, PatternCheckActivity.class);
                    intent.putExtra("inter", 1);
                    startActivity(intent);
                } else {
                    //开启关闭图案验证
                    startActivity(PatternProveActivity.class);
                }

                break;
            default:
                break;
        }

    }

    private void checkFinger(boolean isChecke) {
        dialog = new FingerDialog(FingerProveActivity.this);
        dialog.setCancelable(false);
        dialog.setOnConfirmListener(new FingerDialog.OnConfirmListener() {
            @Override
            public void onCancel() {
                dialog.dismiss();
                jsFingerUtils.cancelListening();
                mSwitchRing.setChecked(isFingerOn);
            }

            @Override
            public void onConfirm() {

            }
        });
        dialog.show();
        dialog.setConfirmVisible();
        jsFingerUtils.startListening(FingerProveActivity.this);
    }

    @Override
    public void onStartListening() {
        dialog.setMessage("请进行指纹验证...");
    }

    @Override
    public void onStopListening() {

    }

    @Override
    public void onSuccess(FingerprintManager.AuthenticationResult result) {
        if (!isFingerOn) {
            dialog.setMessage("指纹验证成功，已开启指纹验证");
        } else {
            dialog.setMessage("指纹验证成功，已关闭指纹验证");
        }
        // 认证成功，开启指纹登录
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onFail(boolean isNormal, String info) {

        if (isNormal) {
            if (error_num == 2) {
                dialog.setErrMessageColor("指纹验证失败三次，请稍后再试");
                jsFingerUtils.cancelListening();
                handler.sendEmptyMessageDelayed(3, 2000);
            } else {

                error_num++;
                dialog.setErrMessageColor("指纹验证失败" + "请重试");

            }
            //  dialog.setMessage("\n   指纹认证失败，请稍后再试");
        } else {
            dialog.setErrMessageColor(info);
            handler.sendEmptyMessageDelayed(2, 1000);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        jsFingerUtils.cancelListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dialog != null && dialog.isShowing()) {
            jsFingerUtils.startListening(FingerProveActivity.this);
        }
        //设置图案开关
        isPatternOn = sp.getBoolean(SP_PATTERN_STATE, false);
        mSwitchPattern.setChecked(isPatternOn);
        mSwitchPattern.setOnCheckedChangeListener(this);
        if (isPatternOn) {
            mArtlChange.setVisibility(View.VISIBLE);

        } else {
            mArtlChange.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        if (errorCode == 7) {
            dialog.setErrMessageColor(errString.toString());
            handler.sendEmptyMessageDelayed(3, 1000);
        }
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}