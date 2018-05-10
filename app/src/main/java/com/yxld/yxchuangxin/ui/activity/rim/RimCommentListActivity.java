package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.RimOrderCommentBean;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimCommentListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimCommentListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimCommentListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimCommentListAdapter;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣主编 订单评价列表
 * @date 2017/06/17
 */

public class RimCommentListActivity extends BaseActivity implements RimCommentListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RimCommentListPresenter mPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;

    private RimCommentListAdapter rimCommentListAdapter;

    private List<RimOrderCommentBean.DataBean> commentList;

    private View loadingView;//正在加载
    private View notDataView;//无数据
    private int page;//分页数
    private int rows = 6;//每页加载数
    private String orderNumber;

    private String businessNumber;//店铺id
    private double score =5;//店铺星级
    private String logo;//商家logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.rim_comment_list_layout);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderNumber = getIntent().getStringExtra("orderNumber");
        businessNumber = getIntent().getStringExtra("businessNumber");
        logo = getIntent().getStringExtra("logo");

        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
        swipeLayouts.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) recyclerView
                .getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView
                .getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


        rimCommentListAdapter = new RimCommentListAdapter(new ArrayList<>());
        rimCommentListAdapter.setEmptyView(loadingView);
        rimCommentListAdapter.setLoadMoreView(new CustomLoadMoreView());
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        rimCommentListAdapter.setOnLoadMoreListener(new BaseQuickAdapter
                .RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, recyclerView);
        recyclerView.setAdapter(rimCommentListAdapter);//绑定适配器
    }

    @Override
    protected void initData() {
        refresh();
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        page = 1;
        swipeLayouts.setRefreshing(true);//显示加载进度条.要在主线程中执行
        rimCommentListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getData(true);
    }

    /**
     * 数据请求
     */
    private void getData(boolean isRefresh) {
        Map<String, String> map = new HashMap<>();
        map.put("uuId", Contains.uuid);
        map.put("orderNumber", orderNumber);
        mPresenter.getRimCommentList(map, isRefresh);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimCommentListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimCommentListModule(new RimCommentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimCommentListContract.RimCommentListContractPresenter presenter) {
        mPresenter = (RimCommentListPresenter) presenter;
    }

    @Override
    public void setData(boolean isRefresh, RimOrderCommentBean data) {
//        Log.d("geek", "setData: recyclerView" + recyclerView);
//        Log.d("geek", "setData: data" + data.getData().toString());
//        Log.d("geek", "setData: activityAdapter" + rimCommentListAdapter.toString());
        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条


        if (null != data.getData() && data.getData().size() != 0) {
            commentList = data.getData();
        } else {
            commentList = new ArrayList<>();
        }

        page++;
        final int size = commentList == null ? 0 : commentList.size();
        if (isRefresh) {
            rimCommentListAdapter.setEnableLoadMore(true);//自动加载更多
            if (size > 0) {
                rimCommentListAdapter.setNewData(commentList);//将首次数据塞入适配器的方法
            } else {
                rimCommentListAdapter.setEmptyView(notDataView);
                rimCommentListAdapter.setNewData(new ArrayList<>());
            }
        } else {
            if (size > 0) {
                rimCommentListAdapter.addData(commentList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            rimCommentListAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            rimCommentListAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }
    }

    @Override
    public void setError() {
        rimCommentListAdapter.setEmptyView(notDataView);
        rimCommentListAdapter.setNewData(new ArrayList<>());
        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条
    }

    @Override
    public void setEmptyData(RimOrderCommentBean data) {
        swipeLayouts.setRefreshing(false);
        rimCommentListAdapter.setEmptyView(notDataView);
        rimCommentListAdapter.setNewData(new ArrayList<>());
        onError(data.MSG, data.status);
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
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rim_comment_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.comment:
                Intent intent = new Intent(this, RimShopCommentListActivity.class);
                intent.putExtra("businessNumber", businessNumber);
                intent.putExtra("score", score);
                intent.putExtra("logo", logo);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}