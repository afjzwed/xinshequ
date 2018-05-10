package com.yxld.yxchuangxin.ui.activity.wuye;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerUpdateComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.UpdateContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.UpdateModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.UpdatePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.R.id.update_vision;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/23 09:25:59
 */

public class UpdateActivity extends BaseActivity implements UpdateContract.View {

    @Inject
    UpdatePresenter mPresenter;
    @BindView(R.id.cur_vision)
    TextView curVision;
    @BindView(R.id.new_vision)
    TextView newVision;
    @BindView(R.id.update_vision)
    Button updateVision;
    @BindView(R.id.environment)
    View environment;
    @BindView(R.id.check_environment)
    SmoothCheckBox checkEnvironment;
    private String curVersion;

    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        curVersion = CxUtil.getVersion(this);
        curVision.setText("您的当前版本号:" + curVersion);
        checkEnvironment.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                if (b) {
                    ToastUtil.show(UpdateActivity.this, "选择线上环境");
//                    SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true);
                } else {
                    ToastUtil.show(UpdateActivity.this, "选择测试环境");
//                    SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, false);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getLastVersion();
        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCount == 0) {
                    fiveClickHandlere.sendEmptyMessageDelayed(0, 1000);
                }
                clickCount++;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (checkEnvironment.getVisibility() == View.VISIBLE) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("环境已经修改，点击确定生效");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Map<String, String> map = new HashMap<>();
                    map.put("shouji", Contains.user.getYezhuShouji());
                    mPresenter.getLoginOut(map);
                }
            });
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    Handler fiveClickHandlere = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (clickCount >= 4) {
                checkEnvironment.setVisibility(View.VISIBLE);
            }
            clickCount = 0;
        }
    };

    @Override
    protected void setupActivityComponent() {
        DaggerUpdateComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .updateModule(new UpdateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(UpdateContract.UpdateContractPresenter presenter) {
        mPresenter = (UpdatePresenter) presenter;
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
    public void setLastVersion(CxwyAppVersion version) {
        newVision.setText("最新版本号:" + version.getVer().getVersionUId());
        if (Float.valueOf(version.getVer().getVersionUId().replace(".", "")) > Float.valueOf(curVersion.replace(".", ""))) {
            updateVision.setVisibility(View.VISIBLE);
        } else {
            updateVision.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void loginOutSuccess() {
        if (checkEnvironment.isChecked()) {
            SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true);
            KLog.i("选择了线上环境，置为true");
        } else {
            SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, false);
            KLog.i("选择了开发环境，置为false");
        }
        CxUtil.clearData(sp);
        AppConfig.getInstance().mAppActivityManager.AppExit();

    }

    @OnClick(update_vision)
    public void onViewClicked() {
        mPresenter.getUpdatePermission();
    }

}