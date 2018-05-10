package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMarketComplainListComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainListContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketComplainListModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketComplainListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MarketSuggestListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/11/02 14:28:33
 */

public class MarketComplainListActivity extends BaseActivity implements MarketComplainListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MarketComplainListPresenter mPresenter;
    @Inject
    MarketSuggestListAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout mSwipRefresh;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_market_suggest_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mSwipRefresh.setOnRefreshListener(this);
        UIUtils.configSwipeRefreshLayoutColors(mSwipRefresh);
        mPresenter.getSuggestListFromSever();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMarketComplainListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .marketComplainListModule(new MarketComplainListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MarketComplainListContract.MarketComplainListContractPresenter presenter) {
        mPresenter = (MarketComplainListPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }
    private View getEmptyView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_empty_data, null);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        content.setText("暂无数据");
        return view;
    }
    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void loadSuggestListSuccess(MallOrderSuggest mallOrderSuggest) {
        mSwipRefresh.setRefreshing(false);
        if (mallOrderSuggest.getStatus() == 1) {
            if (mallOrderSuggest.getRows().size() == 0) {
                mAdapter.setEmptyView(getEmptyView());
            }
            mAdapter.setNewData(mallOrderSuggest.getRows());
        } else {
            onError(mallOrderSuggest.getMSG(),mallOrderSuggest.getStatus());
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getSuggestListFromSever();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera_device, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, MarketJainyiActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == REQUEST_CODE) {
            mPresenter.getSuggestListFromSever();
        }

    }
}