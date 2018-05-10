package com.yxld.yxchuangxin.ui.activity.main;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PatternHelper;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.fingerlogin.FingerListener;
import com.yxld.yxchuangxin.Utils.fingerlogin.JsFingerUtils;
import com.yxld.yxchuangxin.Utils.fingerlogin.KeyguardLockScreenManager;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerFingerLoginComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.FingerLoginContract;
import com.yxld.yxchuangxin.ui.activity.main.module.FingerLoginModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.FingerLoginPresenter;
import com.yxld.yxchuangxin.view.FingerDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity.SP_FINGER_STATE;
import static com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity.SP_PATTERN_STATE;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2018/04/03 11:46:25
 */

public class FingerLoginActivity extends BaseActivity implements FingerLoginContract.View, FingerListener {

    @Inject
    FingerLoginPresenter mPresenter;
    @BindView(R.id.text_msg)
    TextView textMsg;
    @BindView(R.id.pattern_lock_view)
    PatternLockerView patternLockerView;
    @BindView(R.id.img_finger)
    TextView mImgFinger;
    @BindView(R.id.text_fenge)
    TextView mTextFenge;
    @BindView(R.id.img_bg)
    ImageView mImgBg;
    @BindView(R.id.img_logo)
    ImageView mImgLogo;
    private FingerDialog dialog;
    private JsFingerUtils jsFingerUtils;
    private int error_num = 0;//识别失败次数
    private KeyguardLockScreenManager mKeyguardLockScreenManager;
    private PatternHelper patternHelper;

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
                    startActivity(HomeActivity.class);
                    finish();
                    break;
                //不支持指纹
                case 2:
                    sp.edit().putBoolean(SP_FINGER_STATE, false).apply();
                    break;
                //验证失败
                case 3:
                    error_num = 0;
                    jsFingerUtils.cancelListening();
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
        setContentView(R.layout.activity_finger_login);
        ButterKnife.bind(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToorBar(false);
        initBg();
        initDialog();
        if (sp.getBoolean(SP_FINGER_STATE, false)) {
            dialog.show();
            dialog.setConfirmVisible();
        } else {
            mImgFinger.setVisibility(View.GONE);
            mTextFenge.setVisibility(View.GONE);
        }
        if (sp.getBoolean(SP_PATTERN_STATE, false)) {
            this.patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
                @Override
                public void onStart(PatternLockerView view) {
                }

                @Override
                public void onChange(PatternLockerView view, List<Integer> hitList) {
                }

                @Override
                public void onComplete(PatternLockerView view, List<Integer> hitList) {
                    boolean isError = !isPatternOk(hitList);
                    view.updateStatus(isError);
                    updateMsg();
                }

                @Override
                public void onClear(PatternLockerView view) {
                    finishIfNeeded();
                }
            });

