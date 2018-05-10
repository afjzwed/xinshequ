package com.yxld.yxchuangxin.ui.activity.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PatternHelper;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerPatternProveComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.PatternProveContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.PatternProveModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.PatternProvePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yxld.yxchuangxin.ui.activity.mine.FingerProveActivity.SP_PATTERN_STATE;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2018/04/04 11:25:15
 */

public class PatternProveActivity extends BaseActivity implements PatternProveContract.View {

    @Inject
    PatternProvePresenter mPresenter;
    @BindView(R.id.pattern_indicator_view)
    PatternIndicatorView patternIndicatorView;
    @BindView(R.id.pattern_lock_view)
    PatternLockerView patternLockerView;
    @BindView(R.id.text_msg)
    TextView textMsg;
    private PatternHelper patternHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pattern_prove);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        this.patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView view) {
            }

            @Override
            public void onChange(PatternLockerView view, List<Integer> hitList) {
            }

            @Override
            public void onComplete(PatternLockerView view, List<Integer> hitList) {
                boolean isOk = isPatternOk(hitList);
                view.updateStatus(!isOk);
                patternIndicatorView.updateState(hitList, !isOk);
                updateMsg();
            }

            @Override
            public void onClear(PatternLockerView view) {
                finishIfNeeded();
            }
        });
        this.textMsg.setText("设置手势密码图案");
        this.patternHelper = new PatternHelper();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerPatternProveComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .patternProveModule(new PatternProveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PatternProveContract.PatternProveContractPresenter presenter) {
        mPresenter = (PatternProvePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    private boolean isPatternOk(List<Integer> hitList) {
        this.patternHelper.validateForSetting(hitList);
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
            sp.edit().putBoolean(SP_PATTERN_STATE, true).apply();
            finish();
        }
    }
}