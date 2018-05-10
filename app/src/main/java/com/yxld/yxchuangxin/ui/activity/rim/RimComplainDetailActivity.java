package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimComplainDetailComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainDetailModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainDetailPresenter;
import com.yxld.yxchuangxin.view.OrderComplainDetailItemView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimcomplaindetail
 * @Description: $description
 * @date 2017/12/21 15:47:26
 */

public class RimComplainDetailActivity extends BaseActivity implements RimComplainDetailContract
        .View {

    @Inject
    RimComplainDetailPresenter mPresenter;
    @BindView(R.id.tv_order_num)
    OrderComplainDetailItemView tvOrderNum;
    @BindView(R.id.tv_order_complain_time)
    OrderComplainDetailItemView tvOrderComplainTime;
    @BindView(R.id.tv_order_complain_type)
    OrderComplainDetailItemView tvOrderComplainType;
    @BindView(R.id.tv_order_complain_content)
    OrderComplainDetailItemView tvOrderComplainContent;
    @BindView(R.id.tv_order_complain_response)
    OrderComplainDetailItemView tvOrderComplainResponse;

    private List<SJComplain> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_complain_detail);//复用欣商城投诉详情布局
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            SJComplain detailBean = (SJComplain) bundle.getSerializable("SJComplain");
            if(detailBean!=null){
                SJComplain sjComplain = detailBean.getData().get(0);
                tvOrderNum.setContent(sjComplain.getComplainOrderNumber());
                tvOrderComplainContent.setContent(sjComplain.getComplainContent());
                if (sjComplain.getComplainType() == 1) {
                    tvOrderComplainType.setContent("商品");
                } else if (sjComplain.getComplainType() == 2) {
                    tvOrderComplainType.setContent("服务");
                }
                tvOrderComplainTime.setContent(sjComplain.getComplainTime());
                if (!TextUtils.isEmpty(sjComplain.getComplainReplyContent())) {
                    tvOrderComplainResponse.setContent(sjComplain.getComplainReplyContent());
                }
            }
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimComplainDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimComplainDetailModule(new RimComplainDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimComplainDetailContract.RimComplainDetailContractPresenter
                                     presenter) {
        mPresenter = (RimComplainDetailPresenter) presenter;
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
        getMenuInflater().inflate(R.menu.menu_rim_tslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.tusu:
                startActivity(RimComplainListActivity.class);
                finish();
                break;

            default:
                break;
        }
        return true;
    }

}