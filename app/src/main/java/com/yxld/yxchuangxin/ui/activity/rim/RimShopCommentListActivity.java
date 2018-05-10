package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.RimCommentListBean;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimShopCommentListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopCommentListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimShopCommentListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimShopCommentListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimShopCommentListAdapter;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author RimShopCommentList
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopcommentlist
 * @Description: $description
 * @date 2017/12/18 13:46:52
 */

public class RimShopCommentListActivity extends BaseActivity implements
        RimShopCommentListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RimShopCommentListPresenter mPresenter;
    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;
    @BindView(R.id.rb_shop_level)
    RatingBar rbShopLevel;
    @BindView(R.id.tv_shop_level)
    TextView tvShopLevel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;

    private RimShopCommentListAdapter rimShopCommentListAdapter;

    private View loadingView;//正在加载
    private View notDataView;//无数据
    private int page;//分页数
    private int rows = 6;//每页加载数
    private List<RimCommentListBean.DataBean.PingjiaListBean> commentList;
    private String businessNumber;//商家序列号
    private double score;//店铺星级
    private String logo;//商家logo
    private int appraiseNum;//评价总数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rim_shopcommentlist);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        businessNumber = getIntent().getStringExtra("businessNumber");
        score = getIntent().getDoubleExtra("score",5);
        logo = getIntent().getStringExtra("logo");
        appraiseNum = getIntent().getIntExtra("appraiseNum",5);

        Glide.with(this)
                .load(API.PIC + logo)
//                .placeholder(R.drawable.default_recommed_icon)
//                .error(R.drawable.default_recommed_icon)
                .crossFade()//渐入渐出
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .bitmapTransform(new CropCircleTransformation(MyApplication.context))
                .into(ivShopLogo);
        rbShopLevel.setRating((float) score);
        tvShopLevel.setText(score+"分");

        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
        swipeLayouts.setOnRefreshListener(this);
//        swipeLayouts.setRefreshing(true);
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

//        rimShopCommentListAdapter.setOnLoadMoreListener(this, recyclerView);这里注释
// 加载更多逻辑可看RimCommentListActivity
        rimShopCommentListAdapter = new RimShopCommentListAdapter();
        rimShopCommentListAdapter.setEmptyView(loadingView);
        rimShopCommentListAdapter.setLoadMoreView(new CustomLoadMoreView());
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        rimShopCommentListAdapter.setOnLoadMoreListener(new BaseQuickAdapter
                .RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, recyclerView);
        recyclerView.setAdapter(rimShopCommentListAdapter);//绑定适配器
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
        rimShopCommentListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getData(true);
    }

    /**
     * 数据请求
     */
    private void getData(boolean isRefresh) {
//        http://192.168.8.129:8080/cxwy_consumer_terminal/app/pingjialist?uuId=c8c86d0f-f4eb-40b8-ad35-e197e7e32bb0&businessNumber=1001&page=&rows  评价列表信息
        Map<String, String> map = new HashMap<>();
        map.put("uuId", Contains.uuid);
        map.put("businessNumber", businessNumber);
        map.put("page", page + "");
        map.put("rows", rows + "");
        mPresenter.getRimShopCommentList(map, isRefresh);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimShopCommentListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimShopCommentListModule(new RimShopCommentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimShopCommentListContract.RimShopCommentListContractPresenter
                                     presenter) {
        mPresenter = (RimShopCommentListPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    /**
     * 填充数据
     *
     * @param data
     */
    @Override
    public void setData(boolean isRefresh, RimCommentListBean data) {

        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条

        if (null != data.getData().getPingjiaList() && data.getData().getPingjiaList().size() != 0) {
            commentList = data.getData().getPingjiaList();
        } else {
            commentList = new ArrayList<>();
        }

        score = data.getData().getScore();
        rbShopLevel.setRating((float) score);
        tvShopLevel.setText(score+"分");

        page++;
        final int size = commentList == null ? 0 : commentList.size();
        if (isRefresh) {
            rimShopCommentListAdapter.setEnableLoadMore(true);//自动加载更多
            if (size > 0) {
                rimShopCommentListAdapter.setNewData(commentList);//将首次数据塞入适配器的方法
            } else {
                rimShopCommentListAdapter.setEmptyView(notDataView);
                rimShopCommentListAdapter.setNewData(new ArrayList<RimCommentListBean.DataBean.PingjiaListBean>());
            }
        } else {
            if (size > 0) {
                rimShopCommentListAdapter.addData(commentList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            rimShopCommentListAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            rimShopCommentListAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }
    }

    @Override
    public void setError() {
        rimShopCommentListAdapter.setEmptyView(notDataView);
        rimShopCommentListAdapter.setNewData(new ArrayList<RimCommentListBean.DataBean.PingjiaListBean>());
        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条
    }

    @Override
    public void setEmptyData(RimCommentListBean data) {
        swipeLayouts.setRefreshing(false);
        rimShopCommentListAdapter.setEmptyView(notDataView);
        rimShopCommentListAdapter.setNewData(new ArrayList<RimCommentListBean.DataBean.PingjiaListBean>());
        onError(data.MSG, data.status);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}