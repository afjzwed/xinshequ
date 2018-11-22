package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerRecommendMemberComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.RecommendMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.RecommendMemberModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.RecommendMemberPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 推荐候选人成员
 * @date 2018/11/07 19:36:36
 */

public class RecommendMemberActivity extends BaseActivity implements RecommendMemberContract.View {

    @Inject
    RecommendMemberPresenter mPresenter;
    @BindView(R.id.title_recommend_member)
    TextView titleRecommendMember;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.bt_recommend_member)
    Button btRecommendMember;

    private YwhCurrentflow.DataBean.FlowBean.GongshiBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recommend_member);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = getIntent().getParcelableExtra("ywh_gongshi");

        // TODO: 2018/11/10 根据上级跳转页面显示推荐筹备组成员或候选人成员

        titleRecommendMember.setText("" + data.getTitle());
        tvSendTime.setText("发布时间：" + data.getStarttime());
        tvNotice.setText("" + data.getContent());
    }

    @Override
    protected void initData() {
        //网络请求 获得数据
//        mPresenter.getData(null);
    }

    @Override
    public void setData(BaseEntity baseEntity) {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerRecommendMemberComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .recommendMemberModule(new RecommendMemberModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RecommendMemberContract.RecommendMemberContractPresenter presenter) {
        mPresenter = (RecommendMemberPresenter) presenter;
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
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @OnClick(R.id.bt_recommend_member)
    public void onViewClicked() {
//        Toast.makeText(this, "推荐筹备组成员", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HouxuanListActivity.class);
        startActivity(intent);
    }
}