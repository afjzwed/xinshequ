package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.ResetPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerAccountComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AccountContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AccountModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AccountPresenter;
import com.yxld.yxchuangxin.view.AddMemberView;
import com.yxld.yxchuangxin.view.ConfirmDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity.SP_FINGER_STATE;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class AccountActivity extends BaseActivity implements AccountContract.View {

    @Inject
    AccountPresenter mPresenter;
    @BindView(R.id.zhanghao)
    AddMemberView zhanghao;
    @BindView(R.id.xingming)
    AddMemberView xingming;
    @BindView(R.id.shenfenzheng)
    AddMemberView shenfenzheng;
    @BindView(R.id.anquan)
    AddMemberView anquan;
    @BindView(R.id.gengxin)
    AddMemberView gengxin;
    @BindView(R.id.zhiwen)
    AddMemberView zhiwen;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.multi_account)
    AddMemberView multiAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_account);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  zhiwen.setLeftText(sp.getBoolean(SP_FINGER_STATE, false) ? "已开启" : "未开启");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChange(String content) {
        if (content.equals("reLogin"))
            initData();
    }


    @Override
    protected void initData() {
        if (Contains.user == null || Contains.appYezhuFangwus == null || Contains.appYezhuFangwus.size() == 0) {
            xingming.setRightText("业主信息未完善");
        } else {
            xingming.setRightText(Contains.user.getYezhuName());
        }

//        if(Contains.user == null || Contains.user.getYezhuChuangxinhao() == null || "".equals(Contains.user.getYezhuChuangxinhao())){
        zhanghao.setRightText(Contains.user.getYezhuShouji().substring(0, 3) + "****" + Contains.user.getYezhuShouji().substring(7, Contains.user.getYezhuShouji().length()));
//        }else{
//            zhanghao.setRightText(Contains.user.getYezhuChuangxinhao());
//        }
        if (Contains.user == null || Contains.user.getYezhuCardNum() == null || "".equals(Contains.user.getYezhuCardNum())) {
            shenfenzheng.setRightText("");
        } else {
            shenfenzheng.setRightText(Contains.user.getYezhuCardNum().substring(0, 6) + "************");
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAccountComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .accountModule(new AccountModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AccountContract.AccountContractPresenter presenter) {
        mPresenter = (AccountPresenter) presenter;
    }

    @OnClick({R.id.anquan, R.id.gengxin, R.id.logout, R.id.multi_account, R.id.zhiwen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.multi_account:
                startActivity(MultiAccountActivity.class);
                break;
            case R.id.anquan:
                //重新设置密码
                startActivity(ResetPasswordActivity.class);
                break;
            case R.id.gengxin:
                //进入更新页面
                startActivity(UpdateActivity.class);
                break;
            case R.id.zhiwen:
                //进入指纹验证
                startActivity(FingerProveActivity.class);
                break;
            case R.id.logout:
                //退出登录信息
                ConfirmDialog.showDialog(this, "提示", "确认退出登录吗？", new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onConfirm() {
                        Map<String, String> map = new HashMap<>();
                        map.put("shouji", Contains.user.getYezhuShouji());
                        mPresenter.getLoginOut(map);
                    }
                });
                break;
        }
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
    public void loginOut() {
//        JPushInterface.setAlias(this,"", new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//
//            }
//        });
        PushServiceFactory.getCloudPushService().removeAlias(null, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                KLog.i("阿里云推送移除别名成功" + "removeAlias success" + s);
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });

        PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                KLog.i("阿里云推送移除绑定账号成功" + "unbindAccount" + s);
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });
        CxUtil.clearData(sp);
        AppConfig.getGreenDaoSession().getUserInfoDao().deleteAll();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("flag", "other");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}