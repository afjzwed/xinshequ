package com.yxld.yxchuangxin.ui.activity.goods;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMyEvaluateComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MyEvaluateContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MyEvaluateModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MyEvaluatePresenter;
import com.yxld.yxchuangxin.ui.adapter.MyEvaluateAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/10/23 13:55:27
 */

public class MyEvaluateActivity extends BaseActivity implements MyEvaluateContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MyEvaluatePresenter mPresenter;
    @Inject
    MyEvaluateAdapter myEvaluateAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_evaluate);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        UIUtils.configSwipeRefreshLayoutColors(refreshLayout);
        refreshLayout.setOnRefreshListener(this);

    }

    private View getEmptyView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_empty_data, null);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        content.setText("您还没有过任何评价哦");
        content.setTextColor(Color.rgb(250, 63, 63));
        return view;
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myEvaluateAdapter);
        setRecyclerBottomDivider();
        mPresenter.getMyEvaluate();
    }

    private void setRecyclerBottomDivider() {
        myEvaluateAdapter.setFooterView(UIUtils.getRecyclerBottomView(this));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMyEvaluateComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .myEvaluateModule(new MyEvaluateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MyEvaluateContract.MyEvaluateContractPresenter presenter) {
        mPresenter = (MyEvaluatePresenter) presenter;
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
    public void onGetEvaluateSuccess(MyAllComment myAllComment) {
        if (myAllComment.getStatus() == 1) {
            if (myAllComment.getRows().size() == 0) {
                myEvaluateAdapter.setEmptyView(getEmptyView());
            }
            myEvaluateAdapter.setNewData(myAllComment.getRows());
            refreshLayout.setRefreshing(false);
        } else {
//            ToastUtil.show(this, myAllComment.getMSG());
            onError(myAllComment.MSG, myAllComment.status);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getMyEvaluate();
    }
}