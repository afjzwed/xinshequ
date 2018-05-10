package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMarketComplainComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketComplainModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketComplainPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;

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
 * @date 2017/06/22 15:30:24
 */

public class MarketComplainActivity extends BaseActivity implements MarketComplainContract.View, PopWindowUtil.OnSubmitClickListener {
    @Inject
    MarketComplainPresenter mPresenter;
    @BindView(R.id.tv_tousu_leixing)
    TextView tvTousuLeixing;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_pick_tousu_content)
    TextView tvPickTousuContent;
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
    private String mOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mOrderId = getIntent().getExtras().getString("orderid");
    }

    @Override
    protected void initView() {
        setContentView(R.layout.fragment_market_tousu);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));

        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etTousuContent);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMarketComplainComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .marketComplainModule(new MarketComplainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MarketComplainContract.MarketComplainContractPresenter presenter) {
        mPresenter = (MarketComplainPresenter) presenter;
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
    public void onComplainSucceed(BaseEntity entity) {
        if (entity.getStatus() == 1) {
            ToastUtil.show(getApplicationContext(), "操作成功");
//            finish();
            toListActivity();

        } else {
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onComplainFailed() {
        ToastUtil.show(MarketComplainActivity.this, getResources().getString(R.string.load_failed));
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @OnClick({R.id.tv_pick_tousu_content, R.id.bt_submit, R.id.tv_enter_lsit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pick_tousu_content:
                PopWindowUtil popWindowUtil = new PopWindowUtil();
                popWindowUtil.showMarketComplainPop(this, tvPickTousuContent);
                popWindowUtil.setOnSubmitClickListener(this);
                break;
            case R.id.bt_submit:
                toSubmit();
                break;
            case R.id.tv_enter_lsit:
                toListActivity();
                break;
            default:
                break;
        }
    }

    private void toListActivity() {
        Intent intent = new Intent(MarketComplainActivity.this, OrderComplainActivity.class);
        intent.putExtra("orderid", mOrderId);
        startActivity(intent);
        finish();

    }

    private void toSubmit() {
        /*if (StringUitl.hasEmptyItem(tvPickTousuContent.getText().toString())) {
            ToastUtil.show(MarketComplainActivity.this, "请选择投诉类型");
            return;
        }*/
        if (StringUitl.hasEmptyItem(etTousuContent.getText().toString())) {
            ToastUtil.show(MarketComplainActivity.this, "请输入投诉内容");
            return;
        }
        Map<String, String> params = new HashMap<>();
//        params.put("tsjyLeixing", "商品".equals(tvPickTousuContent.getText().toString()) ? "1" : "2");
        params.put("tsjyLeixing", "1");
        params.put("tsjyDindanBianhao", mOrderId);
        params.put("tsjyNeirong", etTousuContent.getText().toString());
        params.put("uuid", Contains.uuid);
        mPresenter.toComplain(params);

    }

    @Override
    public void onSubmitClick(View v, String tousuKind) {
        switch (v.getId()) {
            case R.id.bt1:
            case R.id.bt2:
                tvPickTousuContent.setText(tousuKind);
                CustomPopWindow.onBackPressed();
                break;
        }
        tvPickTousuContent.setText(tousuKind);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}