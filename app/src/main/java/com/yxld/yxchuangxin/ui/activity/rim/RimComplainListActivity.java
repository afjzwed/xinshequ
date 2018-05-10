package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.paginate.Paginate;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimComplainListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimComplainAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 投诉列表
 * @date 2017/06/16
 */

public class RimComplainListActivity extends BaseActivity implements RimComplainListContract
        .View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RimComplainListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;
    @BindView(R.id.public_recylist_main_id)
    AutoLinearLayout publicRecylistMainId;

    private Paginate mPaginate;
    private boolean isLoadingMore;

    Map<String, String> paramMap = new HashMap<String, String>();
    private int page = 1;
    private int rows = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.public_recyclerview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        paramMap.put("page", page + "");
        paramMap.put("rows", rows + "");
        paramMap.put("uuId", Contains.uuid);
        mPresenter.getComplanData(paramMap, true);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimComplainListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimComplainListModule(new RimComplainListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimComplainListContract.RimComplainListContractPresenter presenter) {
        mPresenter = (RimComplainListPresenter) presenter;
    }

    @Override
    public void onRefresh() {
        paramMap.put("page", page + "");
        paramMap.put("rows", rows + "");
        paramMap.put("uuId", Contains.uuid);
        mPresenter.getComplanData(paramMap, true);
    }

    @Override
    public void setAdapter(RimComplainAdapter adapter) {
        recyclerView.setAdapter(adapter);
        initRecycleView();
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public void showLoading() {
        Log.d("geek", "showLoading: ");
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> swipeLayouts.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        Log.d("geek", "hideLoading: ");
        swipeLayouts.setRefreshing(false);
    }


    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        swipeLayouts.setOnRefreshListener(this);
        UIUtils.configRecycleView(recyclerView, new GridLayoutManager(this, 1));
        recyclerView.addOnItemTouchListener(clickListener);
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    @Override
    public void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getComplanData(paramMap, false);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(recyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
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
    public void getRimComplainDetailFinish(boolean isFinish, SJComplain data) {
        if (isFinish) {//已投诉则跳投诉详情页面
            Bundle bun = new Bundle();
            bun.putSerializable("SJComplain", data);
            startActivity(RimComplainDetailActivity.class, bun);
        } else {//未投诉则跳投诉页面
            ToastUtil.show(this, "此订单显示未投诉状态");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPaginate = null;
    }

    /**
     * recyclerView 点击事件
     */
    private OnItemChildClickListener clickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(final BaseQuickAdapter adapter, View view, final int
                position) {
        /*    Bundle bundle = new Bundle();
            bundle.putInt("complainId", mPresenter.mAdapter.getData().get(position).getComplainId
                    ());
            startActivity(RimComplainAddActivity.class, bundle);*/

      /*      SJComplain sjComplain = mPresenter.mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("SJComplain", sjComplain);
            startActivity(RimComplainDetailActivity.class, bundle);*/

            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("orderNumber", mPresenter.mAdapter.getData().get(position)
                    .getComplainOrderNumber());
            map.put("uuId", Contains.uuid);
            mPresenter.getRimComplainDetail(map);
        }
    };
}