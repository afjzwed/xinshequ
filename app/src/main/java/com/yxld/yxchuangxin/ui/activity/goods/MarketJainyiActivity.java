package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMarketJainyiComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketJainyiContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketJainyiModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketJainyiPresenter;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainListActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 16:19:40
 */

public class MarketJainyiActivity extends BaseActivity implements MarketJainyiContract.View {

    @Inject
    MarketJainyiPresenter mPresenter;
    @BindView(R.id.tv_tousu_neirong)
    TextView tvTousuNeirong;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.et_tousu_content)
    EditText etTousuContent;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_enter_lsit)
    TextView tvEnterLsit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.fragment_market_jianyi);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etTousuContent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerMarketJainyiComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .marketJainyiModule(new MarketJainyiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MarketJainyiContract.MarketJainyiContractPresenter presenter) {
        mPresenter = (MarketJainyiPresenter) presenter;
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
    public void onJianYiBack(BaseEntity baseEntity) {
        if (baseEntity.getStatus() == 1) {
            ToastUtil.show(this, "提交成功");
            setResult(MarketComplainListActivity.REQUEST_CODE);
            finish();
        } else {
         onError(baseEntity.getMSG(),baseEntity.getStatus());
        }
    }

    @OnClick({R.id.bt_submit, R.id.tv_enter_lsit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                if (!StringUitl.isNotEmpty(this, etTousuContent.getText().toString(), "建议内容为空")) {
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("uuid", Contains.uuid);
                map.put("tsjyNeirong", etTousuContent.getText().toString());
                mPresenter.saveJianYi(map);
                break;
            case R.id.tv_enter_lsit:
                startActivity(ComplainListActivity.class);
                break;
            default:
                break;
        }
    }
}