            this.textMsg.setText("绘制手势密码图案");
            this.patternHelper = new PatternHelper();
        } else {
            patternLockerView.setVisibility(View.GONE);
            textMsg.setVisibility(View.GONE);
        }

    }

    private void initBg() {
        //蒙层效果
        Glide.with(this).load(R.mipmap.bgimg).bitmapTransform(new BlurTransformation
                (this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mImgBg.setBackground(resource);
                    }
                });
        //蒙层效果
        Glide.with(this).load(R.mipmap.gyem_logo).bitmapTransform(new BlurTransformation
                (this, 15))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mImgLogo.setBackground(resource);
                    }
                });
    }

    private boolean isPatternOk(List<Integer> hitList) {
        this.patternHelper.validateForChecking(hitList);
        return this.patternHelper.isOk();
    }

    private void updateMsg() {
        this.textMsg.setText(this.patternHelper.getMessage());
        this.textMsg.setTextColor(this.patternHelper.isOk() ?
                getResources().getColor(R.color.blue) :
                getResources().getColor(R.color.red));
    }

    private void finishIfNeeded() {
        if (this.patternHelper.isFinish()) {
            if (this.patternHelper.isOk()) {
                startActivity(HomeActivity.class);
                finish();
            } else {
                this.textMsg.setText("验证手势密码图案失败");
                this.textMsg.setTextColor(getResources().getColor(R.color.red));
                ToastUtil.showShort("验证手势密码图案失败");
                patternLockerView.setEnabled(false);
                //关闭指纹登录 和手势登录
                sp.edit().putBoolean(SP_FINGER_STATE, false).apply();
                sp.edit().putBoolean(SP_PATTERN_STATE, false).apply();
                //其他登录 清除账号信息
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("NAME", "");
                editor.putString("PASSWORD", "");
                editor.putBoolean("ISCHECK", false);
                editor.putInt("xiangmuId", 0);
                editor.commit();
                startActivity(LoginActivity.class);
                finish();
            }

        }
    }

    private void initDialog() {
        dialog = new FingerDialog(this);
        dialog.setCancelable(false);
        dialog.setOnConfirmListener(new FingerDialog.OnConfirmListener() {
            @Override
            public void onCancel() {
                dialog.dismiss();
                jsFingerUtils.cancelListening();
            }

            @Override
            public void onConfirm() {

            }
        });
    }

    @Override
    protected void initData() {
        jsFingerUtils = new JsFingerUtils(this);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFingerLoginComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fingerLoginModule(new FingerLoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FingerLoginContract.FingerLoginContractPresenter presenter) {
        mPresenter = (FingerLoginPresenter) presenter;
    }


    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.img_finger, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_finger:
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                dialog.show();
                jsFingerUtils.startListening(this);
                break;
            case R.id.tv_login:
                //关闭指纹登录 和手势登录
                sp.edit().putBoolean(SP_FINGER_STATE, false).apply();
                sp.edit().putBoolean(SP_PATTERN_STATE, false).apply();
                //其他登录 清除账号信息
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("NAME", "");
                editor.putString("PASSWORD", "");
                editor.putBoolean("ISCHECK", false);
                editor.putInt("xiangmuId", 0);
                editor.commit();
                startActivity(LoginActivity.class);
                finish();
                break;
        }
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
        dialog.setMessage("指纹验证成功");
        // 认证成功，开启指纹登录
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onFail(boolean isNormal, String info) {

        KLog.i("isNormal" + isNormal);
        if (isNormal) {
            if (error_num == 5) {
                dialog.setErrMessageColor("尝试次数过多，请稍后重试");
                jsFingerUtils.cancelListening();
                handler.sendEmptyMessageDelayed(3, 1000);
            } else {
                error_num++;
                dialog.setErrMessageColor("指纹验证失败请重试");

            }
        } else {
            KLog.i(isNormal + "" + info);
            dialog.setErrMessageColor(info);
            handler.sendEmptyMessageDelayed(2, 2000);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.i("onPause");
        jsFingerUtils.cancelListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dialog != null && dialog.isShowing()) {
            jsFingerUtils.startListening(FingerLoginActivity.this);
        }

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        if (errorCode == 7) {
            KLog.i(errorCode + errString.toString() + "");
            dialog.setErrMessageColor(errString.toString());
            handler.sendEmptyMessageDelayed(3, 2000);
        }

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    private void startFingerprintRecognitionUnlockScreen() {
        if (mKeyguardLockScreenManager == null) {
            return;
        }
        if (!mKeyguardLockScreenManager.isOpenLockScreenPwd()) {
            //   toastTipMsg(R.string.fingerprint_not_set_unlock_screen_pws);
            //   FingerprintUtil.openFingerPrintSettingPage(this);
            String ACTION_SETTING = "android.settings.SETTINGS";
            Intent intent = new Intent(ACTION_SETTING);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                FingerLoginActivity.this.startActivity(intent);
            } catch (Exception e) {
            }
            return;
        }
        mKeyguardLockScreenManager.showAuthenticationScreen(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                ToastUtil.showShort("密码验证成功");
                startActivity(HomeActivity.class);
                finish();
            } else {
                ToastUtil.showShort("密码验证失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
    }
}